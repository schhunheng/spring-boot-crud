package com.example.demo.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // set your configuration on the auth object

        // auth.inMemoryAuthentication()
        // .withUser("blah")
        // .password("blah")
        // .roles("USER")
        // .and()
        // .withUser("foo")
        // .password("foo")
        // .roles("ADMIN");

        // Using JDBC authentication with spring security
        auth.jdbcAuthentication()
                .dataSource(dataSource);
        // .withDefaultSchema()
        // .withUser(
        // User.withUsername("user")
        // .password("pass")
        // .roles("USER"))
        // .withUser(
        // User.withUsername("admin")
        // .password("pass")
        // .roles("ADMIN"));

    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeRequests()
        // .antMatchers("/").permitAll()
        // .antMatchers("/**")
        // .hasRole("ADMIN")
        // .and()
        // .formLogin();
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/user").hasAnyRole("USER", "ADMIN")
                .antMatchers("/").permitAll()
                .and().formLogin();
    }

}
