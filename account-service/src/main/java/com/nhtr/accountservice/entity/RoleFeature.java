package com.nhtr.accountservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@IdClass(value = RoleFeatureId.class)
public class RoleFeature implements Serializable {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Id
    @Column(name = "feature_id")
    private Long featureId;

    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    public RoleFeature() {
    }

    public RoleFeature(String roleId, Long featureId) {
        this.roleId = roleId;
        this.featureId = featureId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleFeature that = (RoleFeature) o;
        return Objects.equals(uuid, that.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
