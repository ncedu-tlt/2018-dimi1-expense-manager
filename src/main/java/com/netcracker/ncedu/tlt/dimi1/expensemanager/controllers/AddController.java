package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Deprecated
@Controller
public class AddController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AddController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/add")
    String add(String name) {
        jdbcTemplate.update("insert into cats(name) values (?)", name);
        return "redirect:/";
    }

}
