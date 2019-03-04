package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report1Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report1Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport1", method = RequestMethod.GET )
    String showReport1(ModelMap model){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        model.addAttribute("report1", dbObj.getReport1());
        model.addAttribute("totalSum", dbObj.getTotalSum());
        return "report1";
    }
}
