package com.greatlearning.EventManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.greatlearning.EventManagement.entities.User;
import com.greatlearning.EventManagement.repository.UserRepository;
import com.greatlearning.EventManagement.security.MyUserDetails;

public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.getByUsername(username);
		
		if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
		
		return new MyUserDetails(user);
	}

}
