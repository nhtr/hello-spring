package com.nhtr.accountservice.mapper;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.entity.Feature;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureDtoMapper extends DomainMapper<FeatureDto, Feature> {
}
