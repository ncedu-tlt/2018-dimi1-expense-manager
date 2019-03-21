package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.AccountsImpl;
import implementations.BudgetImpl;
import implementations.BudgetTypeImpl;
import interfaces.Accounts;
import interfaces.BudgetType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class AddBudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(AddBudgetController.class);

    @Autowired
    AddBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

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

}
