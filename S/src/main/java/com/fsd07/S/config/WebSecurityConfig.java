package com.fsd07.S.config;

import com.fsd07.S.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/adminlogin", "/studentlogin", "/instructorlogin", "/register", "/login").permitAll()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/instructor/**").hasRole("INSTRUCTOR")
                    .antMatchers("/student/**").hasRole("STUDENT")
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/")
                .and()
                .formLogin()
                    .loginPage("/studentlogin")
                    .failureUrl("/studentlogin_err")
                    .defaultSuccessUrl("/student/homepage")
                .and()
                .formLogin()
                    .loginPage("/adminlogin")
                    .failureUrl("/adminLogin?error=true")
                    .defaultSuccessUrl("/admin/userlist")
                .and()
                .formLogin()
                    .loginPage("/instructorLogin")
                    .failureUrl("/instructorLogin?error=true")
                    .defaultSuccessUrl("/student/homepage")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/");
    }
}
