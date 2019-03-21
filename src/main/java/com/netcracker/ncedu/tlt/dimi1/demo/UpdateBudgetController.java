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
public class UpdateBudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(UpdateBudgetController.class);

    @Autowired
    UpdateBudgetController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
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
