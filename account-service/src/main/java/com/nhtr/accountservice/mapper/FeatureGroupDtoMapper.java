package com.nhtr.accountservice.mapper;

import com.nhtr.accountservice.client.model.FeatureGroupDto;
import com.nhtr.accountservice.entity.Feature;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeatureGroupDtoMapper extends DomainMapper<FeatureGroupDto, Feature> {
}
