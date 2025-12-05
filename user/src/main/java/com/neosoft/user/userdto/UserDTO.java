package com.neosoft.user.userdto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDTO {

	public Long id;
	
	public String name;
	
	public String email;
	
	public String password;
	
	public String mobileNo;
	
	public LocalDate dob;
	
	private Integer flag;
}
