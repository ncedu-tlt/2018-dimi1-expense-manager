package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TablesController {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TablesController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @RequestMapping("/tables")
    String home(ModelMap model) {
        DatabaseWork databaseObj = new DatabaseWork(jdbcTemplate);

        model.addAttribute("persons", databaseObj.getAllPersons());
        model.addAttribute("accounts", databaseObj.getAllAccounts());
        model.addAttribute("budget_types", databaseObj.getAllBudgetTypes());
        model.addAttribute("budgets", databaseObj.getAllBudgets());
        model.addAttribute("plan_budgets", databaseObj.getAllPlanBudgets());
        return "tables";
    }
}
