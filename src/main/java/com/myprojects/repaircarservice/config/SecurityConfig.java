package com.myprojects.repaircarservice.config;

import com.myprojects.repaircarservice.service.CarServiceDetailsService;
import com.myprojects.repaircarservice.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final CarServiceDetailsService carServiceDetailsService;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsServiceImpl, CarServiceDetailsService carServiceDetailsService) {
        this.userDetailsServiceImpl = userDetailsServiceImpl;
        this.carServiceDetailsService = carServiceDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authProviderUser() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public DaoAuthenticationProvider authProviderService() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(carServiceDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }




    @Bean
    public SecurityFilterChain userSecurityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/user/**")
                .authorizeRequests()
                .antMatchers("/user/user-menu/**").hasRole("USER")
                .antMatchers("/error").hasRole("USER")
                .antMatchers("/user/registration-user").permitAll()
                .antMatchers("/user/login-user", "/").anonymous()
                .anyRequest()
                .authenticated()

                .and()
                    .userDetailsService(userDetailsServiceImpl)
                    .formLogin()
                    .loginPage("/user/login-user")
                    .loginProcessingUrl("/user/process_login_user")
                    .defaultSuccessUrl("/user/user-menu", true)
                    .failureUrl("/user/login-user?error")

                .and()
                    .logout()
                    .logoutUrl("/user/logout_user")
                    .logoutSuccessUrl("/")

                .and()
                    .authenticationProvider(authProviderUser());
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain carServiceSecurityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/service/**")
                .authorizeRequests()
                .antMatchers("/service/service-menu/**").hasRole("SERVICE")
                .antMatchers( "/error").hasRole("SERVICE")
                .antMatchers("/service/registration-service").permitAll()
                .antMatchers("/service/login-service", "/").anonymous()
                .anyRequest()
                .authenticated()

                .and()
                    .userDetailsService(carServiceDetailsService)
                    .formLogin()
                    .loginPage("/service/login-service")
                    .loginProcessingUrl("/service/process_login_service")
                    .defaultSuccessUrl("/service/service-menu", true)
                    .failureUrl("/service/login-service?error")

                .and()
                    .logout()
                    .logoutUrl("/service/logout_service")
                    .logoutSuccessUrl("/")

                .and()
                    .authenticationProvider(authProviderService());
        return http.build();
    }

}
