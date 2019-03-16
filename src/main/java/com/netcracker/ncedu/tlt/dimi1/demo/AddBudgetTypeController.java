package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class AddBudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(AddBudgetTypeController.class);

    @Autowired
    AddBudgetTypeController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/addNewBudgetType")
    String addNewBudgetType(String name, boolean isRequired, BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForAdd = new BudgetTypeImpl(jdbcTemplate);
        budgetTypeForAdd.createUniqId();
        budgetTypeForAdd.setGroupId(null);
        budgetTypeForAdd.setName(name);
        budgetTypeForAdd.setRequired(isRequired);
        budgetTypeForAdd.setCheckMax(checkMax);
        budgetTypeForAdd.create();
        log.info("Record in BUDGET_TYPE was created");
        return "redirect:/";
    }

    @RequestMapping(value = "/addBudgetType")
    String addBudgetType(Integer groupId, String name, boolean isRequired, BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForAdd = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForAdd.isGroupExsist(groupId)){
            budgetTypeForAdd.createUniqId();
            budgetTypeForAdd.setGroupId(groupId);
            budgetTypeForAdd.setName(name);
            budgetTypeForAdd.setRequired(isRequired);
            budgetTypeForAdd.setCheckMax(checkMax);
            budgetTypeForAdd.create();
            log.info("Record in BUDGET_TYPE was created");
        } else {
            log.info("Adding a new budget type failed. The group with the specified id doesn't exist");
        }
        return "redirect:/";
    }
}
