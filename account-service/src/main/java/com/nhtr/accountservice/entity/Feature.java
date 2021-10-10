package com.nhtr.accountservice.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();

    private String label;

    private String icon;

    private String routerLink;

    private Boolean hasChildren;

    private Long parent;

    private Boolean showInMenu;

    private Integer orderValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private GroupMenu groupMenu;


    public Feature(String label, String icon, String routerLink, Boolean hasChildren, Long parent, Boolean showInMenu, Integer orderValue) {
        this.label = label;
        this.icon = icon;
        this.routerLink = routerLink;
        this.hasChildren = hasChildren;
        this.parent = parent;
        this.showInMenu = showInMenu;
        this.orderValue = orderValue;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", icon='" + icon + '\'' +
                ", routerLink='" + routerLink + '\'' +
                ", hasChildren=" + hasChildren +
                ", parent=" + parent +
                ", showInMenu=" + showInMenu +
                ", orderValue=" + orderValue +
                '}';
    }
}

