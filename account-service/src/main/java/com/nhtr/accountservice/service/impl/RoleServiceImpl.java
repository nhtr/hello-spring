package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.RoleDto;
import com.nhtr.accountservice.client.model.RoleResponse;
import com.nhtr.accountservice.client.model.SaveRoleFeatureRequest;
import com.nhtr.accountservice.client.model.SaveRoleFeatureResponse;
import com.nhtr.accountservice.entity.RoleFeature;
import com.nhtr.accountservice.properties.KeycloakProperties;
import com.nhtr.accountservice.repository.RoleFeatureRepository;
import com.nhtr.accountservice.service.RoleService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;

    private final RoleFeatureRepository roleFeatureRepository;

    @Override
    public RoleResponse getRoles() {
        RoleResponse response = new RoleResponse();
        response.setResult(ApiResult.OK);
        // required add manage-clients at clientRoles - realmManagement
        // navigate to clients - clientName - accountServiceRoles in keycloak
        List<RoleDto> roleDtoList = keycloak.realm(keycloakProperties.getRealm())
                .clients().get(keycloakProperties.getIdWebSpa())
                .roles().list()
                .stream().map(item -> new RoleDto(item.getId(), item.getClientRole(), item.getName(), item.getDescription()))
                .collect(Collectors.toList());
        response.setContext(roleDtoList);
        return response;
    }

    @Override
    public RoleResponse getRolesByFeatures(String featureId) {
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public SaveRoleFeatureResponse saveRoleFeature(SaveRoleFeatureRequest saveRoleFeatureRequest) {
        SaveRoleFeatureResponse response = new SaveRoleFeatureResponse();
        response.setResult(ApiResult.EXCEPTION);
        roleFeatureRepository.deleteByRoleId(saveRoleFeatureRequest.getRole().getId());
        List<RoleFeature> roleFeatures = saveRoleFeatureRequest.getFeatures().stream().map(item -> new RoleFeature(
                saveRoleFeatureRequest.getRole().getId(),
                item.getId()
        )).collect(Collectors.toList());
        roleFeatureRepository.saveAll(roleFeatures);
        response.setResult(ApiResult.OK);
        return response;
    }
}
