package com.nhtr.accountservice.service;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.MenuResponse;

public interface FeatureService {

    FeatureResponse getFeatures();
    MenuResponse getMenu();
}
