package com.test.pilot;

import static io.restassured.RestAssured.given;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.test.entity.RestApiConnector;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Pilot {

	public static void main(String[] args) {
		
		try {
			RestApiConnector restApiConnector = new RestApiConnector("https://35.237.112.170/deepfence/v1.3", 
					"application/json", false, "_hkHD8UaqO_BBq849ydCWQeHwbZYupqtcCm9WGLAcyc");
			restApiConnector.setExt("/users/network_protection_policy/23");
			
			Response response = null;
			
			RequestSpecification reqSpec = given();
			reqSpec.relaxedHTTPSValidation();
			reqSpec.header("Authorization", "Bearer " + "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MzUwNzAyNTYsImlhdCI6MTUzNDk4Mzg1NiwibmJmIjoxNTM0OTgzODU2LCJqdGkiOiI4YzZiMjVhMC1kOGRmLTRjYWItODQ4OS0xNjM2OGE2NzRjZjMiLCJpZGVudGl0eSI6eyJpZCI6MSwiZmlyc3RfbmFtZSI6IlJhbWFuYW4iLCJsYXN0X25hbWUiOiJSYXZpIiwiZW1haWwiOiJyYW1hbmFuQGRlZXBmZW5jZS5pbyIsImNvbXBhbnkiOiJkZWVwZmVuY2UiLCJyb2xlIjoiYWRtaW4iLCJhcGlfa2V5IjoiX2hrSEQ4VWFxT19CQnE4NDl5ZENXUWVId2JaWXVwcXRjQ205V0dMQWN5YyIsImNvbXBhbnlfbGljZW5zZV9leGlzdHMiOnRydWV9LCJmcmVzaCI6ZmFsc2UsInR5cGUiOiJhY2Nlc3MifQ.nqV6mM0B8CIdagVdA7gr-t9b9T5zx0xFAoTZ0kiktvY");
			reqSpec.header("Content-type", restApiConnector.getContentType());
			response = reqSpec.delete(restApiConnector.getBaseURL() + restApiConnector.getExt());
			
			if(response.getBody()!=null){
			//JSONObject respData = new JSONObject(response.getBody().asString());
			System.out.println(response.getBody().asString());
			System.out.println(response.getStatusCode());
			}
			else{
				System.out.println("Response body is null");
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static JSONObject createJSONObject(String key, String val, JSONObject jsonObj) {
		if(!StringUtils.contains(key, ".")){
			jsonObj.put(key, getJsonData(val));
		}
		else{
			String key1 = key.substring(0, key.indexOf("."));
			String key2 = key.substring(key.indexOf(".") + 1);
			if(!jsonObj.has(key1)){
				jsonObj.put(key1, createJSONObject(key2, val, new JSONObject()));
			}
			else{
				jsonObj.put(key1, createJSONObject(key2, val, jsonObj.getJSONObject(key1)));
			}
		}
		return jsonObj;
	}
	
	public static Object getJsonData(String val){
		if(val.startsWith("[") && val.endsWith("]")){
			JSONArray jsonArray = new JSONArray(val);
			return jsonArray;
		}
		return val;
	}
	
}

	
