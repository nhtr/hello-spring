package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.mapper.FeatureGroupDtoMapper;
import com.nhtr.accountservice.properties.KeycloakProperties;
import com.nhtr.accountservice.repository.FeatureRepository;
import com.nhtr.accountservice.service.AuthenticationFacade;
import com.nhtr.accountservice.service.FeatureService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;
    private final AuthenticationFacade authenticationFacade;
    private final FeatureGroupDtoMapper featureGroupDtoMapper;

    @Override
    public FeatureResponse getFeatures() {
        FeatureResponse response = new FeatureResponse();
        response.setResult(ApiResult.OK);
        response.setContext(featureRepository.findAllAndMapToDto());
        return response;
    }

    @Override
    public MenuResponse getMenu() {
        MenuResponse response = new MenuResponse();
        response.setResult(ApiResult.OK);
        String[] roles = keycloak.realm(keycloakProperties.getRealm())
                .users().get(authenticationFacade.getUserId())
                .roles().clientLevel(keycloakProperties.getIdWebSpa()).listEffective().stream().map(RoleRepresentation::getName)
                .toArray(String[]::new);
        response.setContext(featureRepository.findByRoles(roles)
                .stream()
                .map(featureGroupDtoMapper::toDTO)
                .collect(Collectors.toList())
        );
        return response;
    }
}
