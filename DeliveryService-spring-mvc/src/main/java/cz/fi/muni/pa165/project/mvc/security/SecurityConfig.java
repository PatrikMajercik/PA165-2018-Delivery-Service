package cz.fi.muni.pa165.project.mvc.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.inject.Inject;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    @Inject
    private Authenticator authenticationProvider;

    /**
     * Inject our custom authentication provider.
     */
    @Inject
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        log.debug("Registering authentication provider");
        auth.authenticationProvider(authenticationProvider);
    }


    /**
     * Configure URLs.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/article/new/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/article/edit/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/article/delete/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/article/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/delivery/new/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/delivery/edit/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/delivery/delete/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/delivery/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/person/new/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/person/edit/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/person/delete/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/person/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .antMatchers("/address/new/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/address/edit/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/address/delete/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/address/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/j_spring_security_check")
                .failureUrl("/login?error=invalidLoginAttempt")
                .usernameParameter("user").passwordParameter("pass")
                .and()
                .logout().logoutSuccessUrl("/")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .csrf().disable();

    }
}