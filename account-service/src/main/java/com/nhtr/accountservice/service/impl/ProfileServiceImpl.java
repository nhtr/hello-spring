package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.LogoutRequest;
import com.nhtr.accountservice.client.model.LogoutResponse;
import com.nhtr.accountservice.client.model.UserProfile;
import com.nhtr.accountservice.client.model.UserProfileResponse;
import com.nhtr.accountservice.properties.KeycloakProperties;
import com.nhtr.accountservice.service.AuthenticationFacade;
import com.nhtr.accountservice.service.ProfileService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;
    private final AuthenticationFacade authenticationFacade;

    @Override
    public UserProfileResponse getProfile() {
        UserProfileResponse response = new UserProfileResponse();
        response.setResult(ApiResult.OK);
        UserRepresentation userRepresentation = keycloak.realm(keycloakProperties.getRealm())
                .users().get(authenticationFacade.getUserId()).toRepresentation();
        response.setContext(
                new UserProfile(
                        userRepresentation.getId(),
                        userRepresentation.getUsername(),
                        userRepresentation.getFirstName(),
                        userRepresentation.getLastName(),
                        ""
                )
        );
        return response;
    }

    @Override
    public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse response = new LogoutResponse();
        response.setResult(ApiResult.OK);
        // required add manage-users at clientRoles - realmManagement
        // navigate to clients-client name-account service roles in keycloak
        keycloak.realm(keycloakProperties.getRealm())
                .users().get(request.getUsername()).logout();
        return response;
    }
}
