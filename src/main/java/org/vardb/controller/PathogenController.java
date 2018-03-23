package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Pathogen;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.PathogenItem;
import org.vardb.service.PathogenService;
import org.vardb.tool.HtmlTool;

@Controller
public class PathogenController {
    @Autowired
    PathogenService service;

    @ResponseBody
    @RequestMapping( value = "/pathogens", method = RequestMethod.GET)
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
    @RequestMapping( value = "/pathogen_diseases", method = RequestMethod.GET)
    public PageResult diseaseJson(
    	@RequestParam( name = "id" ) Integer id,
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.diseasePage( id, page, size, sort, dir );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/pathogen_drugs", method = RequestMethod.GET)
    public PageResult drugJson(
    	@RequestParam( name = "id" ) Integer id,
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.drugPage( id, page, size, sort, dir );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/pathogen_refs", method = RequestMethod.GET)
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

    @RequestMapping( value = "/pathogen", method = RequestMethod.GET )
    public String view(
        	@RequestParam( name = "id", required = false ) Integer id,
    		Model model ) {
    	String page = "pathogen";
    	if( id != null ) {
    		page = "pathogen_detail";
    		Pathogen pathogen = this.service.findPathogen( id );
    		PathogenItem item = PathogenService.convertPathogenToItem( pathogen );

    		model.addAttribute( "pathogen", pathogen );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", id );
    		model.addAttribute( "drugCount", this.service.drugCount( id ) );
    		model.addAttribute( "diseaseCount", this.service.diseaseCount( id ) );
    		model.addAttribute( "refCount", this.service.referenceCount( id ) );
    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "pathogen" );
    	return page;
    }
}

