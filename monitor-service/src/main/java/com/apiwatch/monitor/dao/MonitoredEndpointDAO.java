package com.apiwatch.monitor.dao;

import com.apiwatch.monitor.entity.MonitoredEndpointEntity;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MonitoredEndpointDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public Optional<MonitoredEndpointEntity> findByUrl(String url) {
        String query = "FROM monitored_endpoint me WHERE me.url = :url";
        List<MonitoredEndpointEntity> result = sessionFactory.getCurrentSession()
                .createQuery(query,  MonitoredEndpointEntity.class)
                .setParameter("url", url)
                .list();

        return Optional.ofNullable(!result.isEmpty() ? result.get(0) : null);
    }

    public void insertMonitoredEndpoint(MonitoredEndpointEntity monitoredEndpointEntity) {
        sessionFactory.getCurrentSession().persist(monitoredEndpointEntity);
    }

    public List<MonitoredEndpointEntity> findAll() {
        String query = "FROM monitored_endpoint";

        return sessionFactory.getCurrentSession()
                .createQuery(query, MonitoredEndpointEntity.class)
                .getResultList();
    }

    public Optional<MonitoredEndpointEntity> findById(UUID id) {
        return sessionFactory.getCurrentSession()
                .createQuery(
                        "FROM monitored_endpoint me WHERE me.id = :id",
                        MonitoredEndpointEntity.class
                )
                .setParameter("id", id)
                .uniqueResultOptional();
    }

    public void update(MonitoredEndpointEntity entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    public void delete(MonitoredEndpointEntity entity) {
        sessionFactory.getCurrentSession().remove(entity);
    }
}
