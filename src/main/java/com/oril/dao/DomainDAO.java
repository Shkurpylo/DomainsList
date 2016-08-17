package com.oril.dao;


import com.oril.model.Domain;

import java.util.List;

public interface DomainDAO {

    public void addDomain(Domain domain);
    public void updateDomain(Domain domain);
    public Domain getDomain(int id);
    public void deleteDomain(int id);
    public List<Domain> getDomains();

}
