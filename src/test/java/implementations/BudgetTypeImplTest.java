package implementations;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.ExpenseManagerApplication;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.BudgetTypeImpl;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.hamcrest.SelfDescribing;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ExpenseManagerApplication.class)
public class BudgetTypeImplTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @org.junit.Test
    public void create() {
        BudgetTypeImpl budgetType = new BudgetTypeImpl(jdbcTemplate);
        budgetType.createUniqId();
        budgetType.create();
        Integer budgetTypeId = budgetType.getBudgetTypeId();

        BudgetTypeImpl budgetType1Test = new BudgetTypeImpl(jdbcTemplate);
        assertTrue(budgetType1Test.load(budgetTypeId));
    }
}
