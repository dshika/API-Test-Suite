package com.test.service;

import com.test.data.ReadExcelData;

public class ServiceFactory {
	private static RestService restService = new RestServiceImpl();
	private static ReadExcelData readExcelData = new ReadExcelData();

	private ServiceFactory() {

	}

	public static ReadExcelData getExcelDataReaderService() {
		return readExcelData;
	}

	public static RestService getRestService() {
		return restService;
	}

}
