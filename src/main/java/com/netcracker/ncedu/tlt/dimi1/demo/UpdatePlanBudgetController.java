package com.netcracker.ncedu.tlt.dimi1.demo;

import implementations.AccountsImpl;
import implementations.BudgetTypeImpl;
import implementations.DatabaseWork;
import implementations.PlanBudgetImpl;
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
public class UpdatePlanBudgetController {
    private JdbcTemplate jdbcTemplate;
    private static final Logger log = Logger.getLogger(UpdateBudgetController.class);

    @Autowired
    UpdatePlanBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/updatePlanBudget")
    String updatePlanBudget(Integer planBudgetId, String operationType, Integer budgetTypeId, String description,
                         Integer accountId, Date operationDate, BigDecimal chargeValue,
                         String regularMask, Integer repeatCount, Date startDate, Date endDate, Boolean spliter){
        PlanBudgetImpl planBudgetForUpdate = new PlanBudgetImpl(jdbcTemplate);
        BudgetType objForCheckBT = new BudgetTypeImpl(jdbcTemplate);
        Accounts objForCheckAcc = new AccountsImpl(jdbcTemplate);
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        boolean isBudgTRight = objForCheckBT.load(budgetTypeId), isAccRight = objForCheckAcc.load(accountId);
        if(isBudgTRight && isAccRight){
            if(operationDate == null && startDate == null){
                log.info("Updating a plan budget failed. An OPERATION_DATE or START_DATE field must be filled");
            } else if(operationDate != null && startDate != null){
                log.info("Updating a plan budget failed. Only one field OPERATION_DATE or START_DATE must be filled");
            } else if(startDate != null && (regularMask == null || regularMask.equals(""))){
                log.info("Updating a plan budget failed. Field REGULAR_MASK must be filled");
            } else {
                planBudgetForUpdate.setPlanBudgetId(planBudgetId);
                planBudgetForUpdate.setOperationType(operationType);
                planBudgetForUpdate.setBudgetTypeId(budgetTypeId);
                planBudgetForUpdate.setDescription(description);
                planBudgetForUpdate.setAccountId(accountId);
                planBudgetForUpdate.setOperationDate(operationDate);
                planBudgetForUpdate.setChargeValue(chargeValue);
                if(dbObj.checkRegularMask(regularMask)){
                    planBudgetForUpdate.setRegularMask(regularMask);
                    planBudgetForUpdate.setRepeatCount(repeatCount);
                    planBudgetForUpdate.setStartDate(startDate);
                    planBudgetForUpdate.setEndDate(endDate);
                    planBudgetForUpdate.setSpliter(spliter);
                    planBudgetForUpdate.update();
                    log.info("Record in PLAN_BUDGET was updated");
                }
            }
        } else if(!isBudgTRight){
            log.info("Updating a plan budget failed. Group with the specified ID doesn't exist in the table BUDGET_TYPE");
        } else if(!isAccRight){
            log.info("Updating a plan budget failed. Account with the specified ID doesn't exist in the table ACCOUNTS");
        }
        return "redirect:/";
    }
}
