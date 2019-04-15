package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers.BudgetController;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.Budget;
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
public class DeleteBudgetControllerTest {

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
    public void deleteBudgetTest() {
        obj.deleteBudget(lastId);
        Budget objCheck = new BudgetImpl(jdbcTemplate);
        Assert.assertFalse(objCheck.load(lastId));

        obj.deleteBudget(lastId);
    }
}