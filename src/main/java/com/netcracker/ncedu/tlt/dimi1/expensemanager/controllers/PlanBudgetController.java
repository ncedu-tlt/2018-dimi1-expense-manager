package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.AccountsImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.PlanBudgetImpl;
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
public class PlanBudgetController {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(PlanBudgetController.class);

    @Autowired
    public PlanBudgetController(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @RequestMapping(value = "/addPlanBudget")
    public String addPlanBudget(String operationType, Integer budgetTypeId, String description,
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
            } else if(operationDate != null && (regularMask == null || regularMask.equals(""))){
                log.info("Adding a new plan budget failed. Field REGULAR_MASK must be filled");
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

    @RequestMapping(value = "deletePlanBudget")
    public String deletePlanBudget(Integer budgetId){
        PlanBudgetImpl planBudgetForDelete = new PlanBudgetImpl(jdbcTemplate);
        if(planBudgetForDelete.load(budgetId)){
            planBudgetForDelete.delete();
            log.info("Record removed from PLAN_BUDGET");
        } else {
            log.info("Deleting a plan budget failed. The record with specified ID no longer exists in the table PLAN_BUDGET");
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/updatePlanBudget")
    public String updatePlanBudget(Integer planBudgetId, String operationType, Integer budgetTypeId, String description,
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
            } else if(operationDate != null && (regularMask == null || regularMask.equals(""))){
                log.info("Updating a plan budget failed. Field REGULAR_MASK must be filled");
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
