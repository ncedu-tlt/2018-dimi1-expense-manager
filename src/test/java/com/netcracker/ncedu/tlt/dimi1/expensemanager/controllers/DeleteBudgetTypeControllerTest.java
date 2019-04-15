package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.BudgetType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.SelfDescribing;
//import org.hamcrest.SelfDescribing;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class DeleteBudgetTypeControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Integer lastId;
    private BudgetTypeController obj;

    @Before
    public void setJdbcTemplate(){
        obj = new BudgetTypeController(jdbcTemplate);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_type_id) FROM budget_type", Integer.class);
    }

    @org.junit.Test
    public void deleteBudgetType() {
        obj.deleteBudgetType(lastId);
        BudgetType objCheck = new BudgetTypeImpl(jdbcTemplate);
        Assert.assertFalse(objCheck.load(lastId));
        obj.deleteBudgetType(lastId-1);

        obj.deleteBudgetType(lastId);
    }

}