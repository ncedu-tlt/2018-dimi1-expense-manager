package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteBudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(DeleteBudgetTypeController.class);

    @Autowired
    DeleteBudgetTypeController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "deleteBudgetType")
    String deleteBudgetType(Integer budgetTypeId){
        BudgetTypeImpl budgetTypeForDelete = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForDelete.load(budgetTypeId)){
            budgetTypeForDelete.delete();
            log.info("Record removed from BUDGET_TYPE");
        } else {
            log.info("Deleting a budget type failed. The record with specified ID no longer exists in the table BudgetTypes");
        }
        return "redirect:/";
    }
}
