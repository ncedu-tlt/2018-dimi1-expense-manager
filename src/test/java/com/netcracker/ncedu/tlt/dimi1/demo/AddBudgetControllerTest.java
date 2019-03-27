package com.netcracker.ncedu.tlt.dimi1.demo;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetImpl;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class AddBudgetControllerTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    //TODO если есть возможность - запустить тест после старта tomcat на локал-хосте и запростить get("/")

    @org.junit.Test
    public void addBudgetTest() {

        Logger log = LoggerFactory.getLogger(AddBudgetControllerTest.class);

        BudgetImpl b = new BudgetImpl(jdbcTemplate);
        if(b == null){
            log.error("NPE", b);
        }
        b.createUniqId();
        b.setDescription("TEST");
        b.create();

        Integer id = b.getBudgetId();
        Assert.assertNotNull(id);

        /*BudgetImpl o = mock(BudgetImpl.class);
        o.createUniqId();
        o.setDescription("TEST");
        o.create();
        boolean res = o.load(1013);
        assertEquals(true, res);*/

    }
}