package com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.controllers.BudgetController;
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

    /*String operationType, description;
    Integer budgetTypeId, accountId;
    Date operationDate;
    BigDecimal chargeValue;
    Boolean expectedResult;

    public BudgetControllerTest(String operationType, Integer budgetTypeId, String description,
                                Integer accountId, Date operationDate, BigDecimal chargeValue,
                                Boolean result){
        this.operationType = operationType;
        this.budgetTypeId = budgetTypeId;
        this.description = description;
        this.accountId = accountId;
        this.operationDate = operationDate;
        this.chargeValue = chargeValue;
        this.expectedResult = result;

    }

    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][]{
                {null, 4, "GOOD TEST", 5, null, null, true},
                {null, 3, "WRONG TEST", 3, null, null, false}
        });
    }*/

    @org.junit.Test
    public void addBudgetTest() {

        //Boolean actualResult = false;
        obj.addBudget(null, 4, "GOOD TEST",
                5, null, null);
        lastId = jdbcTemplate.queryForObject("SELECT max(budget_id) FROM budget", Integer.class);
        String res = jdbcTemplate.queryForObject("SELECT description FROM budget " +
                "WHERE budget_id = ?", String.class, lastId);
        Assert.assertEquals(res, "GOOD TEST");
        /*if(res.get(res.size()-1) == "GOOD TEST"){
            actualResult=true;
        }
        Assert.assertEquals(expectedResult, actualResult);*/
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


/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /*Logger log = LoggerFactory.getLogger(BudgetControllerTest.class);

        BudgetImpl b = new BudgetImpl(jdbcTemplate);
        b.createUniqId();
        b.setDescription("GOOD TEST");
        b.setAccountId(5);
        b.create();
        List<Integer> res = jdbcTemplate.queryForList("SELECT budget_id FROM budget", Integer.class);
        Integer id = b.getBudgetId();
        //Assert.assertNotNull(id);
        if(b.load(id)){
            log.info("Record was created and added to the database");
        } else{
            log.info("Record wasn't created and didn't added to the database");
        }
        Assert.assertEquals(res.get(res.size()-1), id);

        b.createUniqId();
        b.setDescription("WRONG TEST");
        b.setAccountId(10);
        res = jdbcTemplate.queryForList("SELECT budget_id FROM budget", Integer.class);
        id = b.getBudgetId();
        if(b.load(id)){
            log.info("Record was created and added to the database");
        } else{
            log.info("Record wasn't created and didn't added to the database");
        }
        Assert.assertNotEquals(res.get(res.size()-1), id);*/
////////////////////////////////////////////////////////////////////////////////////////////////////
        /*BudgetImpl o = mock(BudgetImpl.class);
        o.createUniqId();
        o.setDescription("TEST");
        o.create();
        boolean res = o.load(1013);
        assertEquals(true, res);*/

    }
}