package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.BudgetTypeImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.math.BigInteger;

@Controller
public class UpdateBudgetType {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(DeleteBudgetTypeController.class);

    @Autowired
    UpdateBudgetType(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "updateBudgetType")
    String updateBudgetType(BigInteger budgetTypeId, Integer groupId, String name, boolean isRequired, BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForUpdate = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForUpdate.load(budgetTypeId)){
            budgetTypeForUpdate.setBudgetTypeId(budgetTypeId);
            if(budgetTypeForUpdate.isGroupExsist(groupId)){
                budgetTypeForUpdate.setGroupId(groupId);
                budgetTypeForUpdate.setName(name);
                budgetTypeForUpdate.setRequired(isRequired);
                budgetTypeForUpdate.setCheckMax(checkMax);
                budgetTypeForUpdate.update();
            } else {
                log.info("The group with the specified id doesn't exist");
            }
        } else {
            log.info("Record with the specified ID doesn't exist in table BudgetTypes");
        }
        return "redirect:/";
    }

}
