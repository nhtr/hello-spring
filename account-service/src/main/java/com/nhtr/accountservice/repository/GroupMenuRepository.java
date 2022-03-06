package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.entity.GroupMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.Tuple;
import java.util.List;


public interface GroupMenuRepository extends JpaRepository<GroupMenu, Long> {

    @Query(value = "select gm.id as gId, gm.label as gLabel, gm.icon as gIcon, gm.order_value as gOrderValue," +
            " ft.id as id, ft.label as label, ft.icon as icon, ft.router_link as routerLink, ft.has_children as hasChildren, ft.parent as parent, ft.show_in_menu as showInMenu, ft.order_value as orderValue" +
            " from group_menu gm right join feature ft on gm.id = ft.group_id" +
            " join role_feature rf on ft.id = rf.feature_id" +
            " where rf.role_id in :roles", nativeQuery = true)
    List<Tuple> findWithFeatureByRoles(@Param("roles") String[] roles);
}
