package dev.barbz.subscriptionsyscore.infrastructure.config;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

/**
 * Security configuration class.
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * Define XSS (Cross-Site Scripting) protection, and define CORS protection.
     *
     * @param http http security object
     * @throws Exception generic exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .configurationSource(request -> {
                    CorsConfiguration corsConfiguration = new CorsConfiguration();
                    // Set allowed methods
                    corsConfiguration.addAllowedMethod(HttpMethod.GET);
                    corsConfiguration.addAllowedMethod(HttpMethod.POST);
                    corsConfiguration.addAllowedMethod(HttpMethod.DELETE);
                    // Set allowed origins
                    corsConfiguration.addAllowedOrigin("http://localhost:8080");
                    corsConfiguration.addAllowedOrigin("http://subscription-sys-bff:8080");
                    return corsConfiguration;
                })
                .and()
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
