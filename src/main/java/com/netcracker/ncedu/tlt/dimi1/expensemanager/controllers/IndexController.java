package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IndexController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    String home(ModelMap model) {
        DatabaseWork databaseWork = new DatabaseWork(jdbcTemplate);
        model.addAttribute("tables", databaseWork.getDBTablesAsJsonArray().toString());
        model.addAttribute("relations", databaseWork.getDBTablesRelationsAsJsonArray().toString());
        return "menu";
    }
}
