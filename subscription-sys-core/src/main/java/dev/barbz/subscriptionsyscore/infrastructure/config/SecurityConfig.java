package dev.barbz.subscriptionsyscore.infrastructure.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Security configuration class.
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Define XSS (Cross-Site Scripting) protection.
     *
     * @param http http security object
     * @throws Exception generic exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .xssProtection()
                .and()
                .contentSecurityPolicy("script-src 'self'");
    }

    /**
     * Disable auth security for all request.
     *
     * @param web web security.
     */
    @Override
    public void configure(WebSecurity web) {
        // Disable auth security for all request
        web.ignoring().antMatchers("/**");
    }
}
