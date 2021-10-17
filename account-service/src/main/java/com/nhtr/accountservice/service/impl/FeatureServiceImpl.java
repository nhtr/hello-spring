package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.GroupMenuFeatureDto;
import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.client.model.MenuTransformedResponse;
import com.nhtr.accountservice.client.model.MenuTransformedResponseContext;
import com.nhtr.accountservice.mapper.FeatureGroupDtoMapper;
import com.nhtr.accountservice.properties.KeycloakProperties;
import com.nhtr.accountservice.repository.FeatureRepository;
import com.nhtr.accountservice.repository.GroupMenuRepository;
import com.nhtr.accountservice.service.AuthenticationFacade;
import com.nhtr.accountservice.service.FeatureService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;
    private final Keycloak keycloak;
    private final KeycloakProperties keycloakProperties;
    private final AuthenticationFacade authenticationFacade;
    private final FeatureGroupDtoMapper featureGroupDtoMapper;
    private final GroupMenuRepository groupMenuRepository;

    @Override
    @Transactional(readOnly = true)
    public FeatureResponse getFeatures() {
        FeatureResponse response = new FeatureResponse();
        response.setResult(ApiResult.OK);
        response.setContext(featureRepository.findAllAndMapToDto());
        return response;
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    public MenuTransformedResponse getMenuTransformed() {
        MenuTransformedResponse response = new MenuTransformedResponse();
        response.setResult(ApiResult.OK);
        String[] roles = keycloak.realm(keycloakProperties.getRealm())
                .users().get(authenticationFacade.getUserId())
                .roles().clientLevel(keycloakProperties.getIdWebSpa()).listEffective().stream().map(RoleRepresentation::getName)
                .toArray(String[]::new);
        response.setContext(this.transform(groupMenuRepository.findWithFeatureByRoles(roles)));
        return response;
    }

    private MenuTransformedResponseContext transform(List<Object[]> rs) {
        MenuTransformedResponseContext context = new MenuTransformedResponseContext();
        final Map<Long, GroupMenuFeatureDto> groupMenuFeatureDtoMap = new HashMap<>();
        List<FeatureDto> standaloneFeatureList = new ArrayList<>();
        for (Object[] o : rs) {
            if (o[0] == null) {
                standaloneFeatureList.add(objToFeature(o));
            } else {
                Long gId = ((Number) o[0]).longValue();
                GroupMenuFeatureDto groupMenuFeatureDto = groupMenuFeatureDtoMap.get(gId);
                if (groupMenuFeatureDto == null) {
                    groupMenuFeatureDto = GroupMenuFeatureDto.builder()
                            .id(gId)
                            .label((String) o[1])
                            .icon((String) o[2])
                            .orderValue((Integer) o[3])
                            .build();
                }
                groupMenuFeatureDto.addFeaturesItem(objToFeature(o));
                groupMenuFeatureDtoMap.putIfAbsent(groupMenuFeatureDto.getId(), groupMenuFeatureDto);
            }
        }
        context.setGroups(new ArrayList<>(groupMenuFeatureDtoMap.values()));
        context.setStandalone(standaloneFeatureList);
        return context;
    }

    private FeatureDto objToFeature(Object[] o) {
        return FeatureDto.builder()
                .id((Long) o[4])
                .label((String) o[5])
                .icon((String) o[6])
                .routerLink((String) o[7])
                .hasChildren((Boolean) o[8])
                .parent((Long) o[9])
                .showInMenu((Boolean) o[10])
                .orderValue((Integer) o[11])
                .build();
    }
}
