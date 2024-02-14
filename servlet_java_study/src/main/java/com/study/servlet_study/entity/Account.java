package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Account {

	private String userName;
	private String password;
	private String name;
	private String email;

}
