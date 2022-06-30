package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.client.model.FeaturePageResponse;
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

import javax.persistence.Tuple;
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
        response.setContext(featureRepository.findAllDto());
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

    private MenuTransformedResponseContext transform(List<Tuple> rs) {
        MenuTransformedResponseContext context = new MenuTransformedResponseContext();
        final Map<Long, GroupMenuFeatureDto> groupMenuFeatureDtoMap = new HashMap<>();
        List<FeatureDto> standaloneFeatureList = new ArrayList<>();
        for (Tuple tuple : rs) {
            Object gIdObj = tuple.get("gId");
            if (gIdObj == null) {
                standaloneFeatureList.add(objToFeature(tuple));
            } else {
                Long gId = ((Number) gIdObj).longValue();
                GroupMenuFeatureDto groupMenuFeatureDto = groupMenuFeatureDtoMap.get(gId);
                if (groupMenuFeatureDto == null) {
                    groupMenuFeatureDto = GroupMenuFeatureDto.builder()
                            .id(gId)
                            .label((String) tuple.get("gLabel"))
                            .icon((String) tuple.get("gIcon"))
                            .orderValue((Integer) tuple.get("gOrderValue"))
                            .build();
                }
                groupMenuFeatureDto.addFeaturesItem(objToFeature(tuple));
                groupMenuFeatureDtoMap.putIfAbsent(groupMenuFeatureDto.getId(), groupMenuFeatureDto);
            }
        }
        context.setGroups(new ArrayList<>(groupMenuFeatureDtoMap.values()));
        context.setStandalone(standaloneFeatureList);
        return context;
    }

    private FeatureDto objToFeature(Tuple tuple) {

        return FeatureDto.builder()
                .id(tuple.get("id") == null ? null : ((Number) tuple.get("id")).longValue())
                .label((String) tuple.get("label"))
                .icon((String) tuple.get("icon"))
                .routerLink((String) tuple.get("routerLink"))
                .hasChildren((Boolean) tuple.get("hasChildren"))
                .parent(tuple.get("parent") == null ? null : ((Number) tuple.get("parent")).longValue())
                .showInMenu((Boolean) tuple.get("showInMenu"))
                .orderValue((Integer) tuple.get("orderValue"))
                .build();
    }

    @Override
    public FeaturePageResponse getFeaturePaging(Long id, Integer limit) {
        FeaturePageResponse response = new FeaturePageResponse();
        response.setResult(ApiResult.OK);
        if (id == null || limit == null) {
            response.setResult(ApiResult.BAD);
        } else {
            List<FeatureDto> featureDtos = featureRepository.fetchWithKeysetPagination(id, limit + 1);
            if (featureDtos.size() == (limit + 1)) {
                featureDtos.remove(featureDtos.size() - 1);
                response.setContext(featureDtos);
                response.setLast(false);
            } else {
                response.setContext(featureDtos);
                response.setLast(true);
            }
        }
        return response;
    }

    @Override
    public FeatureResponse getFeaturesByRole(String roleId) {
        FeatureResponse response = new FeatureResponse();
        response.setResult(ApiResult.OK);
        response.setContext(featureRepository.findByRole(roleId));
        return response;
    }
}
