package org.blacksage.learn.easyschool.config;

import org.blacksage.learn.easyschool.constants.RoleNameConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

        @Bean
        SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

                // Enable csrf.
                // Ignore csrf on /saveMsg because it's a public form.
                http.csrf(httpSecurityCsrfConfigurer ->
                        httpSecurityCsrfConfigurer
                                .ignoringRequestMatchers("/saveMsg")
                                .ignoringRequestMatchers("/public/**")
                                // It's React/Angular headache.
                                .ignoringRequestMatchers("/api/**")
                                .ignoringRequestMatchers("/data-api/**")
                );

                // Permit All
                http.authorizeHttpRequests(authorizeHttpRequests ->
                        authorizeHttpRequests
                                .requestMatchers(
                                        "/", "/home",
                                        "/courses",
                                        "/about",
                                        "/holidays/**",
                                        "/contact",
                                        "/assets/**",
                                        "/saveMsg",
                                        "/login",
                                        "/logout",
                                        "/public/**"
                                )
                                .permitAll()
                );

                // Any Authentication accepted
                http.authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(
                                        "/dashboard",
                                        "/displayProfile",
                                        "/updateProfile",
                                        "/api/**",
                                        "/data-api/**"
                                )
                                .authenticated()
                );

                // ADMIN Authorization required
                http.authorizeHttpRequests((requests) ->
                        requests
                                .requestMatchers(
                                        "/displayMessages/**",
                                        "/closeMsg/**",
                                        "/admin/**",
                                        "/easyschool/actuator/**"
                                )
                                .hasRole(RoleNameConstants.ADMIN)
                );

                // STUDENT Authorization required
                http.authorizeHttpRequests((requests) -> {
                                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                                if (authentication != null) {
                                        System.out.println("Incoming role: " + authentication.getAuthorities());
                                }

                                requests
                                        .requestMatchers("/student/**")
                                        .hasRole(RoleNameConstants.STUDENT);
                        }

                );

                // Spring Security integrated with Thymeleaf
                http
                        .formLogin(formLoginCustomizer ->
                                formLoginCustomizer
                                        .loginPage("/login")
                                        .defaultSuccessUrl("/dashboard")
                                        .failureUrl("/login?error=true")
                                        .permitAll()
                        )
                        .httpBasic(withDefaults());

                return http.build();
        }

        @Bean
        public PasswordEncoder bcryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }
}
