package com.test.service;

import org.json.JSONObject;

import com.test.entity.RestApiConnector;

public interface RestService {
	public JSONObject getRequest(RestApiConnector apiConnector);
	public JSONObject postRequest(RestApiConnector apiConnector);
	public JSONObject deleteRequest(RestApiConnector apiConnector);
	

}
