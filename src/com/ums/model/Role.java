package com.ums.model;

public enum  Role {
     SIMPLE_USER("SIMPLE USER"),
     ADMINISTRATEUR("ADMINISTRAEUR");
	private String value;
	
	private Role(String value) {
		this.value = value;
	}
	
	public String getValue() {
		// TODO Auto-generated method stub
		return value;
	}

}
