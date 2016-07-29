package com.iconcloud.core.model;

public class Project extends DataObject{

	private String status = null;
	private String dispalyName = null;
	private String description = null;

	public String getDispalyName() {
		return dispalyName;
	}

	public void setDispalyName(String dispalyName) {
		this.dispalyName = dispalyName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public DataObject processJSONString(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
