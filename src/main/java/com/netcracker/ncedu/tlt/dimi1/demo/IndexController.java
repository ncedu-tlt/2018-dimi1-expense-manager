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
        model.addAttribute("persons", jdbcTemplate.queryForList("select * from person"));
        model.addAttribute("accounts", jdbcTemplate.queryForList("select * from accounts"));
        model.addAttribute("budget_types", jdbcTemplate.queryForList("select * from budget_type"));
        model.addAttribute("budgets", jdbcTemplate.queryForList("select * from budget"));
        model.addAttribute("plan_budgets", jdbcTemplate.queryForList("select * from plan_budget"));
        return "index";
    }
}
