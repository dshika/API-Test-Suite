package com.test.testcases;

import static org.testng.Assert.fail;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.test.entity.RestApiConnector;
import com.test.entity.SessionConfig;
import com.test.entity.TestData;
import com.test.logging.LogUtil;
import com.test.service.ServiceFactory;
import com.test.util.TestCommonUtil;

public class TestMethods {
	
	@DataProvider(name = "authMethodDataProvider")
	public Object[][] authMethodDataProvider() {
		return ServiceFactory.getExcelDataReaderService().getTestData("authMethod");
	}
	@Test(dataProvider = "authMethodDataProvider", priority=1)
	public void authMethod(TestData testData){
		try {
			RestApiConnector restApiConnector = new RestApiConnector(testData.getBaseURL(), testData.getContentType(),
					false, testData.getApiKey());
			if (StringUtils.isNotBlank(testData.getInputParameters())) {
				restApiConnector.setRequestParameters(TestCommonUtil.formatInputParam(testData.getInputParameters()));
			}

			restApiConnector.setExt(testData.getExt());

			JSONObject responseData = ServiceFactory.getRestService().postRequest(restApiConnector);

			if (responseData != null) {
				LogUtil.getLogger().debug("TestMethods > postRequest() | Response Data : {}", responseData);

				Assert.assertTrue(TestCommonUtil.validateExpectedResult(responseData, testData.getExpectedOutput()));

				if (responseData.getBoolean("success")) {
					SessionConfig.SESSION_TOKEN_KEY = responseData.getJSONObject("data").getString("access_token");
				}

			} else {
				Assert.fail("Response data is null");
				// fail("Response data is null");
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("", ex);
			ex.printStackTrace();
			// fail(ex.getMessage());
		}
		
	}
	
	@DataProvider(name = "postMethodDataProvider")
	public Object[][] postMethodDataProvider() {
		return ServiceFactory.getExcelDataReaderService().getTestData("postMethod");
	}

	@Test(dataProvider = "postMethodDataProvider", priority=4, dependsOnMethods="authMethod")
	public void postMethod(TestData testData) {
		try {
			RestApiConnector restApiConnector = new RestApiConnector(testData.getBaseURL(), testData.getContentType(),
					false, testData.getApiKey());
			if (StringUtils.isNotBlank(testData.getInputParameters())) {
				restApiConnector.setRequestParameters(TestCommonUtil.formatInputParam(testData.getInputParameters()));
			}
			restApiConnector.setAccessToken(SessionConfig.SESSION_TOKEN_KEY);

			restApiConnector.setExt(testData.getExt());

			JSONObject responseData = ServiceFactory.getRestService().postRequest(restApiConnector);

			if (responseData != null) {
				LogUtil.getLogger().debug("TestMethods > postRequest() | Response Data : {}", responseData);

				Assert.assertTrue(TestCommonUtil.validateExpectedResult(responseData, testData.getExpectedOutput()));

				/*if (responseData.getBoolean("success")) {
					SessionConfig.SESSION_TOKEN_KEY = responseData.getJSONObject("data").getString("access_token");
				}*/

			} else {
				Assert.fail("Response data is null");
				// fail("Response data is null");
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("", ex);
			ex.printStackTrace();
			// fail(ex.getMessage());
		}
	}

	@Test(dataProvider = "getMethodDataProvider", priority=2, dependsOnMethods="authMethod")
	public void getMethod(TestData testData) {
		try {
			RestApiConnector restApiConnector = new RestApiConnector(testData.getBaseURL(), testData.getContentType(),
					false, testData.getApiKey());

			restApiConnector.setAccessToken(SessionConfig.SESSION_TOKEN_KEY);
			LogUtil.getLogger().info("TestMethods > postRequest() | Received Access Token : {}",
					SessionConfig.SESSION_TOKEN_KEY);

			restApiConnector.setExt(testData.getExt());

			JSONObject responseData = ServiceFactory.getRestService().getRequest(restApiConnector);
			System.out.println(responseData.toString());
			
			if (responseData != null) {
				LogUtil.getLogger().info("TestMethods > postRequest() | Response Data : {}", responseData);
				Assert.assertEquals(true, responseData.getBoolean("success"));
			} else {
				fail("Response data is null");
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("", ex);
			ex.printStackTrace();
		}
	}

	@DataProvider(name = "getMethodDataProvider")
	public Object[][] getMethodDataProvider() {
		return ServiceFactory.getExcelDataReaderService().getTestData("getMethod");
	}
	
	@Test(dataProvider = "deleteMethodDataProvider", priority=3, dependsOnMethods="authMethod")
	public void deleteMethod(TestData testData) {
		try {
			RestApiConnector restApiConnector = new RestApiConnector(testData.getBaseURL(), testData.getContentType(),
					false, testData.getApiKey());

			restApiConnector.setAccessToken(SessionConfig.SESSION_TOKEN_KEY);
			LogUtil.getLogger().info("TestMethods > postRequest() | Received Access Token : {}",
					SessionConfig.SESSION_TOKEN_KEY);

			restApiConnector.setExt(testData.getExt());

			JSONObject responseData = ServiceFactory.getRestService().deleteRequest(restApiConnector);
			System.out.println(responseData.toString());
			
			if (responseData != null) {
				LogUtil.getLogger().info("TestMethods > postRequest() | Response Data : {}", responseData);
				Assert.assertEquals(true, responseData.getBoolean("success"));
			} else {
				fail("Response data is null");
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("", ex);
			ex.printStackTrace();
		}
	}

	@DataProvider(name = "deleteMethodDataProvider")
	public Object[][] deleteMethodDataProvider() {
		return ServiceFactory.getExcelDataReaderService().getTestData("deleteMethod");
	}
	
}
