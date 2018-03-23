package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.entity.Drug;
import org.vardb.model.response.DrugItem;
import org.vardb.model.response.PageResult;
import org.vardb.service.DrugService;
import org.vardb.tool.HtmlTool;

@Controller
public class DrugController {
    @Autowired
    DrugService service;

    @ResponseBody
    @RequestMapping( value = "/drugs", method = RequestMethod.GET)
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
    @RequestMapping( value = "/drug_pathogens", method = RequestMethod.GET)
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
    @RequestMapping( value = "/drug_diseases", method = RequestMethod.GET)
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

    @RequestMapping( value = "/drug", method = RequestMethod.GET )
    public String view(
        	@RequestParam( name = "id", required = false ) Integer id,
    		Model model
    ) {
    	String page = "drug";
    	if( id != null ) {
    		page = "drug_detail";

    		Drug drug = this.service.findDrug( id );
    		DrugItem item = DrugService.convertDrugToItem( drug );

    		model.addAttribute( "drug", drug );
    		model.addAttribute( "item", item );
    		model.addAttribute( "id", id );
    		model.addAttribute( "pathogenCount", this.service.pathogenCount( id ) );
    		model.addAttribute( "diseaseCount", this.service.diseaseCount( id ) );
    	}
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "drug" );
    	return page;
    }
}

