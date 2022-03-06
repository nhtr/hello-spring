package com.nhtr.accountservice.controller;

import com.nhtr.accountservice.client.FeatureApi;

import com.nhtr.accountservice.client.model.FeaturePageResponse;
import com.nhtr.accountservice.client.model.MenuTransformedResponse;
import com.nhtr.accountservice.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.MenuResponse;

@RequiredArgsConstructor
@RestController
public class FeatureController implements FeatureApi {

    private final FeatureService featureService;

    @Override
    public ResponseEntity<FeaturePageResponse> getFeatureWithPage(Long id, Integer limit) {
        return ResponseEntity.ok().body(featureService.getFeaturePaging(id, limit));
    }

    @Override
    public ResponseEntity<FeatureResponse> getFeatures() {
        return ResponseEntity.ok().body(featureService.getFeatures());
    }

    @Override
    public ResponseEntity<MenuResponse> getMenu() {
        return ResponseEntity.ok(featureService.getMenu());
    }

    @Override
    public ResponseEntity<MenuTransformedResponse> getMenuTransformed() {
        return ResponseEntity.ok().body(featureService.getMenuTransformed());
    }
}
