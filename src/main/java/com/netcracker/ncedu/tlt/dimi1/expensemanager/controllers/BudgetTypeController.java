package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class BudgetTypeController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(BudgetTypeController.class);

    @Autowired
    BudgetTypeController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/addNewBudgetType")
    String addNewBudgetType(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "required") boolean required,
            @RequestParam(value = "checkMax") BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForAdd = new BudgetTypeImpl(jdbcTemplate);
        budgetTypeForAdd.createUniqId();
        budgetTypeForAdd.setGroupId(null);
        budgetTypeForAdd.setName(name);
        budgetTypeForAdd.setRequired(required);
        budgetTypeForAdd.setCheckMax(checkMax);
        budgetTypeForAdd.create();
        log.info("Record in BUDGET_TYPE was created");
        return "redirect:/";
    }

    @RequestMapping(value = "/addBudgetType")
    String addBudgetType(
            @RequestParam(value = "groupId") Integer groupId,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "required") boolean required,
            @RequestParam(value = "checkMax") BigDecimal checkMax){
        BudgetTypeImpl budgetTypeForAdd = new BudgetTypeImpl(jdbcTemplate);
        if(budgetTypeForAdd.isGroupExsist(groupId)){
            budgetTypeForAdd.createUniqId();
            if(budgetTypeForAdd.isGroupExsist(groupId)){
                budgetTypeForAdd.setGroupId(groupId);
                budgetTypeForAdd.setName(name);
                budgetTypeForAdd.setRequired(required);
                budgetTypeForAdd.setCheckMax(checkMax);
                budgetTypeForAdd.create();
            }
            log.info("Record in BUDGET_TYPE was created");
        } else {
            log.info("Adding a new budget type failed. The group with the specified id doesn't exist");
        }
        return "redirect:/";
    }

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
