package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.PersonImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class RegistrationController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public RegistrationController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    String registration(ModelMap model) {
        return "menu";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    String addUser(String login, String password, String email, String information, String number) {
        Date date = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd.mm.yyyy");

        DatabaseWork databaseObj = new DatabaseWork(jdbcTemplate);
        String  sql = "select login from person where login = '" + login + "'" ;
        if(!databaseObj.checkLogin(sql) && login != "" && email !="")
        {
            jdbcTemplate.update("INSERT INTO Person (login, pass, access, email, description, reg_date, phone_number)" +
                    " VALUES ('"+ login +"', '"+ password +"', 'USER', '"+ email +"', '"+ information +"'," +
                    "TO_DATE('"+ formatForDateNow.format(date) +"', 'dd.mm.yyyy'), '"+ number +"')");
        }
        return "redirect:/";
    }
}