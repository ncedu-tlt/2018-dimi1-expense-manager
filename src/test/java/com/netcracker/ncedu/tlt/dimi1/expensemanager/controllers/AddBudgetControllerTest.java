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

//import org.hamcrest.SelfDescribing;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class AddBudgetControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Integer lastId;
    private BudgetController obj;

    @Before
    public void setJdbcTemplate(){
        obj = new BudgetController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_id) FROM budget", Integer.class);
    }

    @org.junit.Test
    public void addBudgetTest() {

        obj.addBudget(null, 4, "GOOD TEST",
                5, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_id) FROM budget", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT description FROM budget " +
                "WHERE budget_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST");
        
        obj.addBudget(null, 4, "WRONG TEST",
                7, null, null);
        res = jdbcTemplate.queryForObject("SELECT description FROM budget " +
                "WHERE budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "WRONG TEST");

        obj.addBudget(null, 5, "WRONG TEST",
                2, null, null);
        res = jdbcTemplate.queryForObject("SELECT description FROM budget " +
                "WHERE budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "WRONG TEST");

        obj.addBudget(null, 5, "WRONG TEST",
                7, null, null);
        res = jdbcTemplate.queryForObject("SELECT description FROM budget " +
                "WHERE budget_id = ?", String.class, lastId);
        Assert.assertNotEquals(res, "WRONG TEST");

    }
}
