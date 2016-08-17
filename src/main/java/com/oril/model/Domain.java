package com.oril.model;


import com.oril.exceptions.LookupException;
import com.oril.exceptions.ServiceUnavailableException;
import com.oril.service.DomainVerificationImpl;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.IOException;

@Entity
@Table(name="domains")
public class Domain {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private String status;


    public Integer getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public void setId(Integer id) { this.id = id; }
    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public void setStatus() {
        try {
            boolean isSafety = new DomainVerificationImpl().lookupURL(this.name);
            this.status = isSafety ? "NOT in the blacklist" : "in the blacklist";
        } catch (IOException | ServiceUnavailableException | LookupException ex) {
            this.status = "empty";
        }
    }
    public String getStatus() {
        return status;
    }

}
