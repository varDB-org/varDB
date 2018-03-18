package org.vardb.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.response.KeggOrthologCategory;
import org.vardb.service.KeggOrthologService;

@Controller
public class KeggOrthologController {
	@Autowired
	KeggOrthologService service;

    @ResponseBody
    @RequestMapping( value = "/kegg_ortholog_oc", method = RequestMethod.GET )
    public Object json(
    		@RequestParam( name = "id", required = false ) String id,
    		Model model
    ) throws Exception {
    	List< KeggOrthologCategory > categories = this.service.getOrthologCategories( id );
    	return categories;
    }

    @RequestMapping( value = "/kegg_ortholog", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id", required = false ) String id,
    		Model model
    ) {
    	model.addAttribute( "id", id );
    	return "kegg_ortholog";
    }
}
