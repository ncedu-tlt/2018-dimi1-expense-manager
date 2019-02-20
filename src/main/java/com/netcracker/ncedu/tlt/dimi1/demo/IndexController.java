package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.DatabaseWork;
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
        DatabaseWork databaseObj = new DatabaseWork();

        model.addAttribute("persons", databaseObj.getAllPersons());
        model.addAttribute("accounts", databaseObj.getAllAccounts());
        model.addAttribute("budget_types", databaseObj.getAllBudgetTypes());
        model.addAttribute("budgets", databaseObj.getAllBudgets());
        model.addAttribute("plan_budgets", databaseObj.getAllPlanBudgets());
        return "index";
    }
}
