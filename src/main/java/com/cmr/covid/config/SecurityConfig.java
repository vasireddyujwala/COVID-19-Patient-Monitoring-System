package com.cmr.covid.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SimpleAuthenticationSuccessHandler successHandler;

	@Value("${spring.queries.loginusers-query}")
	private String usersQuery;

	@Value("${spring.queries.loginroles-query}")
	private String rolesQuery;

	@Autowired
	DataSource dataSource;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(usersQuery)
				.authoritiesByUsernameQuery(rolesQuery);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN").and().formLogin().successHandler(successHandler)
				.loginPage("/login").and().logout().permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);

		http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
				.antMatchers("/user/**").hasRole("USER").and().formLogin().successHandler(successHandler)
				.loginPage("/login").and().logout().permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
				.deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);
		
		http.csrf().requireCsrfProtectionMatcher(new AntPathRequestMatcher("**/login")).and().authorizeRequests()
		.antMatchers("/doctor/**").hasRole("DOCTOR").and().formLogin().successHandler(successHandler)
		.loginPage("/login").and().logout().permitAll().and().logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
		.deleteCookies("auth_code", "JSESSIONID").invalidateHttpSession(true);
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/*.css");
		web.ignoring().antMatchers("/*.js");
	}

}
