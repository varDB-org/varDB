package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Ref;
import org.vardb.model.response.PageResult;
import org.vardb.model.response.ReferenceItem;
import org.vardb.service.ReferenceService;
import org.vardb.tool.HtmlTool;

@Controller
public class ReferenceController {
    @Autowired
    ReferenceService service;

    @ResponseBody
    @RequestMapping( value = "/references", method = RequestMethod.GET)
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
    @RequestMapping( value = "/reference_families", method = RequestMethod.GET)
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

    @ResponseBody
    @RequestMapping( value = "/reference_orthologs", method = RequestMethod.GET)
    public PageResult orthologJson(
    	@RequestParam( name = "id" ) Integer id,
    	@RequestParam( name = "page" ) Integer page,
    	@RequestParam( name = "size" ) Integer size,
    	@RequestParam( name = "sorters[0][field]", required = false ) String sort,
    	@RequestParam( name = "sorters[0][dir]", required = false  ) String dir
    ) {
        PageResult result = this.service.orthologPage( id, page, size, sort, dir );
    	return result;
    }

    @ResponseBody
    @RequestMapping( value = "/reference_diseases", method = RequestMethod.GET)
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
    @RequestMapping( value = "/reference_pathogens", method = RequestMethod.GET)
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

    @RequestMapping( value = "/reference", method = RequestMethod.GET )
    public String view(
        	@RequestParam( name = "id", required = false ) Integer id,
    		Model model )
    {
    	String page = "reference";
    	if( id != null ) {
    		page = "reference_detail";
    		Ref reference = this.service.findReference( id );
    		ReferenceItem item = ReferenceService.convertReferenceToItem( reference );
    		model.addAttribute( "reference", reference );
    		model.addAttribute( "id", id );
    		model.addAttribute( "item", item );
    		model.addAttribute( "familyCount", this.service.familyCount( id ) );
    		model.addAttribute( "orthologCount", this.service.orthologCount( id ) );
    		model.addAttribute( "diseaseCount", this.service.diseaseCount( id ) );
    		model.addAttribute( "pathogenCount", this.service.pathogenCount( id ) );
    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "reference" );
    	return page;
    }
}

