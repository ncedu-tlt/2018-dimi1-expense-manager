package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.PlanBudgetImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.PlanBudget;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.SelfDescribing;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

//import org.hamcrest.SelfDescribing;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class UpdatePlanBudgetControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private PlanBudgetController obj;
    private Integer lastId;

    @Before
    public void setJdbcTemplate(){
        obj = new PlanBudgetController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
    }

    @org.junit.Test
    public void updatePlanBudget() {
        Date d = new Date();
        PlanBudget objCheck = new PlanBudgetImpl(jdbcTemplate);
        objCheck.load(lastId);
        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 5, "UPDATED",
                7, null, objCheck.getChargeValue(), objCheck.getRegularMask(),
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, null, objCheck.getChargeValue(), objCheck.getRegularMask(),
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, d, objCheck.getChargeValue(), null,
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, null, objCheck.getChargeValue(), null,
                null, d, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, d, objCheck.getChargeValue(), "* * * 12 5 ?",
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, d, objCheck.getChargeValue(), "* 30 10 12 5 ?",
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updatePlanBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, d, objCheck.getChargeValue(), "0 10 30 12 5 ?",
                null, null, null, null);
        objCheck.load(lastId);
        Assert.assertEquals(objCheck.getDescription(), "UPDATED");
    }
}