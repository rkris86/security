package com.cts.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityWebConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

//    @Autowired
//    public void configurationAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder.jdbcAuthentication().dataSource(dataSource)
//                //.usersByUsernameQuery("select username,password,enabled from users where username=?")
//                .authoritiesByUsernameQuery("select username,role from user_roles where username=?").passwordEncoder(new BCryptPasswordEncoder());
//    }

//    @Autowired
//    public void myAuthentication(AuthenticationManagerBuilder auth){
//        auth.authenticationProvider(customAuthenticationProvider);
//
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }

}
