package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.BudgetType;
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
public class UpdateBudgetTypeControllerTest {

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
    public void updateBudgetType() {
        BudgetType objCheck = new BudgetTypeImpl(jdbcTemplate);
        objCheck.load(lastId);
        System.out.println(lastId);
        obj.updateBudgetType(lastId, 5, "UPDATED",
                objCheck.getRequired(), objCheck.getCheckMax());
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getName(), "UPDATED");

        obj.updateBudgetType(lastId+1, objCheck.getBudgetTypeId(), "UPDATED",
                objCheck.getRequired(), objCheck.getCheckMax());
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getName(), "UPDATED");

        obj.updateBudgetType(lastId, 4, "UPDATED",
                objCheck.getRequired(), objCheck.getCheckMax());
        objCheck.load(lastId);
        Assert.assertEquals(objCheck.getName(), "UPDATED");
    }
}