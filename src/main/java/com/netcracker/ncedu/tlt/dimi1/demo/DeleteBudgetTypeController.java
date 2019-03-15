package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigInteger;

public class DeleteBudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(DeleteBudgetTypeController.class);

    @Autowired
    DeleteBudgetTypeController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "deleteBudgetType")
    String deleteBudgetType(BigInteger budgetTypeId){
        BudgetTypeImpl budgetTypeForDelete = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForDelete.load(budgetTypeId)){
            budgetTypeForDelete.delete();
        } else {
            log.info("The record with specified ID no longer exists in the table BudgetTypes");
        }
        return "redirect:/";
    }
}
