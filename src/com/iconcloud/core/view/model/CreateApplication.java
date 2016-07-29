package com.iconcloud.core.view.model;

import java.util.List;

import com.iconcloud.core.model.Template;

public class CreateApplication {

	List<Template> templates = null;
	private String projectName = null;
	
	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<Template> getTemplates() {
		return templates;
	}

	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

}
