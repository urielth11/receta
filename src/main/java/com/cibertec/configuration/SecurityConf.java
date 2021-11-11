package com.cibertec.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cibertec.service.espec.LoginServImpl;

public class SecurityConf extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginServImpl serv;

	// registrar en el contendor de Spring PasswordEncoder
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		//auth.inMemoryAuthentication().withUser("test").password("test").roles("USER");
		auth.userDetailsService(serv).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll()
		//.and()
		.csrf().disable().authorizeRequests().antMatchers("/**").authenticated()
		/*.and().formLogin().loginPage("/login").permitAll()
		.and().formLogin().defaultSuccessUrl("/usuario")
		.and().logout().logoutSuccessUrl("/login");*/
		//.csrf().ignoringAntMatchers("/usuario/**").and()
		//.csrf().disable().authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
		//.and().csrf().ignoringAntMatchers("/usuario/**")
		//.authorizeRequests().antMatchers("/").permitAll().anyRequest().authenticated()
        .and().formLogin().loginPage("/login").permitAll()
        //.formLogin().loginPage("/login").permitAll()
        .and().formLogin().successForwardUrl("/principal")
        .and().formLogin().defaultSuccessUrl("/principal")
		.and()
        .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
		//.and().logout().logoutSuccessUrl("/login").permitAll();

	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", 
                		"/static/**", 
                		"/css/**", 
                		"/js/**", 
                		"/images/**",
                		"/vendor/**",
                		"/fonts/**",
                		"/usuario/**",
                		"/layout/**",
                		"/templates/**",
                		"/assets/**");
    }
}
