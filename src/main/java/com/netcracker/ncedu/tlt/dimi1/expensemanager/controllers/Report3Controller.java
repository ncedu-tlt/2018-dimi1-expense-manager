package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report3Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report3Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport3", method = RequestMethod.GET)
    String showReport1(ModelMap model){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        model.addAttribute("report3", dbObj.getReport3("03.03.2019 10:30", "04.05.2019 3:00"));
        return "report3";
    }
}
