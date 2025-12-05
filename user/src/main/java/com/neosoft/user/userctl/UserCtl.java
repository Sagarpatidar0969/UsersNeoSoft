package com.neosoft.user.userctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neosoft.user.entity.User;
import com.neosoft.user.userserviceimpl.UserServiceImpl;
import com.neosoft.user.userserviceint.UserServiceInt;

@RestController
@RequestMapping("/user")
public class UserCtl {
	
	@Autowired
	public UserServiceImpl userServiceImpl;
	
	
	
	
	@PostMapping("/add")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		User saved = userServiceImpl.add(user);
		return ResponseEntity.ok(saved);
		
	}
	
	 @PutMapping("/update/{id}")
	    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
	        User updated = userServiceImpl.update(id, user);
	        return ResponseEntity.ok(updated);
	    }
	 
	 
	 @DeleteMapping("/delete/{id}")
	 public ResponseEntity<String> softDeleteUser(@PathVariable Long id) {
	     userServiceImpl.delete(id);
	     return ResponseEntity.ok("User soft-deleted successfully");
	 }
	
	
}
