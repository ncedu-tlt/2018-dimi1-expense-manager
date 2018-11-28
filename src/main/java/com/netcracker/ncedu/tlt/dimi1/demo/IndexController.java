package com.netcracker.ncedu.tlt.dimi1.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public IndexController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/")
    String home(ModelMap model) {
        model.addAttribute("cats", jdbcTemplate.queryForList("select * from cats"));
        return "index";
    }
}
