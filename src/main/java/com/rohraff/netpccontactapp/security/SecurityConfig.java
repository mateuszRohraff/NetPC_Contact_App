package com.rohraff.netpccontactapp.security;

import com.rohraff.netpccontactapp.services.AuthenticationService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationService authenticationService;

    public SecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(this.authenticationService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*Definiujemy niezabezpieczone endpointy.
         Moduły css i js muszą być dodane w celu poprawnego generowania widoku przez thymelaf*/
        http.authorizeRequests()
                .antMatchers("/signup", "/home", "/css/**", "/js/**").permitAll()
                .anyRequest().authenticated();
        /*Definiujemy endpoint użyty do autoryzacji poprzez Spring Security
        * oraz definiujemy zachowanie aplikacji po zalogowaniu i wylogowaniu.*/
        http.formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/home")
                .permitAll();

        http.formLogin()
                .defaultSuccessUrl("/home", true);
    }
}
