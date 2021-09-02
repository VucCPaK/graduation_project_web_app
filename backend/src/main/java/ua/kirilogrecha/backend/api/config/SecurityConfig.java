package ua.kirilogrecha.backend.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/**").permitAll()
                    .anyRequest().fullyAuthenticated()
                    .and()
                .oauth2Login()
                    .defaultSuccessUrl("http://localhost:8082/")
                    .and()
                .formLogin()
                    .loginPage("http://localhost:8082/sign_in")
                    .loginProcessingUrl("/perform-login")
                    .usernameParameter("username")
                    .passwordParameter("password")
                    .defaultSuccessUrl("http://localhost:8082/")
                    .failureUrl("http://localhost:8082/sign_in")
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .and()
                .csrf()
                    .disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .ldapAuthentication()
                    .userDnPatterns("login={0},ou=users")
                    .contextSource()
                        .url("ldap://localhost:8084/dc=embedded,dc=ldap")
                        .and()
                .passwordCompare()
                    .passwordEncoder(new BCryptPasswordEncoder())
                    .passwordAttribute("userPassword");
    }
}