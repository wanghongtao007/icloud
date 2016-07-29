package com.iconcloud.core.model;

import com.iconcloud.core.exception.CCMalFormatJsonException;
import com.iconcloud.core.util.Util;

import net.sf.json.JSONObject;

public class DeploymentConfigList {

	public static DeploymentConfigList processJSONString(String json) throws CCMalFormatJsonException {
		DeploymentConfigList ret = null;
//		JSONObject j = null;
//
//		if (json != null) {
//			return null;
//		}
//
//		try {
//			j = JSONObject.fromObject(json);
//			if (j == null) {
//				return null;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new CCMalFormatJsonException(json);
//		}
//
//		JSONObject md = Util.getJSONObject(j, "metadata");
//
//		ret = new DeploymentConfigList();
//		ret.setName(md.getString("name"));

		return ret;
	}
}
