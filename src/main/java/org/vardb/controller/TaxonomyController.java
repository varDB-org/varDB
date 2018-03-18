package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Taxon;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.TaxonItem;
import org.vardb.service.TaxonomyService;
import org.vardb.tool.HtmlTool;

@Controller
public class TaxonomyController {
    @Autowired
    TaxonomyService service;

    @ResponseBody
    @RequestMapping( value = "/taxons", method = RequestMethod.GET)
    public PageResult json(
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir,
    	@RequestParam( name = "keyword", required = false ) String keyword
    ) {
        PageResult result = this.service.page( page, size, sort, dir, keyword );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/taxon_children", method = RequestMethod.GET)
    public PageResult childJson(
    	@RequestParam( name = "id" ) Integer id,
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.childrenPage( id,  page,  size, sort, dir );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/taxon_pathogens", method = RequestMethod.GET)
    public PageResult pathogenJson(
    	@RequestParam( name = "id" ) Integer id,
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.pathogenPage( id,  page,  size, sort, dir );
    	return result;
    }

    @RequestMapping( value = "/taxon", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id", required = false ) Integer id,
    		Model model
    ) {
    	String page = "taxon";
    	if( id != null ) {
    		page = "taxon_detail";
    		Taxon taxon = this.service.findTaxon( id );
    		TaxonItem item = TaxonomyService.convertTaxonToItem( taxon );

    		model.addAttribute( "taxon", taxon );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", id );

    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "taxon" );
    	return page;
    }
}
