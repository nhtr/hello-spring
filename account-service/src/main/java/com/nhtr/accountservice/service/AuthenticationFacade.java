package com.nhtr.accountservice.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;

public interface AuthenticationFacade {

    /**
     * get Authentication from SecurityContextHolder
     * */
    Authentication getAuthentication();

    /**
     * get user id
     * */
    String getUserId();

    /**
     * set auth header
     * */
    ExchangeFilterFunction setAuthHeader();
}
