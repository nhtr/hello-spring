package com.nhtr.accountservice.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    @EqualsAndHashCode.Include
    private String uuid = UUID.randomUUID().toString();

    private String label;

    private String icon;

    private String routerLink;

    private Boolean hasChildren;

    private Long parent;

    private Boolean showInMenu;
}

