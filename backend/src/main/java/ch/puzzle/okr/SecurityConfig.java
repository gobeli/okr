package ch.puzzle.okr;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter;

//@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    @Order(1) // Must be First order! Otherwise unauthorized Requests are sent to Controllers
    public SecurityFilterChain apiSecurityFilterChain(HttpSecurity http) throws Exception {
        logger.debug("*** apiSecurityFilterChain reached");
        return http.antMatcher("/api/**")
                .authorizeRequests(authorizeRequests -> authorizeRequests.anyRequest().authenticated())
                .exceptionHandling(e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt).build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain securityHeadersFilter(HttpSecurity http) throws Exception {
        logger.debug("*** SecurityHeader reached");
        return http.headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin)
                .referrerPolicy(referrer -> referrer.policy(ReferrerPolicyHeaderWriter.ReferrerPolicy.SAME_ORIGIN))
                .permissionsPolicy(
                        permissions -> permissions.policy("accelerometer=(), ambient-light-sensor=(), autoplay=(), "
                                + "battery=(), camera=(), cross-origin-isolated=(), display-capture=(), document-domain=(), encrypted-media=(), "
                                + "execution-while-not-rendered=(), execution-while-out-of-viewport=(), fullscreen=(),"
                                + " geolocation=(), gyroscope=(), keyboard-map=(), magnetometer=(), microphone=(), "
                                + "midi=(), navigation-override=(), payment=(), picture-in-picture=(),"
                                + " publickey-credentials-get=(), screen-wake-lock=(), sync-xhr=(self), "
                                + "usb=(), web-share=(), xr-spatial-tracking=()"))
                .and().xssProtection().block(false).and().httpStrictTransportSecurity().includeSubDomains(true)
                .preload(true).maxAgeInSeconds(31536000)).build();
    }
}