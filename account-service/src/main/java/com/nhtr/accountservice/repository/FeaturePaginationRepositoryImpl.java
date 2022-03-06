package com.nhtr.accountservice.repository;

import com.nhtr.accountservice.client.model.FeatureDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class FeaturePaginationRepositoryImpl implements FeaturePaginationRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<FeatureDto> fetchWithKeysetPagination(long id, int limit) {
        TypedQuery<FeatureDto> query = entityManager.createQuery("select new com.nhtr.accountservice.client.model.FeatureDto(" +
                " f.id, f.label, f.icon, f.routerLink, f.hasChildren, f.parent, f.showInMenu, f.orderValue)" +
                " from Feature f where f.id < ?1 order by f.id desc", FeatureDto.class);
        query.setParameter(1, id);
        return query.setMaxResults(limit).getResultList();
    }
}
