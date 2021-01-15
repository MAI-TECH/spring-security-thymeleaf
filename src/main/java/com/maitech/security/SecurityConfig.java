package com.maitech.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        return daoAuthenticationProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //  H2 Console
        http.csrf().ignoringAntMatchers("/h2console/**");
        http.headers().frameOptions().sameOrigin();

        //  Authorisations
        http.authorizeRequests().antMatchers("/signIn", "/signUp").permitAll();
        http.authorizeRequests().antMatchers("/user/**").hasAnyRole("USER", "ADMIN").anyRequest();
        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN").anyRequest();

        //  Authentication
        http.formLogin().loginProcessingUrl("/signIn").loginPage("/signIn").permitAll().usernameParameter("username").passwordParameter("password");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/signOut")).logoutSuccessUrl("/signIn");
        http.rememberMe().tokenValiditySeconds(2592000).key("$2y$12$OCu861NixlpTdrF.7wJAouCy08QvEbevMDb/1F5QEXkWlUEyvA7DG").rememberMeParameter("checkRememberMe");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
