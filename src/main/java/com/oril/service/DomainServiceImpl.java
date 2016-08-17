package com.oril.service;

import java.util.List;

import com.oril.dao.DomainDAO;
import com.oril.model.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainServiceImpl implements DomainService {

    @Autowired
    private DomainDAO domainDAO;


    public void addDomain(Domain domain) {
        domainDAO.addDomain(domain);

    }

    public void updateDomain(Domain domain) {
        domainDAO.updateDomain(domain);

    }

    public Domain getDomain(int id) {
        return domainDAO.getDomain(id);
    }


    public void deleteDomain(int id) {
        domainDAO.deleteDomain(id);

    }

    public List<Domain> getDomains() {
        return domainDAO.getDomains();
    }
}
