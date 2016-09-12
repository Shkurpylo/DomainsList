package com.oril.service;

import java.io.IOException;
import java.util.List;

import com.oril.dao.DomainDAO;
import com.oril.exceptions.LookupException;
import com.oril.exceptions.ServiceUnavailableException;
import com.oril.model.Domain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DomainServiceImpl implements DomainService {

    @Autowired
    private DomainDAO domainDAO;


    public void addDomain(Domain domain) throws IOException,  ServiceUnavailableException,  LookupException {

        boolean isSafety = new DomainVerificationImpl().lookupURL(domain.getName());
        domain.setStatus(isSafety ? "NOT in the blacklist" : "in the blacklist");
        domainDAO.addDomain(domain);

    }

    public void updateDomain(Domain domain) throws IOException,  ServiceUnavailableException,  LookupException   {

        boolean isSafety = new DomainVerificationImpl().lookupURL(domain.getName());
        domain.setStatus(isSafety ? "NOT in the blacklist" : "in the blacklist");
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
