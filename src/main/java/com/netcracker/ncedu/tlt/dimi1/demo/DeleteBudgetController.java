package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeleteBudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(DeleteBudgetController.class);

    @Autowired
    DeleteBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "deleteBudget")
    String deleteBudget(Integer budgetId){
        BudgetImpl budgetForDelete = new BudgetImpl(jdbcTemplate);
        if(budgetForDelete.load(budgetId)){
            budgetForDelete.delete();
            log.info("Record removed from BUDGET");
        } else {
            log.info("Deleting a budget failed. The record with specified ID no longer exists in the table BUDGET");
        }
        return "redirect:/";
    }
}
