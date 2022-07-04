package org.winterframework.dashboard.security.core;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.winterframework.dashboard.security.permission.UserRole;

import java.util.Collection;

public class JwtAuthenticationToken implements Authentication {


    private final JwtUserDetails userDetails;

    public JwtAuthenticationToken(Claims claims) {
        userDetails = new JwtUserDetails(claims);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        // NOT SUPPORTED
        return null;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public JwtUserDetails getPrincipal() {
        return userDetails;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        // NOT SUPPORTED
    }

    @Override
    public String getName() {
        return userDetails.getUsername();
    }

    public boolean hasRole(UserRole role) {
        return getAuthorities().stream().anyMatch(authority -> authority.getAuthority().equals(role.code()));
    }
}
