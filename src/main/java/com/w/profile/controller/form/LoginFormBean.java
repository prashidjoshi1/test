package com.w.profile.controller.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


public class LoginFormBean {
	
	@NotNull
	@NotEmpty(message = "Please enter userid")
	private String userid;
	
	@NotNull
	@NotEmpty(message = "Please enter password")
	private String password;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	


}
