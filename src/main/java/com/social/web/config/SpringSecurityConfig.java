package com.social.web.config;

import com.social.middleware.service.ApplicationUserDetailsService;
import com.social.middleware.service.SimpleSocialUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * Created by mithundas on 11/28/14.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;


    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                //Spring Security ignores request to static resources such as CSS or JS files.
                .ignoring()
                .antMatchers("/static/**");
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("USER");
        //auth.inMemoryAuthentication().withUser("user").password("user").roles("ADMIN");
//        auth.jdbcAuthentication().dataSource(dataSource)
//                .usersByUsernameQuery(
//                        "select email,password, version from user_accounts where email=?")
//                .authoritiesByUsernameQuery(
//                        "select email, role from user_accounts where email=?");

        auth.userDetailsService(userDetailsService());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //Configures form login
                .formLogin()
                .loginPage("/login")
                //.loginProcessingUrl("/j_spring_security_check")
                .loginProcessingUrl("/login/authenticate")
                .failureUrl("/login?error=bad_credentials")
                .usernameParameter("email").passwordParameter("password")
                                //Configures the logout function
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                        //Configures url based authorization
                .and()
                .exceptionHandling().accessDeniedPage("/403")

                .and()
                .authorizeRequests()
                        //Anyone can access the urls
                .antMatchers(
                        "/auth/**",
                        "/static/**",
                        "/login",
                        "/logout",
                        "/signup/**",
                        "/user/register/**"
                ).permitAll()
                //The rest of the our application is protected.
                .antMatchers("/**").hasRole("USER")
                .and()
                        //Adds the SocialAuthenticationFilter to Spring Security's filter chain.
                        .apply(new SpringSocialConfigurer())

                .and()
                .csrf().disable();

    }


    /**
     * This bean is used to load the user specific data when social sign in
     * is used.
     */
    @Bean
    public SocialUserDetailsService socialUserDetailsService() {
        return new SimpleSocialUserDetailsService(userDetailsService());
    }

    /**
     * This bean is load the user specific data when form login is used.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return new ApplicationUserDetailsService();
    }
}
