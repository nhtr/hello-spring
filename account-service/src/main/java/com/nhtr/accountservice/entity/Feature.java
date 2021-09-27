package com.nhtr.accountservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Feature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    public Feature() {
    }

    public Feature(String label, String icon, String routerLink, Boolean hasChildren, Long parent, Boolean showInMenu, Integer orderValue) {
        this.label = label;
        this.icon = icon;
        this.routerLink = routerLink;
        this.hasChildren = hasChildren;
        this.parent = parent;
        this.showInMenu = showInMenu;
        this.orderValue = orderValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRouterLink() {
        return routerLink;
    }

    public void setRouterLink(String routerLink) {
        this.routerLink = routerLink;
    }

    public Boolean getHasChildren() {
        return hasChildren;
    }

    public void setHasChildren(Boolean hasChildren) {
        this.hasChildren = hasChildren;
    }

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public Boolean getShowInMenu() {
        return showInMenu;
    }

    public void setShowInMenu(Boolean showInMenu) {
        this.showInMenu = showInMenu;
    }

    public GroupMenu getGroupMenu() {
        return groupMenu;
    }

    public void setGroupMenu(GroupMenu groupMenu) {
        this.groupMenu = groupMenu;
    }

    public Integer getOrderValue() {
        return orderValue;
    }

    public void setOrderValue(Integer orderValue) {
        this.orderValue = orderValue;
    }

    @Override
    public String toString() {
        return "Feature{" +
                "id=" + id +
                ", uuid='" + uuid + '\'' +
                ", label='" + label + '\'' +
                ", icon='" + icon + '\'' +
                ", routerLink='" + routerLink + '\'' +
                ", hasChildren=" + hasChildren +
                ", parent=" + parent +
                ", showInMenu=" + showInMenu +
                ", orderValue=" + orderValue +
                ", groupMenu=" + groupMenu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Feature feature = (Feature) o;
        return Objects.equals(uuid, feature.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}

