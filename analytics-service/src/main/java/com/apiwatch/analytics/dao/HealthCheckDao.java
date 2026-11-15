package com.apiwatch.analytics.dao;

import com.apiwatch.analytics.entity.HealthCheckEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class HealthCheckDao {

    private final SessionFactory sessionFactory;

    public HealthCheckDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(HealthCheckEntity entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    public Optional<HealthCheckEntity> getLatestHealthCheckRecordByEndpointId(UUID endPointId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession()
                .createQuery("FROM HealthCheckEntity h WHERE h.endpointId = :endpointId ORDER BY h.checkedAt DESC", HealthCheckEntity.class)
                .setParameter("endpointId", endPointId)
                .setMaxResults(1)
                .getSingleResultOrNull());
    }

}
