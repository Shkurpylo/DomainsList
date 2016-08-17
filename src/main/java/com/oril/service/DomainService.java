package com.oril.service;


import com.oril.exceptions.LookupException;
import com.oril.exceptions.ServiceUnavailableException;
import com.oril.model.Domain;

import java.io.IOException;
import java.util.List;

public interface DomainService {

    public void addDomain(Domain domain) throws IOException, ServiceUnavailableException, LookupException;
    public void updateDomain(Domain domain) throws IOException,  ServiceUnavailableException,  LookupException ;
    public Domain getDomain(int id);
    public void deleteDomain(int id);
    public List<Domain> getDomains();
}
