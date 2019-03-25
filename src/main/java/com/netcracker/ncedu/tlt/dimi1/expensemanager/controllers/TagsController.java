package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Deprecated
@Controller
public class TagsController {
    @RequestMapping(value = "/tagscloud", method = RequestMethod.GET)
    String home(ModelMap model) {
        return "tagscloud";
    }
}