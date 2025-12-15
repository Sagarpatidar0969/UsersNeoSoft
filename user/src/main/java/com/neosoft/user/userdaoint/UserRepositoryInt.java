package com.neosoft.user.userdaoint;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.neosoft.user.entity.User;

public interface UserRepositoryInt extends JpaRepository<User, Long>{
	
	Optional<User>  findByEmail(String email);
	
	 Page<User> findAll(Pageable pageable);
	 
	 //public User editUser(Long id, User user);
	 
	 public User getUserById(Long id) ;

}
