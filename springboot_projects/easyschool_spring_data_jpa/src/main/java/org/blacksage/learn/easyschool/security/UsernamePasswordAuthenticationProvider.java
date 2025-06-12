package org.blacksage.learn.easyschool.security;

import lombok.RequiredArgsConstructor;
import org.blacksage.learn.easyschool.models.Person;
import org.blacksage.learn.easyschool.models.Role;
import org.blacksage.learn.easyschool.repositories.PersonRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("prod")
@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider
        implements AuthenticationProvider {

        private final PersonRepository personRepository;
        private final PasswordEncoder passwordEncoder;

        @Override
        public Authentication authenticate(Authentication authentication)
                throws AuthenticationException {

                String email = authentication.getName();
                String password = authentication.getCredentials().toString();

                Person person =
                        personRepository
                                .findByEmail(email)
                                .orElseThrow(() ->
                                        new BadCredentialsException(
                                                "Username or password is invalid"
                                        )
                                );

                if (!passwordEncoder.matches(password, person.getPassword())) {
                        throw new BadCredentialsException(
                                "Username or password is invalid"
                        );
                }

                return new UsernamePasswordAuthenticationToken(
                        email,
                        null,
                        getGrantedAuthorities(person.getRole())
                );
        }

        @Override
        public boolean supports(Class<?> authentication) {
                return
                        authentication.equals(
                                UsernamePasswordAuthenticationToken.class
                        );
        }

        private List<GrantedAuthority> getGrantedAuthorities(Role role) {
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(
                        // Spring security requires a prefix of ROLE_
                        new SimpleGrantedAuthority("ROLE_" + role.getName())
                );
                return grantedAuthorities;
        }
}
