package com.test.data;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.test.entity.TestData;
import com.test.logging.LogUtil;

public class ReadExcelData {
	public static final String testDataFilePath = "C:/Users/Shika/Desktop/TestSuite/app/data/TestData_v0.5.xlsx";
	public static final String testDataSheetName = "TestData";

	private int getRowCountForTestcase(String testCaseName, XSSFSheet dataSheet) {
		int rowIndex = 0;
		for (Row row : dataSheet) {
			if (row.getCell(0) != null && StringUtils.isNotBlank(row.getCell(0).toString())
					&& StringUtils.equalsIgnoreCase(testCaseName, row.getCell(0).toString())) {
				rowIndex++;
			}
		}
		return rowIndex;
	}

	private TestData[][] getRowDataForTestCase(String testCaseName, XSSFSheet dataSheet) {
		int lenRowForTestCase = getRowCountForTestcase(testCaseName, dataSheet);
		TestData[][] rowData = new TestData[lenRowForTestCase][1];

		int rowIndex = 0;

		for (Row row : dataSheet) {
			if (row.getCell(0) != null && StringUtils.isNotBlank(row.getCell(0).toString())
					&& StringUtils.equalsIgnoreCase(testCaseName, row.getCell(0).toString())) {
				TestData dataObj = new TestData();

				for (Cell cell : row) {
					switch (cell.getColumnIndex()) {
						case 0: {
							dataObj.setTestCaseName((cell != null) ? cell.toString() : "");
							break;
						}
						case 1: {
							dataObj.setTestCaseID((cell != null) ? cell.toString() : "");
							break;
						}
						case 2: {
							dataObj.setApiKey((cell != null) ? cell.toString() : "");
							break;
						}
						case 3: {
							dataObj.setBaseURL((cell != null) ? cell.toString() : "");
							break;
						}
						case 4: {
							dataObj.setExt((cell != null) ? cell.toString() : "");
							break;
						}
						case 5: {
							dataObj.setContentType((cell != null) ? cell.toString() : "");
							break;
						}
						case 6: {
							dataObj.setInputParameters((cell != null) ? cell.toString() : "");
							break;
						}
						case 7: {
							dataObj.setExpectedOutput((cell != null) ? cell.toString() : "");
							break;
						}
						default: {
							LogUtil.getLogger().warn("Input field not configured in TestData bean : Index[{}]",
									cell.getColumnIndex());
						}
					}
				}
				rowData[rowIndex][0] = dataObj;
				rowIndex++;
			}
		}

		return rowData;
	}

	public TestData[][] getTestData(String testCaseName) {
		TestData[][] rowData = null;
		try {
			if (StringUtils.isBlank(testDataFilePath))
				throw new Exception("Test data filepath is missing");
			if (StringUtils.isBlank(testDataSheetName))
				throw new Exception("Test data sheet is missing");
			File file = new File(testDataFilePath);

			if (file.isFile() && file.exists()) {
				XSSFWorkbook workbook = new XSSFWorkbook(file);
				XSSFSheet dataSheet = workbook.getSheet(testDataSheetName);
				int lenCol = dataSheet.getRow(0).getPhysicalNumberOfCells();
				int lenRow = dataSheet.getPhysicalNumberOfRows();
				LogUtil.getLogger().info("File Exists:[{}] Rows:[{}] Cols:[{}]", testDataFilePath, lenRow, lenCol);
				rowData = getRowDataForTestCase(testCaseName, dataSheet);

			} else {
				LogUtil.getLogger().warn("File[{}] does not exit", testDataFilePath);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rowData;
	}

}
