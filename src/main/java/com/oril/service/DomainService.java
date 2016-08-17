package com.oril.service;


import com.oril.model.Domain;

import java.util.List;

public interface DomainService {

    public void addDomain(Domain domain);
    public void updateDomain(Domain domain);
    public Domain getDomain(int id);
    public void deleteDomain(int id);
    public List<Domain> getDomains();
}
