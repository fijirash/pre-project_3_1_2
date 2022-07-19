package ru.fijirash.pp.spring_security.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.fijirash.pp.spring_security.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userService;
    private final SuccessUserHandler successUserHandler;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userService, SuccessUserHandler successUserHandler) {
        this.userService = userService;
        this.successUserHandler = successUserHandler;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(getPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/admin/**").hasRole("ADMIN")
                    .antMatchers("/user/**").hasAnyRole("ADMIN", "USER")
                    .antMatchers("/login").permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .successHandler(successUserHandler)
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .permitAll()
                    .failureUrl("/login?error")
                .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
