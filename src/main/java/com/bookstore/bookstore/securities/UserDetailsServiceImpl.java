package com.bookstore.bookstore.securities;

import com.bookstore.bookstore.entities.UserLogin;
import com.bookstore.bookstore.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class UserDetailsServiceImpl implements UserDetailsService  {
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserLoginRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username)  {
	       UserLogin user = userRepo.findByUserName(username)
	    	       .orElseThrow(() -> new UsernameNotFoundException("Logon ID " + username + " not found"));

		PasswordEncoder encoder = new BCryptPasswordEncoder();
	        
		return new org.springframework.security.core.userdetails.User(user.getUsername(),encoder.encode(user.getPassword()),
	    	         getAuthorities(user));
	}
	
    private static Collection<? extends GrantedAuthority> getAuthorities(UserLogin user) {
        String[] userRoles = {"USER"};
        Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
        return authorities;
    }
}