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
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@NamedEntityGraph(
        name = "group-with-features",
        attributeNodes = {
                @NamedAttributeNode(value = "features")
        }
)
public class GroupMenu implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @EqualsAndHashCode.Include
    @Column(unique = true, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID()
            .toString();

    private String label;

    private String icon;

    private Integer orderValue;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="groupMenu")
    private List<Feature> features = new ArrayList<>();

    public GroupMenu(String label, String icon, Integer orderValue) {
        this.label = label;
        this.icon = icon;
        this.orderValue = orderValue;
    }

    @Override
    public String toString() {
        return "GroupMenu{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", icon='" + icon + '\'' +
                ", orderValue=" + orderValue +
                '}';
    }
}
