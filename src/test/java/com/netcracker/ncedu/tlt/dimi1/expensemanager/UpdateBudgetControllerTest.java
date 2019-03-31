package com.netcracker.ncedu.tlt.dimi1.expensemanager;

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

//import org.hamcrest.SelfDescribing;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class UpdateBudgetControllerTest {

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
    public void updateBudgetTest() {
        Budget objCheck = new BudgetImpl(jdbcTemplate);
        objCheck.load(lastId);
        obj.updateBudget(lastId, objCheck.getOperationType(), 7, "UPDATED",
                15, objCheck.getOperationDate(), objCheck.getChargeValue());
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updateBudget(lastId, objCheck.getOperationType(), 7, "UPDATED",
                1, objCheck.getOperationDate(), objCheck.getChargeValue());
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updateBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                15, objCheck.getOperationDate(), objCheck.getChargeValue());
        objCheck.load(lastId);
        Assert.assertNotEquals(objCheck.getDescription(), "UPDATED");

        obj.updateBudget(lastId, objCheck.getOperationType(), 4, "UPDATED",
                2, objCheck.getOperationDate(), objCheck.getChargeValue());
        objCheck.load(lastId);
        Assert.assertEquals(objCheck.getDescription(), "UPDATED");
    }
}