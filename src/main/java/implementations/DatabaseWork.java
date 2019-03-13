package implementations;

import interfaces.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseWork {
    private JdbcTemplate jdbcTemplate;

    public DatabaseWork(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer checkExist(String query, BigInteger id) {
        Integer checkResult = jdbcTemplate.queryForObject(query, Integer.class, id);
        if(checkResult != 0){
            return 1;
        }
        return 0;
    }

    public BigInteger getUniqPersonId(){
        String qwr = "SELECT max(person_id) AS id FROM person";
        Integer uniqPersonId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return BigInteger.valueOf(uniqPersonId+1);
    }

    public BigInteger getUniqAccountId(){
        String qwr = "SELECT max(account_id) AS id FROM accounts";
        Integer uniqAccountId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return BigInteger.valueOf(uniqAccountId+1);
    }

    public BigInteger getUniqBudgetTypeId(){
        String qwr = "SELECT max(budget_type_id) AS id FROM budget_type";
        Integer uniqBudgetTypeId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return BigInteger.valueOf(uniqBudgetTypeId+1);
    }

    public BigInteger getUniqBudgetId(){
        String qwr = "SELECT max(budget_id) AS id FROM budget";
        Integer uniqBudgetId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return BigInteger.valueOf(uniqBudgetId+1);
    }

    public BigInteger getUniqPlanBudgetId(){
        String qwr = "SELECT max(plan_budget_id) AS id FROM plan_budget";
        Integer uniqPlanBudgetId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return BigInteger.valueOf(uniqPlanBudgetId+1);
    }

    public Person getPersonByLogin(String login){
        String sql = "SELECT person_id AS id FROM person where login = ?";
        BigInteger personId = jdbcTemplate.queryForObject(sql, BigInteger.class, login);
        Person person = new PersonImpl(jdbcTemplate);
        person.load(personId);
        return person;
    }

    public List<Person> getAllPersons(){
        List<Person> persL = new ArrayList<>();

        String qwr = "SELECT person_id AS id FROM person";
        List<BigInteger> personsIds = jdbcTemplate.queryForList(qwr, BigInteger.class);
        for(BigInteger personId : personsIds){
            Person person = new PersonImpl(jdbcTemplate);
            person.load(personId);
            persL.add(person);
        }
        return persL;
    }

    public List<Accounts> getAllAccounts(){
        List<Accounts> accL = new ArrayList<>();

        String qwr = "SELECT account_id AS id FROM accounts";
        List<BigInteger> accountsIds = jdbcTemplate.queryForList(qwr, BigInteger.class);
        for(BigInteger accountId : accountsIds){
            Accounts account = new AccountsImpl(jdbcTemplate);
            account.load(accountId);
            accL.add(account);
        }
        return accL;
    }

    public List<BudgetType> getAllBudgetTypes(){
        List<BudgetType> budgetTypeL = new ArrayList<>();

        String qwr = "SELECT budget_type_id AS id FROM budget_type";
        List<BigInteger> budgetTypesIds = jdbcTemplate.queryForList(qwr, BigInteger.class);
        for(BigInteger budgetTypeId : budgetTypesIds){
            BudgetType budgetType = new BudgetTypeImpl(jdbcTemplate);
            budgetType.load(budgetTypeId);
            budgetTypeL.add(budgetType);
        }
        return budgetTypeL;
    }

    public List<Budget> getAllBudgets(){
        List<Budget> budgetL = new ArrayList<>();

        String qwr = "SELECT budget_id AS id FROM budget";
        List<BigInteger> budgetsIds = jdbcTemplate.queryForList(qwr, BigInteger.class);
        for(BigInteger budgetId : budgetsIds){
            Budget budget = new BudgetImpl(jdbcTemplate);
            budget.load(budgetId);
            budgetL.add(budget);
        }
        return budgetL;
    }

    public List<PlanBudget> getAllPlanBudgets(){
        List<PlanBudget> planBudgetL = new ArrayList<>();

        String qwr = "SELECT plan_budget_id AS id FROM plan_budget";
        List<BigInteger> planBudgetsIds = jdbcTemplate.queryForList(qwr, BigInteger.class);
        for(BigInteger planBudgetId : planBudgetsIds){
            PlanBudget planBudget = new PlanBudgetImpl(jdbcTemplate);
            planBudget.load(planBudgetId);
            planBudgetL.add(planBudget);
        }
        return planBudgetL;
    }

    public boolean checkLogin(String sql, String login) {
        List<String> result = jdbcTemplate.query(sql, new RowMapper(){
            public Object mapRow(ResultSet res, int rowNum) throws SQLException{
                return res.getString(1);
            }
        }, login);
        if(result.isEmpty()){
            return false;
        }
        return true;
    }

    public List<Report1> getReport1(){
        List<Report1> report1L = new ArrayList<>();
        Report1 repObj = new Report1(jdbcTemplate);
        List<Integer> expenseGroups = repObj.getAllExpenseGroups();
        for(int i=0; i<expenseGroups.size(); i++){
            repObj.getReportRow(expenseGroups.get(i), report1L);
        }
        repObj.setTotalSum();
        for(int i=0; i<report1L.size(); i++){
            report1L.get(i).setPercent(report1L.get(i).getSum(), repObj.getTotalSum());
        }
        return report1L;
    }

    public Double getTotalSum(){
        Report1 repObj = new Report1(jdbcTemplate);
        repObj.setTotalSum();
        Double totSum = repObj.getTotalSum();
        return totSum;
    }

    public List<Report2> getReport2Required(){
        List<Report2> report2L = new ArrayList<>();
        Report2 repObj = new Report2(jdbcTemplate);
        List<Integer> expenseGroups = repObj.getAllExpenseGroups();
        for(int i=0; i<expenseGroups.size(); i++){
            repObj.getReportRow(expenseGroups.get(i), report2L, true);
        }
        return report2L;
    }

    public List<Report2> getReport2Unrequired(){
        List<Report2> report2L = new ArrayList<>();
        Report2 repObj = new Report2(jdbcTemplate);
        List<Integer> expenseGroups = repObj.getAllExpenseGroups();
        for(int i=0; i<expenseGroups.size(); i++){
            repObj.getReportRow(expenseGroups.get(i), report2L, false);
        }
        return report2L;
    }

    public Double getTotalSum(boolean required){
        Report2 repObj = new Report2(jdbcTemplate);
        repObj.setTotalSum(required);
        Double totSum = repObj.getTotalSum();
        return totSum;
    }

    public List<Report3> getReport3(){
        List<PlanBudget> planBudgetL = getAllPlanBudgets();
        List<Report3> report3L = new ArrayList<>();
        Report3 rep3 = new Report3(jdbcTemplate);
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 2);
        calendar.set(Calendar.DAY_OF_MONTH, 2);
        calendar.set(Calendar.HOUR, 22);
        calendar.set(Calendar.MINUTE, 30);
        java.util.Date start = calendar.getTime();

        calendar.set(Calendar.YEAR, 2019);
        calendar.set(Calendar.MONTH, 4);
        calendar.set(Calendar.DAY_OF_MONTH, 3);
        calendar.set(Calendar.HOUR, 3);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        java.util.Date end = calendar.getTime();

        rep3.getReportRow(planBudgetL, report3L, start, end);
        return report3L;
    }
}

