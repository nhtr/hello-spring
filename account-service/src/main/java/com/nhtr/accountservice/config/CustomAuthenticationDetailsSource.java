package com.nhtr.accountservice.config;

import com.nhtr.commonmodule.constant.AuthConst;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Collectors;

public class CustomAuthenticationDetailsSource implements AuthenticationDetailsSource<HttpServletRequest, GrantedAuthoritiesContainer> {

    @Override
    public GrantedAuthoritiesContainer buildDetails(HttpServletRequest context) {
        Enumeration<String> headerValues = context.getHeaders(AuthConst.AUTH_HEADER);
        Collection<GrantedAuthority> authorities = Collections.list(headerValues)
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new PreAuthenticatedGrantedAuthoritiesWebAuthenticationDetails(context, authorities);
    }

}
