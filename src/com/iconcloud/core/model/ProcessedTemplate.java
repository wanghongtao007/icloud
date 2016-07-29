package com.iconcloud.core.model;

import java.util.ArrayList;
import java.util.List;

public class ProcessedTemplate extends DataObject {
	List<PostObject> objects = new ArrayList<PostObject>();

	public List<PostObject> getObjects() {
		return objects;
	}

	public void setObjects(List<PostObject> objects) {
		this.objects = objects;
	}

	@Override
	public DataObject processJSONString(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
