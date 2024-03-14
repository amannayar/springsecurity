package com.sbs.springsecurityexample;


import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
      @Autowired
	  public DataSource dataSource;
	     
	    @Autowired
	    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	    	System.out.println(dataSource);
	        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
	            .dataSource(dataSource)
	            .usersByUsernameQuery("select username, password, enabled from user where username=?")
	            .authoritiesByUsernameQuery("select username, role from user where username=?")
	        ;
	    }
	    
	    
	    @Bean
		public PasswordEncoder passwordEncoder() {
			return (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();
		}
	
	
	 
	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	    	System.out.println(dataSource);
	        http.authorizeRequests()
	            .anyRequest().authenticated()
	            .and()
	            .formLogin().permitAll()
	            .and()
	            .logout().permitAll();     
	    }
}