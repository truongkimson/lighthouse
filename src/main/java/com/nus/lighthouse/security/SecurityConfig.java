package com.nus.lighthouse.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    UserDetailsService userDetailsService;
//    @Autowired
//    PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(
            UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired


    @Override
    public void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
//                .passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder().encode
//        ("password1")).roles("STU")
//                .and()
//                .withUser("user2").password(passwordEncoder().encode("password2")).roles("ADM")
//                .and()
//                .withUser("user3").password(passwordEncoder().encode("password3")).roles("LEC");
    }

    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADM")
                .antMatchers("/student/**").hasRole("STU")
                .antMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();
//                .loginPage("/login.html")
//                .loginProcessingUrl("/perform_login")
//                .defaultSuccessUrl("/index.html")
//                .failureUrl("/login.html?error=true")
//                .and()
//                .logout()
//                .logoutUrl("/perform_logout")
//                .invalidateHttpSession(true)
//                .deleteCookies("JSSESSIONID");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptPasswordEncoder.BCryptVersion.$2Y);
    }
}
