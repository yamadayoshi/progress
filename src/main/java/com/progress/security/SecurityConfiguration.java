package com.progress.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	private final String SQL_LOGIN  = "select login, password, active from users where login = ?";
	
	private final String SQL_PERMITION = "select u.login, p.description from users_permition up inner join users u on u.id = up.users_id inner join permition p on p.id = up.permition_id where u.login = ?";
	
//	private final String SQL_GROUP = "select g.id, g.description, p.description from grupo_permition gp \n" + 
//			"inner join grupo g on g.id = gp.groups_id \n" + 
//			"inner join permition p on p.id = gp.permition_id \n" + 
//			"inner join user_group ug on ug.group_id = gp.groups_id \n" + 
//			"inner join user u on u.id = ug.user_id\n" + 
//			"where u.login = ?";
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/request/api/**","/client/api/**","/resources/**").permitAll()
				.anyRequest().authenticated()
			.and()
			.formLogin()
				.loginPage("/login").permitAll()
			.and()
				.logout().logoutSuccessUrl("/login");
	}	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.passwordEncoder(new BCryptPasswordEncoder())
			.usersByUsernameQuery(SQL_LOGIN)
			.authoritiesByUsernameQuery(SQL_PERMITION);
//			.groupAuthoritiesByUsername(SQL_GROUP);
	}
}
