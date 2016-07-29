package com.iconcloud.core.model;

import java.util.Map;

public class Pod extends DataObject{

	private String name = null;
	private String status = null;
	private String namespace = null;
	private String hostIP = null;
	private String podIP = null;
	private String startTime = null;
	private Map<String, String> labels = null;
	private Boolean isApplicationPod = false;

	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	public String getPodIP() {
		return podIP;
	}

	public void setPodIP(String podIP) {
		this.podIP = podIP;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public Map<String, String> getLabels() {
		return labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	public Boolean getIsApplicationPod() {
		return isApplicationPod;
	}

	public void setIsApplicationPod(Boolean isApplicationPod) {
		this.isApplicationPod = isApplicationPod;
	}

	@Override
	public DataObject processJSONString(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
