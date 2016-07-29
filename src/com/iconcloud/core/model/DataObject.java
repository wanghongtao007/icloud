package com.iconcloud.core.model;

import java.util.HashMap;
import java.util.Map;

abstract public class DataObject implements IDataObject {

	private String name = null;
	private String displayName = null;
	private String description = null;
	private String json = null;
	private String namespace = null;
	private Map<String, String> labels = null;
	private String creationTimeStamp = null;
	
	 

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, String> getLabels() {
		return labels == null ? new HashMap<String,String>():labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getCreationTimeStamp() {
		return creationTimeStamp;
	}

	public void setCreationTimeStamp(String creationTimeStamp) {
		this.creationTimeStamp = creationTimeStamp;
	}

}
