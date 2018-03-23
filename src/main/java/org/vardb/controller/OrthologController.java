package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Ortholog;
import org.vardb.model.response.OrthologItem;
import org.vardb.model.response.PageResult;
import org.vardb.service.OrthologService;
import org.vardb.tool.HtmlTool;

@Controller
public class OrthologController {
    @Autowired
    OrthologService service;

    @ResponseBody
    @RequestMapping( value = "/orthologs", method = RequestMethod.GET)
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
    @RequestMapping( value = "/ortholog_refs", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping( value = "/ortholog_families", method = RequestMethod.GET)
    public PageResult familyJson(
    		@RequestParam( name = "id" ) Integer id,
    		@RequestParam( name = "page" ) Integer page,
    		@RequestParam( name = "size" ) Integer size,
    		@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    		@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.familyPage( id, page, size, sort, dir );
    	return result;
    }

    @RequestMapping( value = "/ortholog", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id", required = false ) Integer id,
    		Model model
    ) {
    	String page = "ortholog";
    	if( id != null ) {
    		page = "ortholog_detail";
    		Ortholog ortholog = this.service.findOrtholog( id );
    		OrthologItem item = OrthologService.convertOrthologToItem( ortholog );
    		model.addAttribute( "ortholog", ortholog );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", id );
    		model.addAttribute( "refCount", this.service.referenceCount( id ) );
    		model.addAttribute( "familyCount", ortholog.getFamilies().size() );
    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "ortholog" );
    	return page;
    }
}
