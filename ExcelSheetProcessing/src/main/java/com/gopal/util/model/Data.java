package com.gopal.util.model;

import java.io.Serializable;

public class Data implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String expectedId;
	private String autualId;
	private String expectedSubId;
	private String autualSubId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getExpectedId() {
		return expectedId;
	}

	public void setExpectedId(String expectedId) {
		this.expectedId = expectedId;
	}

	public String getAutualId() {
		return autualId;
	}

	public void setAutualId(String autualId) {
		this.autualId = autualId;
	}

	public String getExpectedSubId() {
		return expectedSubId;
	}

	public void setExpectedSubId(String expectedSubId) {
		this.expectedSubId = expectedSubId;
	}

	public String getAutualSubId() {
		return autualSubId;
	}

	public void setAutualSubId(String autualSubId) {
		this.autualSubId = autualSubId;
	}

}
