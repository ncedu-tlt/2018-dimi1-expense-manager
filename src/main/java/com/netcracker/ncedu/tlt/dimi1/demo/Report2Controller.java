package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report2Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report2Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport2", method = RequestMethod.POST)
    String showReport1(ModelMap model){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        model.addAttribute("report2req", dbObj.getReport2Required());
        model.addAttribute("report2unreq", dbObj.getReport2Unrequired());
        model.addAttribute("totalSumReq", dbObj.getTotalSum(true));
        model.addAttribute("totalSumUnreq", dbObj.getTotalSum(false));
        return "report2";
    }
}
