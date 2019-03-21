package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.PlanBudgetImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DeletePlanBudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(DeletePlanBudgetController.class);

    @Autowired
    DeletePlanBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "deletePlanBudget")
    String deletePlanBudget(Integer budgetId){
        PlanBudgetImpl planBudgetForDelete = new PlanBudgetImpl(jdbcTemplate);
        if(planBudgetForDelete.load(budgetId)){
            planBudgetForDelete.delete();
            log.info("Record removed from PLAN_BUDGET");
        } else {
            log.info("Deleting a plan budget failed. The record with specified ID no longer exists in the table PLAN_BUDGET");
        }
        return "redirect:/";
    }
}
