package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Report2Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report2Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport2/{accountId}", method = RequestMethod.GET)
    String showReport1(ModelMap model, @PathVariable("accountId") Integer accountId){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("report2req", dbObj.getReport2Required(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));
        model.addAttribute("report2unreq", dbObj.getReport2Unrequired(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));

        model.addAttribute("totalSumReq", dbObj.getTotalSum(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId, true));
        model.addAttribute("totalSumUnreq", dbObj.getTotalSum(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId, false));

        return "report2";
    }

    @RequestMapping(value = "/test/{accountId}", method = RequestMethod.GET)
    String test(ModelMap model, @PathVariable("accountId") Integer accountId){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("report2req", dbObj.getReport2Required(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));
        model.addAttribute("report2unreq", dbObj.getReport2Unrequired(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));

        model.addAttribute("totalSumReq", dbObj.getTotalSum(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId, true));
        model.addAttribute("totalSumUnreq", dbObj.getTotalSum(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId, false));

        return "test";
    }
}
