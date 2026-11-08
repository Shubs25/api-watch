package com.apiwatch.analytics.dao;

import com.apiwatch.analytics.entity.HealthCheckEntity;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class HealthCheckDao {

    private final SessionFactory sessionFactory;

    public HealthCheckDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(HealthCheckEntity entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }
}
