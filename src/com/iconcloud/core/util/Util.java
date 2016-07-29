package com.iconcloud.core.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Util {

	public static String getJSONString(JSONObject j, String k){
		return j.containsKey(k) ? j.getString(k):null;
	}
	
	public static JSONObject getJSONObject(JSONObject j, String k){
		return j.containsKey(k) ? j.getJSONObject(k):null;
	}
	
	public static JSONArray getJSONArray(JSONObject j, String k){
		return j.containsKey(k) ? j.getJSONArray(k):null;
	}
	
	public static String convToString(Object obj){
		return obj == null ? "" : obj.toString();
	}
}
