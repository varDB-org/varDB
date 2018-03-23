package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Diseas;
import org.vardb.model.response.DiseaseItem;
import org.vardb.model.response.PageResult;
import org.vardb.service.DiseaseService;
import org.vardb.tool.HtmlTool;

@Controller
public class DiseaseController {
    @Autowired
    DiseaseService service;

    @ResponseBody
    @RequestMapping( value = "/diseases", method = RequestMethod.GET)
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
    @RequestMapping( value = "/disease_drugs", method = RequestMethod.GET)
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
    @RequestMapping( value = "/disease_pathogens", method = RequestMethod.GET)
    public PageResult pathogenJson(
        	@RequestParam( name = "id" ) Integer id,
        	@RequestParam( name = "page" ) Integer page,
        	@RequestParam( name = "size" ) Integer size,
        	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
        	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.pathogenPage( id, page, size, sort, dir );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/disease_refs", method = RequestMethod.GET)
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

    @RequestMapping( value = "/disease", method = RequestMethod.GET )
    public String view(
    		@RequestParam( name = "id", required = false ) Integer id,
    		Model model
    ) {
    	String page = "disease";
    	if( id != null ) {
    		page = "disease_detail";
    		Diseas disease = this.service.findDisease( id );
    		DiseaseItem item = DiseaseService.convertDiseaseToItem( disease );

    		model.addAttribute( "disease", disease );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", id );
    		model.addAttribute( "refCount", this.service.referenceCount( id ) );
    		model.addAttribute( "drugCount", this.service.drugCount( id ) );
    		model.addAttribute( "pathogenCount", this.service.pathogenCount( id ) );
    		model.addAttribute( "isHuman", disease.getHuman() );
    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "disease" );
    	return page;
    }
}
