package org.vardb.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.GeneManager;
import org.vardb.model.entity.Family;
import org.vardb.model.entity.Pathogen;
import org.vardb.model.response.GeneItem;
import org.vardb.model.response.PageResult;
import org.vardb.service.FamilyService;
import org.vardb.service.GeneService;
import org.vardb.service.PathogenService;
import org.vardb.tool.HtmlTool;

@Controller
public class GeneController {
	@Autowired
	GeneService service;

	@Autowired
	PathogenService pathogenService;

	@Autowired
	FamilyService familyService;

    @ResponseBody
    @RequestMapping( value = "/genes", method = RequestMethod.GET)
    public PageResult familyJson(
        	@RequestParam( name = "family" ) String family,
        	@RequestParam( name = "page" ) Integer page,
        	@RequestParam( name = "size" ) Integer size,
        	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
        	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
            PageResult result = this.service.page( family, page, size, sort, dir );
        	return result;
    }


    @RequestMapping( value = "/gene", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id" ) String id,
    		Model model
    ) {
    	String page = "gene_detail";

    	GeneManager geneManager = GeneManager.getInstance();
    	GeneItem gene = geneManager.getGene( id );
    	model.addAttribute( "gene", gene );

    	String familyString = "";
    	String pathogenString = "";
    	Map< String, Object > keggGene = this.service.getKeggGene( gene );

    	if( gene != null ) {
    		Family family = this.familyService.findFamily( gene.getFamily() );
    		if( family != null ) {
    			familyString = "<a href=\"family?id=" + family.getId() + "\">" + family.getName() + "</a>";
    		}

    		Pathogen pathogen = this.pathogenService.findPathogenByKeggOrganism( gene.getSpecies() );
    		if( pathogen != null ) {
    			pathogenString = "<a href=\"pathogen?id=" + pathogen.getId() + "\">" + pathogen.getName() + "</a>";
    		}
    	}

    	model.addAttribute( "family", familyString );
    	model.addAttribute( "pathogen", pathogenString );
    	model.addAttribute( "kegg", keggGene );

    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "gene" );
    	return page;
    }

}
