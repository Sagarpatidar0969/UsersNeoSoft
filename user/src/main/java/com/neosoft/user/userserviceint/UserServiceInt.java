package com.neosoft.user.userserviceint;

import java.util.List;

import com.neosoft.user.entity.User;

public interface UserServiceInt {
	
	public User add(User user);
	
	public  User update(Long id, User user); 
	
	

	public void delete(List<Long> userIds);
	
	
	    
}
