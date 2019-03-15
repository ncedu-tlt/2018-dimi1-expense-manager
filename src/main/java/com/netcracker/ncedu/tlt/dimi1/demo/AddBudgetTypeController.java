package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import implementations.DatabaseWork;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
public class AddBudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(DeleteBudgetTypeController.class);

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
        return "redirect:/";
    }

    @RequestMapping(value = "/addBudgetType")
    String addBudgetType(Integer groupId, String name, boolean isRequired, BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForAdd = new BudgetTypeImpl(jdbcTemplate);
        budgetTypeForAdd.createUniqId();
        budgetTypeForAdd.setGroupId(groupId);
        budgetTypeForAdd.setName(name);
        budgetTypeForAdd.setRequired(isRequired);
        budgetTypeForAdd.setCheckMax(checkMax);
        budgetTypeForAdd.create();
        return "redirect:/";
    }
}
