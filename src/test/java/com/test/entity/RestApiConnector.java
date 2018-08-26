package com.test.entity;

import org.json.JSONObject;

import io.restassured.response.Response;

public class RestApiConnector {
	private String baseURL;
	private String ext;
	private String contentType;
	private JSONObject requestParameters;
	private boolean sslVerification;
	private JSONObject apiResponse;
	private String apiKey;
	private String accessToken;
	
	public RestApiConnector(String baseURL, String contentType, boolean sslVerification, String apiKey) {
		super();
		this.baseURL = baseURL;
		this.contentType = contentType;
		this.sslVerification = sslVerification;
		this.apiKey = apiKey;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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
	public JSONObject getRequestParameters() {
		return requestParameters;
	}
	public void setRequestParameters(JSONObject requestParameters) {
		this.requestParameters = requestParameters;
	}
	public boolean isSslVerification() {
		return sslVerification;
	}
	public void setSslVerification(boolean sslVerification) {
		this.sslVerification = sslVerification;
	}
	public JSONObject getApiResponse() {
		return apiResponse;
	}
	public void setApiResponse(JSONObject apiResponse) {
		this.apiResponse = apiResponse;
	}
	

}
