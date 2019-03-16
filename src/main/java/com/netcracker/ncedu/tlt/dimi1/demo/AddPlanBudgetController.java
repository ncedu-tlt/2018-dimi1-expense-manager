package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.*;
import interfaces.Accounts;
import interfaces.BudgetType;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.Date;

@Controller
public class AddPlanBudgetController {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(AddPlanBudgetController.class);

    @Autowired
    AddPlanBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/addPlanBudget")
    String addPlanBudget(String operationType, Integer budgetTypeId, String description,
                     Integer accountId, Date operationDate, BigDecimal chargeValue,
                     String regularMask, Integer repeatCount, Date startDate, Date endDate, Boolean spliter){
        PlanBudgetImpl planBudgetForAdd = new PlanBudgetImpl(jdbcTemplate);
        BudgetType objForCheckBT = new BudgetTypeImpl(jdbcTemplate);
        Accounts objForCheckAcc = new AccountsImpl(jdbcTemplate);
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        boolean isBudgTRight = objForCheckBT.load(budgetTypeId), isAccRight = objForCheckAcc.load(accountId);
        if(isBudgTRight && isAccRight){
            if(operationDate == null && startDate == null){
                log.info("Adding a new plan budget failed. An OPERATION_DATE or START_DATE field must be filled");
            } else if(operationDate != null && startDate != null){
                log.info("Adding a new plan budget failed. Only one field OPERATION_DATE or START_DATE must be filled");
            } else if(startDate != null && (regularMask == null || regularMask.equals(""))){
                log.info("Adding a new plan budget failed. Field REGULAR_MASK must be filled");
            } else {
                planBudgetForAdd.createUniqId();
                planBudgetForAdd.setOperationType(operationType);
                planBudgetForAdd.setBudgetTypeId(budgetTypeId);
                planBudgetForAdd.setDescription(description);
                planBudgetForAdd.setAccountId(accountId);
                planBudgetForAdd.setOperationDate(operationDate);
                planBudgetForAdd.setChargeValue(chargeValue);
                if(dbObj.checkRegularMask(regularMask)){
                    planBudgetForAdd.setRegularMask(regularMask);
                    planBudgetForAdd.setRepeatCount(repeatCount);
                    planBudgetForAdd.setStartDate(startDate);
                    planBudgetForAdd.setEndDate(endDate);
                    planBudgetForAdd.setSpliter(spliter);
                    planBudgetForAdd.create();
                    log.info("Record in PLAN_BUDGET was created");
                }
            }
        } else if(!isBudgTRight){
            log.info("Adding a new plan budget failed. Group with the specified ID doesn't exist in the table BUDGET_TYPE");
        } else if(!isAccRight){
            log.info("Adding a new plan budget failed. Account with the specified ID doesn't exist in the table ACCOUNTS");
        }
        return "redirect:/";
    }
}
