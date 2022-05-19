package com.cooking.FavouriteRecipe.config;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.cooking.FavouriteRecipe.exception.ForbiddenResponse;
import com.cooking.FavouriteRecipe.jwt.JwtFilter;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JwtFilter filter;
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws
	Exception {
		return super.authenticationManagerBean();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/login","/h2-console/**").permitAll()
		.anyRequest().authenticated()
		.and()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.headers().frameOptions().sameOrigin();
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		
		 http.exceptionHandling()
         .authenticationEntryPoint((request, response, e) ->
         {
        	 response.setStatus(HttpServletResponse.SC_FORBIDDEN);
             response.getWriter().write(new ForbiddenResponse("ACCESS DENIED 403 Forbidden").toString());
         
         });
	}
}