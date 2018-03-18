package org.vardb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.vardb.model.response.PageResult;
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

    @RequestMapping( value = "/reference", method = RequestMethod.GET )
    public String view( Model model ) {
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "reference" );
    	return "reference";
    }
}

