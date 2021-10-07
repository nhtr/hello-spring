package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.entity.GroupMenu;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupMenuRepository extends CrudRepository<GroupMenu, Long> {

    @EntityGraph(value = "group-with-features", type = EntityGraph.EntityGraphType.FETCH)
    @Override
    List<GroupMenu> findAll();
}
