package com.nhtr.accountservice.controller;

import com.nhtr.accountservice.client.RoleApi;
import com.nhtr.accountservice.client.model.RoleResponse;
import com.nhtr.accountservice.client.model.SaveRoleFeatureRequest;
import com.nhtr.accountservice.client.model.SaveRoleFeatureResponse;
import com.nhtr.accountservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RoleController implements RoleApi {

    private final RoleService roleService;

    @Override
    public ResponseEntity<RoleResponse> getRoles() {
        return ResponseEntity.ok(roleService.getRoles());
    }

    @Override
    public ResponseEntity<RoleResponse> getRolesByFeature(Long featureId) {
        return null;
    }

    @Override
    public ResponseEntity<SaveRoleFeatureResponse> postSaveRoleFeature(SaveRoleFeatureRequest saveRoleFeatureRequest) {
        return ResponseEntity.ok(roleService.saveRoleFeature(saveRoleFeatureRequest));
    }
}
