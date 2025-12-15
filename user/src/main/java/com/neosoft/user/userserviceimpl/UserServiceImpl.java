package com.neosoft.user.userserviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.neosoft.user.entity.User;
import com.neosoft.user.userdaoint.UserRepositoryInt;
import com.neosoft.user.userserviceint.UserServiceInt;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInt {

	@Autowired
	public UserRepositoryInt userRepositoryInt;
	
	// public PasswordEncoder passwordEncoder;

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public User add(User user) {

		return userRepositoryInt.save(user);
		
//		user.setPassword(passwordEncoder.encode(user.getPassword()));
//		return userRepositoryInt.save(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User update(Long id, User user) {

		User existing = userRepositoryInt.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

		User updated = User.builder().id(existing.getId()).name(user.getName()).email(user.getEmail())
				.password(user.getPassword()).mobileNo(user.getMobileNo()).dob(user.getDob()).flag(user.getFlag())
				.build();

		return userRepositoryInt.save(updated);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(Long id) {
	    User existing = userRepositoryInt.findById(id)
	            .orElseThrow(() -> new RuntimeException("User not found with ID: " + id));

	    
	    existing.setFlag(0);

	    userRepositoryInt.save(existing);   
	}
	
	
	
	public Page<User> getUsers(int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return userRepositoryInt.findAll(pageable);
	}
	
	
	public User editUser(Long id, User user) {

	    User existingUser = userRepositoryInt.findById(id)
	        .orElseThrow(() -> new RuntimeException("User not found"));

	    existingUser.setName(user.getName());
	    existingUser.setEmail(user.getEmail());

	    if (existingUser.getFlag() == null) {
	        existingUser.setFlag(1); // SAFETY
	    }

	    return userRepositoryInt.save(existingUser);
	}
	
	public User getUserById(Long id) {
        Optional<User> optionalUser = userRepositoryInt.findById(id);
        return optionalUser.orElse(null); 
    }

	

}
