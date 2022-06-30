package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.entity.RoleFeature;
import com.nhtr.accountservice.entity.RoleFeatureId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleFeatureRepository extends JpaRepository<RoleFeature, RoleFeatureId> {
    void deleteByRoleId(String roleId);
}
