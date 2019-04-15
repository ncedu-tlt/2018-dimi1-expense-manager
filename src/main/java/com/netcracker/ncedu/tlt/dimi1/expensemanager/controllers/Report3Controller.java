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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class Report3Controller {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public Report3Controller(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/showReport3/{accountId}", method = RequestMethod.GET)
    String showReport(ModelMap model, String date, @PathVariable("accountId") Integer accountId){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        return "report3";
    }

    @RequestMapping(value = "/showReport3/{accountId}", method = RequestMethod.POST)
    String showReportChart(ModelMap model, String addDate, @PathVariable("accountId") Integer accountId) throws ParseException {
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("report3", dbObj.getReport3(addDate, user.getUsername(), accountId));
        return "report3";
    }
}
