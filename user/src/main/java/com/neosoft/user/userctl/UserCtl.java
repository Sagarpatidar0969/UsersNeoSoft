package com.neosoft.user.userctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	 
	 
		 @PostMapping("/delete")
		 public ResponseEntity<String> softDeleteUser(@RequestBody List<Long> userIds) {
		     userServiceImpl.delete(userIds);
		     return ResponseEntity.ok("User soft-deleted successfully");
		 }
	
	 @GetMapping("/list")
	 public ResponseEntity<?> listUsers(
	         @RequestParam(defaultValue = "0") int page,
	         @RequestParam(defaultValue = "5") int size) {

	     return ResponseEntity.ok(userServiceImpl.getUsers(page, size));
	 }
	 
	 
//	 @PutMapping("/update/{id}")
//	    public ResponseEntity<User> editUser(@PathVariable Long id, @RequestBody User user) {
//	        User updatedUser = userServiceImpl.editUser(id, user);
//	        if (updatedUser != null) {
//	            return ResponseEntity.ok(updatedUser);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    }
	  @GetMapping("/{id}")
	    public ResponseEntity<User> findUser(@PathVariable Long id) {
	        User user = userServiceImpl.getUserById(id);
	        if (user != null) {
	            return ResponseEntity.ok(user);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	
}
