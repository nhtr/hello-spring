package com.nhtr.accountservice.controller;

import com.nhtr.accountservice.client.FeatureApi;
import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.service.FeatureService;
import com.nhtr.accountservice.service.GroupMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class FeatureController implements FeatureApi {

    private final FeatureService featureService;
    private final GroupMenuService groupMenuService;

    @Override
    public ResponseEntity<FeatureResponse> getFeatures() {
        return ResponseEntity.ok().body(featureService.getFeatures());
    }

    @Override
    public ResponseEntity<MenuResponse> getMenu() {
        return ResponseEntity.ok().body(groupMenuService.getMenu());
    }
}
