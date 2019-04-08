package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.SelfDescribing;

//import org.hamcrest.SelfDescribing;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class AddBudgetTypeControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private BudgetTypeController obj;
    private Integer lastId;

    @Before
    public void setJdbcTemplate(){
        obj = new BudgetTypeController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
    }

    @org.junit.Test
    public void addNewBudgetType() throws Exception {
        obj.addNewBudgetType("GOOD TEST", null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT name FROM budget_type " +
                "WHERE budget_type_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST");
    }

    @Test
    public void addBudgetType() {
        obj.addBudgetType(4, "GOOD TEST2", null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT name FROM budget_type " +
                "WHERE budget_type_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST2");
    }
}
