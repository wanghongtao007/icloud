package com.iconcloud.core.view.model;

import java.util.ArrayList;

import com.iconcloud.core.model.Project;

public class UserHome {

	private ArrayList<Project> projects = null;

	public Integer getProjectsSize() {
		return projects == null ? 0 : projects.size();
	}

	public ArrayList<Project> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Project> projects) {
		this.projects = projects;
	}

}
