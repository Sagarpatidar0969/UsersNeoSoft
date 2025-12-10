package com.neosoft.user.userserviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.user.entity.User;
import com.neosoft.user.userdaoint.UserRepositoryInt;
import com.neosoft.user.userdto.LoginDTO;
import com.neosoft.user.userdto.LoginResponseDTO;


@Service

public class AuthServiceImpl {
		
		@Autowired
	    private UserRepositoryInt userRepositoryInt;

	    
	    public LoginResponseDTO login(LoginDTO login) {

	        User user = userRepositoryInt.findByEmail(login.getEmail())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        if (!user.getPassword().equals(login.getPassword())) {
	            throw new RuntimeException("Invalid Password");
	        }

	        return new LoginResponseDTO("Login Successful");
	    }
	}



