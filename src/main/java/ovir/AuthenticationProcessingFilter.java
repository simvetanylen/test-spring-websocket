package ovir;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.web.filter.GenericFilterBean;
import ovir.entity.User;
import ovir.repository.UserRepository;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by psygnosis on 02/04/17.
 */
public class AuthenticationProcessingFilter extends AbstractPreAuthenticatedProcessingFilter {

    private UserRepository userRepository;

    public AuthenticationProcessingFilter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public AuthenticationProcessingFilter(AuthenticationManager authenticationManager, UserRepository userRepository) {
//        this.setAuthenticationManager(authenticationManager);
//        this.userRepository = userRepository;
//    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        String login = httpRequest.getHeader("X-Access-Login");
        String password = httpRequest.getHeader("X-Access-Password");

        User user = userRepository.findByLogin(login);

        if(user != null && user.getPassword().equals(password)) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user.getId(), user.getLogin(), authorities);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
        return null;
    }

//    @Override
//    protected Object getPreAuthenticatedPrincipal(HttpServletRequest httpServletRequest) {
//
//        String login = httpServletRequest.getHeader("X-Access-Login");
//        String password = httpServletRequest.getHeader("X-Access-Password");
//
//        User user = userRepository.findByLogin(login);
//
//        if(user != null && user.getPassword().equals(password)) {
//            return user.getId();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    protected Object getPreAuthenticatedCredentials(HttpServletRequest httpServletRequest) {
//        return "";
//    }
}
