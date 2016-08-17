package com.oril.service;

import com.oril.exceptions.LookupException;
import com.oril.exceptions.ServiceUnavailableException;

import java.io.IOException;

public interface DomainVerification {

    public boolean lookupURL(String url_in)
        throws IOException, ServiceUnavailableException, LookupException;

}
