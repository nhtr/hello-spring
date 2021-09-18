package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.entity.Feature;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FeatureRepository extends CrudRepository<Feature, Long> {

    List<FeatureDto> loadAll();
}
