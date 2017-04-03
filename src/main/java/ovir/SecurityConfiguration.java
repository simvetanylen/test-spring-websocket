package ovir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ovir.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable().antMatcher("/**")
                .addFilter(new AuthenticationProcessingFilter(userRepository))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        /**
         * new AuthenticationManager() {
         public Authentication authenticate(Authentication authentication) throws AuthenticationException {
         if (authentication.isAuthenticated()) {
         GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");
         Collection<GrantedAuthority> authorities = new ArrayList<>();
         authorities.add(authority);
         return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), authorities);
         }
         return authentication;
         }
         },
         */
    }
}
