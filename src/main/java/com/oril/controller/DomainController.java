package com.oril.controller;

import com.oril.service.DomainService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import com.oril.model.Domain;

@Controller
@RequestMapping(value="/domain")
public class DomainController {

    @Autowired
    private DomainService domainService;

    @RequestMapping(value="/add", method=RequestMethod.GET)
    public ModelAndView addDomainPage() {
        ModelAndView modelAndView = new ModelAndView("add-domain-form");
        modelAndView.addObject("domain", new Domain());
        return modelAndView;
    }

    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ModelAndView addingDomain(@ModelAttribute Domain domain) {

        ModelAndView modelAndView = new ModelAndView("home");
        domainService.addDomain(domain);
        String message = "Domain was successfully added.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/list")
    public ModelAndView listOfDomains() {
        ModelAndView modelAndView = new ModelAndView("list-of-domains");
        List<Domain> domains = domainService.getDomains();
        modelAndView.addObject("domains", domains);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
    public ModelAndView editDomainPage(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("edit-domain-form");
        Domain domain = domainService.getDomain(id);
        modelAndView.addObject("domain", domain);

        return modelAndView;
    }

    @RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
    public ModelAndView edditingDomain(@ModelAttribute Domain domain, @PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        domainService.updateDomain(domain);
        String message = "Domain was successfully edited.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }

    @RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
    public ModelAndView deleteDomain(@PathVariable Integer id) {
        ModelAndView modelAndView = new ModelAndView("home");
        domainService.deleteDomain(id);
        String message = "Domain was successfully deleted.";
        modelAndView.addObject("message", message);

        return modelAndView;
    }
}
