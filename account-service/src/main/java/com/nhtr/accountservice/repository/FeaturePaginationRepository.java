package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.client.model.FeatureDto;

import java.util.List;

public interface FeaturePaginationRepository {

    List<FeatureDto> fetchWithKeysetPagination(long id, int limit);
}
