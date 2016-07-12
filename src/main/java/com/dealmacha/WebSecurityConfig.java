package com.dealmacha;

import com.dealmacha.security.AuthenticationFailureHandler;
import com.dealmacha.security.AuthenticationSuccessHandler;
import com.dealmacha.security.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
/*import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;*/
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
/*@EnableWebMvcSecurity*/
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    /*   @Autowired
    private RestAuthenticationEntryPoint authenticationEntryPoint;*/
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public void configAuthentication(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/Angular/**").access("hasRole('ROLE_ADMIN')").antMatchers("/seller/**")
                .access("hasRole('ROLE_SELLER_ADMIN')")
                .antMatchers("/", "/register/**", "/users/**","/fonts/**", "/_media/**", "/home/**","/mobile/**", "/offers/**", "/merchant/**",
                        "/product/**", "/common/**", "/products/**", "/cmsSection/**", "/cmsBlock/**", "/merchantCategory/**",
                        "/merchantCategoryMargin/**","/home/**","/cashbackTransaction/**","/mobileProductsList/**","/mobileSingleProduct/**","/account/**", "/uploadPostsWithImage/**", "/merchantForm/**", "/delete/**", "/transaction/**",
                        "/uploadForm.html")
                .permitAll().anyRequest().authenticated().and().formLogin().loginPage("/login").successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)/*.defaultSuccessUrl("/", false).failureUrl("/login?error")*/
                .usernameParameter("username").passwordParameter("password").permitAll().and().logout().logoutSuccessUrl("/").and()
                .exceptionHandling().accessDeniedPage("/403").and().csrf().disable();

    }

    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordencoder() {
        return new BCryptPasswordEncoder();
    }
}