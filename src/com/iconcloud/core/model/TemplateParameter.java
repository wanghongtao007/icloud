package com.iconcloud.core.model;

public class TemplateParameter extends DataObject {

	private String value = null;
	private Boolean required = false;
	private String generate = null;
	private String from = null;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Boolean getRequired() {
		return required;
	}

	public void setRequired(Boolean required) {
		this.required = required;
	}

	public String getGenerate() {
		return generate;
	}

	public void setGenerate(String generate) {
		this.generate = generate;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	@Override
	public DataObject processJSONString(String json) {
		// TODO Auto-generated method stub
		return null;
	}

}
