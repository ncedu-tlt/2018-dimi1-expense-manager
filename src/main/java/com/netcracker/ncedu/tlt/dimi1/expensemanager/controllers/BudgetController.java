package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.AccountsImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.Accounts;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.BudgetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class BudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(BudgetController.class);

    @Autowired
    BudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/addBudget")
    String addBudget(String operationType, Integer budgetTypeId, String description,
                     Integer accountId, Date operationDate, BigDecimal chargeValue){
        BudgetImpl budgetForAdd = new BudgetImpl(jdbcTemplate);
        BudgetType objForCheckBT = new BudgetTypeImpl(jdbcTemplate);
        Accounts objForCheckAcc = new AccountsImpl(jdbcTemplate);
        boolean isBudgTRight = objForCheckBT.load(budgetTypeId), isAccRight = objForCheckAcc.load(accountId);
        if(isBudgTRight && isAccRight){
            budgetForAdd.createUniqId();
            budgetForAdd.setOperationType(operationType);
            budgetForAdd.setBudgetTypeId(budgetTypeId);
            budgetForAdd.setDescription(description);
            budgetForAdd.setAccountId(accountId);
            budgetForAdd.setOperationDate(operationDate);
            budgetForAdd.setChargeValue(chargeValue);
            budgetForAdd.create();
            log.info("Record in BUDGET was created");
        } else if(!isBudgTRight){
            log.info("Adding a new budget failed. Group with the specified ID doesn't exist in the table BUDGET_TYPE");
        } else if(!isAccRight){
            log.info("Adding a new budget failed. Account with the specified ID doesn't exist in the table ACCOUNTS");
        }
        return "redirect:/";
    }

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

    @RequestMapping(value = "updateBudget")
    String updateBudget(Integer budgetId, String operationType, Integer budgetTypeId, String description,
                        Integer accountId, Date operationDate, BigDecimal chargeValue) {
        BudgetImpl budgetForUpdate = new BudgetImpl(jdbcTemplate);
        BudgetType objForCheckBT = new BudgetTypeImpl(jdbcTemplate);
        Accounts objForCheckAcc = new AccountsImpl(jdbcTemplate);
        boolean isBudgTRight = objForCheckBT.load(budgetTypeId), isAccRight = objForCheckAcc.load(accountId);
        if (budgetForUpdate.load(budgetId)) {
            if (isBudgTRight && isAccRight) {
                budgetForUpdate.setBudgetId(budgetId);
                budgetForUpdate.setOperationType(operationType);
                budgetForUpdate.setBudgetTypeId(budgetTypeId);
                budgetForUpdate.setDescription(description);
                budgetForUpdate.setAccountId(accountId);
                budgetForUpdate.setOperationDate(operationDate);
                budgetForUpdate.setChargeValue(chargeValue);
                budgetForUpdate.update();
                log.info("Record in BUDGET was updated");
            } else if (!isBudgTRight) {
                log.info("Updating a budget failed. Group with the specified ID doesn't exist in the table BUDGET_TYPE");
            } else if (!isAccRight) {
                log.info("Updating a budget failed. Account with the specified ID doesn't exist in the table ACCOUNTS");
            }
        } else {
            log.info("Updating a budget failed. Record with the specified ID doesn't exist in table BUDGET");
        }
        return "redirect:/";
    }

}
