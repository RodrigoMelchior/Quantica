package br.com.web.pesquisas.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {
    

    private PasswordEncoder passwordEncoder;

    private UserDetailsService userDetailsService;

    @Autowired
    public CustomAuthenticationProvider(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UsernamePasswordAuthenticationToken token =
                (UsernamePasswordAuthenticationToken) authentication;

        String login = token.getName();
        UserDetails user = userDetailsService.loadUserByUsername(login);
        if (user == null) {
            throw new UsernameNotFoundException("User does not exists");
        }
        String password = user.getPassword();
        String tokenPassword = (String) token.getCredentials();
        if (!passwordEncoder.matches(tokenPassword, password)) {
            throw new BadCredentialsException("Invalid username/password");
        }
        return new UsernamePasswordAuthenticationToken(user, password,
            user.getAuthorities());
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}