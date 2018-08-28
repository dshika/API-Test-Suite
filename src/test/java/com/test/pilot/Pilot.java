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

	
