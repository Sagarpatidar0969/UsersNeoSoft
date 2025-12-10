package com.neosoft.user.userctl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.user.entity.User;
import com.neosoft.user.userdaoint.UserRepositoryInt;
import com.neosoft.user.userdto.LoginDTO;
import com.neosoft.user.userdto.LoginResponseDTO;
import com.neosoft.user.userserviceint.UserServiceInt;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	
	    @Autowired
	    private UserRepositoryInt userRepositoryInt;

	    @PostMapping("/login")
	    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginDTO login) {

	     Optional<User> userOptional = userRepositoryInt.findByEmail(login.getEmail());
	     
	     User user = userOptional.orElseThrow(() -> new RuntimeException("User not found"));

	       	
	        if (!user.getPassword().equals(login.getPassword())) {
	            throw new RuntimeException("Invalid Password");
	        }

	     
	        LoginResponseDTO response = new LoginResponseDTO(
	                "Login Successful"
	        );

	        return ResponseEntity.ok(response);
	    }
	}



