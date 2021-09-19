package com.nhtr.accountservice.controller;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.service.FeatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "api/feature")
public class FeatureController {

    private final FeatureService featureService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public FeatureResponse menu() {
        return featureService.getFeatures();
    }
}
