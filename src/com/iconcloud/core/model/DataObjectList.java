package com.iconcloud.core.model;

import java.util.ArrayList;
import java.util.List;

import com.iconcloud.core.exception.CCMalFormatJsonException;
import com.iconcloud.core.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class DataObjectList {
	public static List<DataObject> processJSONString(String json, DataObject dataObj) throws CCMalFormatJsonException {
		List<DataObject> ret = null;
		JSONObject j = null;

		if (json == null) {
			return null;
		}

		try {
			j = JSONObject.fromObject(json);
			if (j == null) {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new CCMalFormatJsonException(json);
		}

		JSONArray items = Util.getJSONArray(j, "items");

		if(items == null ){
			return null;
		}
		
		ret = new ArrayList<DataObject>();
		
		for(int i =0 ; i < items.size(); i++){
			ret.add(dataObj.processJSONString(items.get(i).toString()));
		}
		
		return ret;

	}
}
