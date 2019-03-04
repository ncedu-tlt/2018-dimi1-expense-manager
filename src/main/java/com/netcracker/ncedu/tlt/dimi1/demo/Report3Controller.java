package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
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

    @RequestMapping(value = "/showReport3", method = RequestMethod.POST)
    String showReport1(ModelMap model){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        model.addAttribute("report3", dbObj.getReport3());
        return "report3";
    }
}
