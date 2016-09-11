package com.oril.dao;

import com.oril.model.Domain;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DomainDAOImpl implements DomainDAO {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    public void addDomain(Domain domain) {
        getCurrentSession().save(domain);

    }

    public void updateDomain(Domain domain) {
        Domain domainToUpdate = getDomain(domain.getId());
        domainToUpdate.setName(domain.getName());
        domainToUpdate.setStatus(domain.getStatus());
        getCurrentSession().update(domainToUpdate);

    }

    public Domain getDomain(int id) {
        Domain domain = (Domain) getCurrentSession().get(Domain.class, id);
        return domain;
    }

    public void deleteDomain(int id) {
        Domain domain = getDomain(id);
        if (domain != null)
            getCurrentSession().delete(domain);
    }


    @SuppressWarnings("unchecked")
    public List<Domain> getDomains() {
        return getCurrentSession().createQuery("FROM Domain").list();

    }
}
