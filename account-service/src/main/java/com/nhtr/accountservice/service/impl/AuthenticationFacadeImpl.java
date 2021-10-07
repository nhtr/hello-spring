package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.service.AuthenticationFacade;
import com.nhtr.commonmodule.constant.AuthConst;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getUserId() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    @Override
    public ExchangeFilterFunction setAuthHeader() {
        return (request, next) -> next.exchange(ClientRequest.from(request).headers((headers) -> {
            headers.set(AuthConst.USER_ID_HEADER, getUserId());
            headers.set(AuthConst.AUTH_HEADER, getAuthentication().getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .toString());
        }).build());
    }
}
