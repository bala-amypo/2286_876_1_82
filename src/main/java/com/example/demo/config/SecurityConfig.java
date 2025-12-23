// package com.example.demo.config;

// import com.example.demo.security.JwtAuthenticationFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

// @Configuration
// @EnableWebSecurity
// public class SecurityConfig {

//     private final JwtAuthenticationFilter jwtAuthenticationFilter;

//     public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
//         this.jwtAuthenticationFilter = jwtAuthenticationFilter;
//     }

//     @Bean
//     public PasswordEncoder passwordEncoder() {
//         return new BCryptPasswordEncoder();
//     }

//     @Bean
//     public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//         http.csrf().disable()
//             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             .and()
//             .authorizeRequests()
//                 .antMatchers("/auth/**").permitAll()
//                 .antMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
//                 .antMatchers("/api/employees/**").authenticated()
//                 .antMatchers("/api/metrics/**").authenticated()
//                 .antMatchers("/api/anomaly-rules/**").authenticated()
//                 .antMatchers("/api/anomalies/**").authenticated()
//                 .antMatchers("/api/team-summaries/**").authenticated()
//                 .antMatchers("/api/credentials/**").authenticated()
//                 .anyRequest().authenticated()
//             .and()
//             .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

//         return http.build();
//     }
// }


package com.example.demo.config;

import com.example.demo.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF (JWT based stateless API)
            .csrf(csrf -> csrf.disable())

            // Stateless session
            .sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            // Authorization rules
            .authorizeHttpRequests(auth -> auth
                // PUBLIC ENDPOINTS
                .requestMatchers(
                        "/auth/register",
                        "/auth/login",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                // PROTECTED API ENDPOINTS
                .requestMatchers(
                        "/api/employees/**",
                        "/api/metrics/**",
                        "/api/anomaly-rules/**",
                        "/api/anomalies/**",
                        "/api/team-summaries/**",
                        "/api/credentials/**"
                ).authenticated()

                // Everything else
                .anyRequest().authenticated()
            )

            // Add JWT filter
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
            )

            // Default security headers
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
