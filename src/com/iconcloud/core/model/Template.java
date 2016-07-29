package com.iconcloud.core.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.iconcloud.core.config.CloudConfig;
import com.iconcloud.core.util.Util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Template extends DataObject {

	private List<String> tags = null;
	private List<TemplateParameter> parameters = null;

	public List<TemplateParameter> getParameters() {
		if (parameters == null) {
			parameters = new ArrayList<TemplateParameter>();
		}
		return parameters;
	}

	public void setParameters(List<TemplateParameter> parameters) {
		this.parameters = parameters;
	}

	public List<String> getTags() {
		if (tags == null) {
			tags = new ArrayList<String>();
		}

		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String processTemplate() {
		String ret = null;
		HashMap<String, String> pmap = new HashMap<String, String>();
		for (TemplateParameter p : this.getParameters()) {
			pmap.put(p.getName(), p.getValue());
		}

		JSONObject t = null;
		try {
			t = JSONObject.fromObject(this.getJson());
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (t != null) {

			if (!t.has(CloudConfig.KIND)){
				t.accumulate(CloudConfig.KIND, "Template");
			}
			
			if (!t.has(CloudConfig.API_VERSION)){
				t.accumulate(CloudConfig.API_VERSION, CloudConfig.API_VERSION_VALUE);
			}
			
			JSONArray parr = Util.getJSONArray(t, "parameters");

			for (int i = 0; i < parr.size(); i++) {
				JSONObject node = parr.getJSONObject(i);
				
				String v = Util.convToString(pmap.get(node.get("name")));
				
				if(v == null || "".equals(v)){
					node.remove("value");
				}else{
					node.element("value", v);
				}				
			}

			ret = t.toString();

		}
		return ret;
	}

	@Override
	public DataObject processJSONString(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
