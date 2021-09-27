package com.nhtr.accountservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class RoleFeatureId implements Serializable {
    private String roleId;

    private Long featureId;
}
