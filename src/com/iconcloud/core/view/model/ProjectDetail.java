package com.iconcloud.core.view.model;

import java.util.List;

import com.iconcloud.core.model.DataObject;
import com.iconcloud.core.model.Pod;

public class ProjectDetail {

	private String projectName = null;
	private List<Pod> pods = null;
	private List<DataObject> deploymentConfigs = null;
	
	

	public List<DataObject> getDeploymentConfigs() {
		return deploymentConfigs;
	}

	public void setDeploymentConfigs(List<DataObject> deploymentConfigs) {
		this.deploymentConfigs = deploymentConfigs;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Pod> getPods() {
		return pods;
	}

	public void setPods(List<Pod> pods) {
		this.pods = pods;
	}

}
