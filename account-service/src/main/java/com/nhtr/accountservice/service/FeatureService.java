package com.nhtr.accountservice.service;

import com.nhtr.accountservice.client.model.FeatureResponse;
import com.nhtr.accountservice.client.model.MenuResponse;
import com.nhtr.accountservice.client.model.MenuTransformedResponse;

public interface FeatureService {

    FeatureResponse getFeatures();
    MenuResponse getMenu();
    MenuTransformedResponse getMenuTransformed();
}
