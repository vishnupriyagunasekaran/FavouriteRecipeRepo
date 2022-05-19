package com.cooking.FavouriteRecipe.jwt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService { 

	@Value("${un}")
	private String un;


	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (un.contains(username)) { 
			return new User(username, 
					"$2a$12$gF6c28utPWVQuACr2lJpqeMwAmJCLbsU6Vym5TZxUA2WJIo.ZveIC", 
					new ArrayList<>()); 
		} else { 
			throw new UsernameNotFoundException("User not found with username: " + username); 
		} 
	}
}