package org.vardb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.vardb.tool.HtmlTool;

@Controller
public class MainController {

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public String view( Model model ) {
    	model.addAttribute( "pages", HtmlTool.getPages() );
    	model.addAttribute( "name", "top" );
    	return "main";
    }

    @RequestMapping( value = "/top", method = RequestMethod.GET )
    public String top( Model model ) {
    	return view( model );
    }
}
