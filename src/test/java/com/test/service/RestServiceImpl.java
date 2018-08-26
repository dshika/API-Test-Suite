package com.test.service;

import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.testng.Assert;

import com.test.entity.RestApiConnector;
import com.test.entity.SessionConfig;
import com.test.logging.LogUtil;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestServiceImpl implements RestService {

	public JSONObject getRequest(RestApiConnector apiConnector) {

		Response response = null;
		JSONObject responseData = null;
		try {
			RequestSpecification reqSpec = given();
			if (!apiConnector.isSslVerification()) {
				reqSpec.relaxedHTTPSValidation();
			}

			if (StringUtils.isNotBlank(apiConnector.getAccessToken())) {
				reqSpec.header("Authorization", "Bearer " + apiConnector.getAccessToken());
			}

			if (StringUtils.isNotBlank(apiConnector.getContentType())) {
				reqSpec.header("Content-type", apiConnector.getContentType());
			}

			if (StringUtils.isNotBlank(apiConnector.getExt())) {
				response = reqSpec.get(apiConnector.getBaseURL() + apiConnector.getExt());
			} else {
				response = reqSpec.get(apiConnector.getBaseURL());
			}

			if (response.getBody() != null) {
				responseData = new JSONObject(response.getBody().asString());
				apiConnector.setApiResponse(responseData);
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("RestServiceImpl > getRequest() | Error : ", ex);
		}

		return responseData;
	}
	
	public JSONObject postRequest(RestApiConnector apiConnector) {
		Response response = null;
		JSONObject responseData = null;
		try {
			RequestSpecification reqSpec = given();
			if (!apiConnector.isSslVerification()) {
				reqSpec.relaxedHTTPSValidation();
			}

			if (StringUtils.isNotBlank(apiConnector.getContentType())) {
				reqSpec.header("Content-type", apiConnector.getContentType());
			}
			
			String accessToken = ObjectUtils.firstNonNull(apiConnector.getAccessToken(),SessionConfig.SESSION_TOKEN_KEY);
			if (StringUtils.isNotBlank(accessToken)) {
				reqSpec.header("Authorization", "Bearer " + accessToken);
				
			}
			
			if(apiConnector.getRequestParameters() != null){
				reqSpec.body(apiConnector.getRequestParameters().toString());
			}
			
			if (StringUtils.isNotBlank(apiConnector.getExt())) {
				response = reqSpec.post(apiConnector.getBaseURL() + apiConnector.getExt());
			} else {
				response = reqSpec.post(apiConnector.getBaseURL());
			}

			if (response.getBody() != null) {
				responseData = new JSONObject(response.getBody().asString());
				apiConnector.setApiResponse(responseData);
				System.out.println(responseData.toString());
				
				
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("RestServiceImpl > postRequest() | Error : ", ex);
		}

		return responseData;

	}
	
	public JSONObject deleteRequest(RestApiConnector apiConnector) {

		Response response = null;
		JSONObject responseData = null;
		try {
			RequestSpecification reqSpec = given();
			if (!apiConnector.isSslVerification()) {
				reqSpec.relaxedHTTPSValidation();
			}

			if (StringUtils.isNotBlank(apiConnector.getAccessToken())) {
				reqSpec.header("Authorization", "Bearer " + apiConnector.getAccessToken());
			}

			if (StringUtils.isNotBlank(apiConnector.getContentType())) {
				reqSpec.header("Content-type", apiConnector.getContentType());
			}

			if (StringUtils.isNotBlank(apiConnector.getExt())) {
				response = reqSpec.delete(apiConnector.getBaseURL() + apiConnector.getExt());
			}/* else {
				response = reqSpec.get(apiConnector.getBaseURL());
			}*/

			if (response.getBody() != null) {
				responseData = new JSONObject(response.getBody().asString());
				apiConnector.setApiResponse(responseData);
			}
		} catch (Exception ex) {
			LogUtil.getLogger().error("RestServiceImpl > getRequest() | Error : ", ex);
		}

		return responseData;
	}

}
