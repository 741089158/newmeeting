package com.cc.bean;


import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OptionBean {

	private String id;
	private String title;
	private String selected = "";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}

	public OptionBean(String id, String title, String selected) {
		super();
		this.id = id;
		this.title = title;
		this.selected = selected;
	}

	public OptionBean(String id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public OptionBean() {
		super();
	}

}
