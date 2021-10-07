package com.nhtr.accountservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
@Entity
@IdClass(value = RoleFeatureId.class)
@EntityListeners(AuditingEntityListener.class)
public class RoleFeature implements Serializable {

    @Id
    @Column(name = "role_id")
    private String roleId;

    @Id
    @Column(name = "feature_id")
    private Long featureId;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    @Embedded
    private AuditingData auditingData = new AuditingData();


    public RoleFeature(String roleId, Long featureId) {
        this.roleId = roleId;
        this.featureId = featureId;
    }

    @Override
    public String toString() {
        return "RoleFeature{" +
                "roleId='" + roleId + '\'' +
                ", featureId=" + featureId +
                '}';
    }
}
