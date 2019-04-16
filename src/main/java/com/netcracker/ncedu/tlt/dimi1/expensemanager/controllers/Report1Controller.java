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
public class Report1Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report1Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport1/{accountId}", method = RequestMethod.GET )
    String showReport1(ModelMap model, @PathVariable("accountId") Integer accountId){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("report1", dbObj.getReport1(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));
        model.addAttribute("totalSum", dbObj.getTotalSum(dbObj.getPersonByLogin(user.getUsername()).getPersonId(), accountId));
        return "report1";
    }
}
