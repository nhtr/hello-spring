package com.nhtr.accountservice.service.impl;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.repository.FeatureRepository;
import com.nhtr.accountservice.service.FeatureService;
import com.nhtr.commonmodule.model.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FeatureServiceImpl implements FeatureService {

    private final FeatureRepository featureRepository;

    @Override
    public FeatureResponse getFeatures() {
        FeatureResponse response = new FeatureResponse();
        response.setResult(ApiResult.OK);
        response.setContext(featureRepository.findAllAndMapToDto());
        return response;
    }
}
