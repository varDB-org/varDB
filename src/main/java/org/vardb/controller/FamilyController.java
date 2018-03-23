package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Family;
import org.vardb.model.response.FamilyItem;
import org.vardb.model.response.PageResult;
import org.vardb.service.FamilyService;
import org.vardb.service.GeneService;
import org.vardb.tool.HtmlTool;

@Controller
public class FamilyController {
    @Autowired
    FamilyService service;

    @Autowired
    GeneService geneService;

    @ResponseBody
    @RequestMapping( value = "/families", method = RequestMethod.GET)
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
    @RequestMapping( value = "/family_refs", method = RequestMethod.GET)
    public PageResult refJson(
    		@RequestParam( name = "id" ) Integer id,
    		@RequestParam( name = "page" ) Integer page,
    		@RequestParam( name = "size" ) Integer size,
    		@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    		@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.refPage( id, page, size, sort, dir );
    	return result;
    }

    @RequestMapping( value = "/family", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id", required = false ) Integer id,
    		Model model
    ) {
    	String page = null;
    	if( id == null ) {
    		page ="family";
    	}
    	else {
    		page = "family_detail";
    		Family family = this.service.findFamily( id );
    		FamilyItem item = FamilyService.convertFamilyToItem( family );
    		model.addAttribute( "family", family );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", family.getId() );
    		model.addAttribute( "refCount", this.service.referenceCount( id ) );
    		model.addAttribute( "geneCount", this.geneService.geneCount( family.getName() ) );
    	}

    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "family" );

    	return page;
    }
}
