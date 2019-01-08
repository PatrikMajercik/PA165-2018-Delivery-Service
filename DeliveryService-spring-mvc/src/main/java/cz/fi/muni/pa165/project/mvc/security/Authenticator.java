package cz.fi.muni.pa165.project.mvc.security;

import cz.muni.fi.pa165.project.dto.PersonAuthenticateDTO;
import cz.muni.fi.pa165.project.dto.PersonDTO;
import cz.muni.fi.pa165.project.facade.PersonFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class Authenticator implements AuthenticationProvider {

    @Autowired
    private PersonFacade userFacade;

    /**
     * Implementation of authenticate that checks user credentials.
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        PersonDTO user = null;

        try {
            user = userFacade.findPersonByEmail(email);
        } catch (Exception e) {
            return null;
        }
        PersonAuthenticateDTO authData = new PersonAuthenticateDTO();
        authData.setPersonId(user.getId());
        authData.setEmail(email);
        authData.setPassword(password);

        if (userFacade.authenticate(authData)) {
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            //System.out.println(user.getId().toString());
            if (userFacade.isAdmin(userFacade.findById(user.getId()))) {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            } else {
                grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }
            return new UsernamePasswordAuthenticationToken(email, password, grantedAuthorities);
        }
        return null;
    }

    /**
     * Supports method determines whether our authentication provider can authenticate user with given token.
     */
    @Override
    public boolean supports(Class<?> authClass) {
        return (UsernamePasswordAuthenticationToken.class
                .isAssignableFrom(authClass));
    }
}
