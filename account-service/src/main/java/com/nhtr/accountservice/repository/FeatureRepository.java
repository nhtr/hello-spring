package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.client.model.FeatureDto;
import com.nhtr.accountservice.entity.Feature;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FeatureRepository extends JpaRepository<Feature, Long>, FeaturePaginationRepository {

    @Query(value = "select new com.nhtr.accountservice.client.model.FeatureDto(" +
            " f.id, f.label, f.icon, f.routerLink, f.hasChildren, f.parent, f.showInMenu, f.orderValue) from Feature f")
    List<FeatureDto> findAllDto();

    @Query(value = "select f from Feature f" +
            " join RoleFeature rf on f.id = rf.featureId" +
            " where rf.roleId in :roles")
    @EntityGraph(attributePaths = {"groupMenu"})
    List<Feature> findByRoles(@Param("roles") String[] roles);

    @Query(value = "select new com.nhtr.accountservice.client.model.FeatureDto(" +
            " f.id, f.label, f.icon, f.routerLink, f.hasChildren, f.parent, f.showInMenu, f.orderValue) from Feature f" +
            " join RoleFeature rf on f.id = rf.featureId" +
            " where rf.roleId = :role")
    // @EntityGraph(attributePaths = {"groupMenu"})
    List<FeatureDto> findByRole(@Param("role") String role);
}
