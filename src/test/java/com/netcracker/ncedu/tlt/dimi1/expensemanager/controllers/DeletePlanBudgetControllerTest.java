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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class DeletePlanBudgetControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Integer lastId;
    private PlanBudgetController obj;

    @Before
    public void setJdbcTemplate(){
        obj = new PlanBudgetController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
    }

    @org.junit.Test
    public void deletePlanBudget() {
        obj.deletePlanBudget(lastId);
        PlanBudget objCheck = new PlanBudgetImpl(jdbcTemplate);
        Assert.assertFalse(objCheck.load(lastId));
        obj.deletePlanBudget(lastId-1);

        obj.deletePlanBudget(lastId);
    }
}