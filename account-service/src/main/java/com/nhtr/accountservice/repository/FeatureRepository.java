package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.entity.Feature;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long> {

    List<FeatureDto> findAllAndMapToDto();

    @EntityGraph(attributePaths = {"groupMenu"})
    List<Feature> findByRoles(@Param("roles") String[] roles);
}
