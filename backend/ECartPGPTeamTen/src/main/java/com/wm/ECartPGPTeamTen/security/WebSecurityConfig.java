package com.wm.ECartPGPTeamTen.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import com.wm.ECartPGPTeamTen.service.ECartPgpJwtUserDetailsService;
import com.wm.ECartPGPTeamTen.service.MessageService;

/**
 * @author Raju
 *
 */
@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ECartPgpJwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	@Autowired
	ECartPgpAccessDeniedHandler accessDeniedHandler;
	
	@Autowired
	private ECartPgpJwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private ECartPgpJwtRequestFilter jwtRequestFilter;

	@Autowired
	MessageService messageService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(jwtUserDetailsService);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean	
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		
		String open = messageService.getMessage("ecart.openUrls");
		String adminRole = messageService.getMessage("ecart.adminrole");
		String[] nonRestricted = null; 
		if(open != null && !open.isEmpty()) {
			nonRestricted = open.split(",");
		}else {
			nonRestricted = new String[0];
		}
		httpSecurity.csrf().disable()
		.cors().configurationSource(request -> {
			CorsConfiguration cors = new CorsConfiguration();
			List<String> host = new ArrayList<>();
			host.add("*");
		      cors.setAllowedOrigins(host);
		      cors.setAllowedMethods(host);
		      cors.setAllowedHeaders(host);
		      return cors;
		    }).and()
			
				// dont authenticate this particular request
				.authorizeRequests().antMatchers(nonRestricted).permitAll()
				.antMatchers("/api/admin/**").hasAnyAuthority(adminRole)
				// all other requests need to be authenticated
				.anyRequest().authenticated().and()
				// make sure we use stateless session; session won't be used to
				// store user's state.
				.exceptionHandling()
				//.accessDeniedHandler("")
				.authenticationEntryPoint(jwtAuthenticationEntryPoint)
				.accessDeniedHandler(accessDeniedHandler)
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
