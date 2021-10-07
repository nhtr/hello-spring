package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.client.model.GroupMenuDto;
import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.client.model.MenuResponseContext;
import com.nhtr.accountservice.mapper.FeatureDtoMapper;
import com.nhtr.accountservice.properties.KeycloakProperties;
import com.nhtr.accountservice.repository.FeatureRepository;
import com.nhtr.accountservice.repository.GroupMenuRepository;
import com.nhtr.accountservice.service.AuthenticationFacade;
import com.nhtr.accountservice.service.GroupMenuService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class GroupMenuServiceImpl implements GroupMenuService {

    private final GroupMenuRepository groupMenuRepository;
    private final FeatureRepository featureRepository;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;
    private final AuthenticationFacade authenticationFacade;
    private final FeatureDtoMapper featureDtoMapper;

    @Override
    public MenuResponse getMenu() {
        MenuResponse response = MenuResponse
                .builder()
                .result(ApiResult.OK)
                .build();
        String[] roles = keycloak.realm(keycloakProperties.getRealm())
                .users().get(authenticationFacade.getUserId())
                .roles().clientLevel(keycloakProperties.getIdWebSpa()).listEffective().stream().map(RoleRepresentation::getName)
                .toArray(String[]::new);
        List<GroupMenuDto> groupMenuDtoList = groupMenuRepository.findAll()
                .stream()
                .map(groupMenu -> GroupMenuDto.builder()
                        .id(groupMenu.getId())
                        .label(groupMenu.getLabel())
                        .icon(groupMenu.getIcon())
                        .features(groupMenu.getFeatures().stream().map(featureDtoMapper::toDTO).collect(Collectors.toList()))
                        .build())
                .collect(Collectors.toList());
        List<FeatureDto> featureNoneGroup = featureRepository.findFeatureByGroupNullAndRoles(roles);
        response.setContext(new MenuResponseContext(groupMenuDtoList, featureNoneGroup));
        return response;
    }
}
