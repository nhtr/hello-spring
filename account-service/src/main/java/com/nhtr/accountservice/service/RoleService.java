package com.nhtr.accountservice.service;

import com.nhtr.accountservice.client.model.RoleResponse;
import com.nhtr.accountservice.client.model.SaveRoleFeatureRequest;
import com.nhtr.accountservice.client.model.SaveRoleFeatureResponse;

public interface RoleService {
    RoleResponse getRoles();
    RoleResponse getRolesByFeatures(String featureId);
    SaveRoleFeatureResponse saveRoleFeature(SaveRoleFeatureRequest saveRoleFeatureRequest);
}
