package com.neosoft.user.userserviceint;

import com.neosoft.user.userdto.LoginDTO;
import com.neosoft.user.userdto.LoginResponseDTO;

public interface AuthServiceInt {
	
	public LoginResponseDTO login(LoginDTO login);
	

}
