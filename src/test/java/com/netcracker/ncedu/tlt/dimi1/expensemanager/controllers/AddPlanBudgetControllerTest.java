package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.SelfDescribing;

import java.time.LocalDate;
import java.util.Date;

//import org.hamcrest.SelfDescribing;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class AddPlanBudgetControllerTest {

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
    public void addPlanBudget() {
        Date d = new Date();
        obj.addPlanBudget(null, 4, "GOOD TEST", 2,
                d, null, "0 10 10 12 * 3", null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 4, "GOOD TEST2", 2,
                null, null, "0 10 10 12 * ?", null,
                d, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST2");

        obj.addPlanBudget(null, 4, "WRONG TEST", 2,
                d, null, "* * 10 12 * 3", null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 4, "WRONG TEST", 2,
                d, null, "* * * 12 * 3", null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 4, "WRONG TEST", 2,
                d, null, null, null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 4, "WRONG TEST", 2,
                null, null, null, null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 5, "WRONG TEST", 2,
                null, null, null, null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 4, "WRONG TEST", 12,
                null, null, null, null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");

        obj.addPlanBudget(null, 5, "WRONG TEST", 12,
                null, null, null, null,
                null, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(plan_budget_id) FROM plan_budget", Integer.class);
        res = jdbcTemplate.queryForObject("SELECT description FROM plan_budget " +
                "WHERE plan_budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "GOOD TEST");
    }
}