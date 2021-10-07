package com.nhtr.accountservice.config;

import com.nhtr.commonmodule.constant.AuthConst;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

public class RestTemplateAuthHeaderInterceptor implements ClientHttpRequestInterceptor {

    @Override
    @NonNull
    public ClientHttpResponse intercept(
            HttpRequest request,
            @NonNull byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toString();
        request.getHeaders().add(AuthConst.USER_ID_HEADER, authentication.getName());
        request.getHeaders().add(AuthConst.AUTH_HEADER, authorities);
        return execution.execute(request, body);
    }


}
