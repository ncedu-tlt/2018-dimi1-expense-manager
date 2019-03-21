package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

@Controller
public class UpdateBudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(UpdateBudgetTypeController.class);

    @Autowired
    UpdateBudgetTypeController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "updateBudgetType")
    String updateBudgetType(Integer budgetTypeId, Integer groupId, String name, boolean isRequired, BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForUpdate = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForUpdate.load(budgetTypeId)){
            budgetTypeForUpdate.setBudgetTypeId(budgetTypeId);
            if(budgetTypeForUpdate.isGroupExsist(groupId)){
                budgetTypeForUpdate.setGroupId(groupId);
                budgetTypeForUpdate.setName(name);
                budgetTypeForUpdate.setRequired(isRequired);
                budgetTypeForUpdate.setCheckMax(checkMax);
                budgetTypeForUpdate.update();
                log.info("Record in BUDGET_TYPE was updated");
            } else {
                log.info("Updating a budget type failed. The group with the specified id doesn't exist");
            }
        } else {
            log.info("Updating a budget type failed. Record with the specified ID doesn't exist in table BudgetTypes");
        }
        return "redirect:/";
    }

}
