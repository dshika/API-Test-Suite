package com.test.entity;

public class TestData {
	private String testCaseName;
	private String testCaseID;
	private String apiKey;
	private String baseURL;
	private String ext;
	private String contentType;
	private String inputParameters;
	private String expectedOutput;
	
	public TestData(String testCaseName, String testCaseID, String apiKey, String baseURL, String ext,
			String contentType, String inputParameters, String expectedOutput) {
		super();
		this.testCaseName = testCaseName;
		this.testCaseID = testCaseID;
		this.apiKey = apiKey;
		this.baseURL = baseURL;
		this.ext = ext;
		this.contentType = contentType;
		this.inputParameters = inputParameters;
		this.expectedOutput = expectedOutput;
	}
	
	public TestData() {
		// TODO Auto-generated constructor stub
	}

	public String getTestCaseName() {
		return testCaseName;
	}
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}
	public String getTestCaseID() {
		return testCaseID;
	}
	public void setTestCaseID(String testCaseID) {
		this.testCaseID = testCaseID;
	}
	public String getApiKey() {
		return apiKey;
	}
	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}
	public String getBaseURL() {
		return baseURL;
	}
	public void setBaseURL(String baseURL) {
		this.baseURL = baseURL;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getInputParameters() {
		return inputParameters;
	}
	public void setInputParameters(String inputParameters) {
		this.inputParameters = inputParameters;
	}
	public String getExpectedOutput() {
		return expectedOutput;
	}
	public void setExpectedOutput(String expectedOutput) {
		this.expectedOutput = expectedOutput;
	}
	
	

}
