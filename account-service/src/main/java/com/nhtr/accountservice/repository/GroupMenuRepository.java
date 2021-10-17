package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.entity.GroupMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface GroupMenuRepository extends JpaRepository<GroupMenu, Long> {

    List<Object[]> findWithFeatureByRoles(@Param("roles") String[] roles);
}
