# Enable H2 Console

```java

    // spring security disables frames, but h2 uses frames
    http.headers(httpSecurityHeadersConfigurer ->
                httpSecurityHeadersConfigurer
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
    );

    // The "connect" button is a form submit button.
    // It's ok to disable csrf because we're using h2 for development purpose only.
    http.csrf(httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer
                        .ignoringRequestMatchers(PathRequest.toH2Console())
    );
    
    // Don't need login to access /h2-console.
    // Again, only because we're using h2 for development only.
    http.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
    );
    
```
