package com.netcracker.ncedu.tlt.dimi1.expensemanager.tools;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.reports.*;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class DatabaseWork {
    private JdbcTemplate jdbcTemplate;

    public DatabaseWork(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public Integer checkExist(String query, Integer id) {
        Integer checkResult = jdbcTemplate.queryForObject(query, Integer.class, id);
        if(checkResult != 0){
            return 1;
        }
        return 0;
    }

    public Integer getUniqPersonId(){
        String qwr = "SELECT max(person_id) AS id FROM person";
        Integer uniqPersonId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return uniqPersonId+1;
    }

    public Integer getUniqAccountId(){
        String qwr = "SELECT max(account_id) AS id FROM accounts";
        Integer uniqAccountId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return uniqAccountId+1;
    }

    public Integer getUniqBudgetTypeId(){
        String qwr = "SELECT max(budget_type_id) AS id FROM budget_type";
        Integer uniqBudgetTypeId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return uniqBudgetTypeId+1;
    }

    public Integer getUniqBudgetId(){
        String qwr = "SELECT max(budget_id) AS id FROM budget";
        Integer uniqBudgetId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return uniqBudgetId+1;
    }

    public Integer getUniqPlanBudgetId(){
        String qwr = "SELECT max(plan_budget_id) AS id FROM plan_budget";
        Integer uniqPlanBudgetId = jdbcTemplate.queryForObject(qwr, Integer.class);
        return uniqPlanBudgetId+1;
    }

    public Person getPersonByLogin(String login){
        String sql = "SELECT person_id AS id FROM person where login = ?";
        Integer personId = jdbcTemplate.queryForObject(sql, Integer.class, login);
        Person person = new PersonImpl(jdbcTemplate);
        person.load(personId);
        return person;
    }

    public List<Person> getAllPersons(){
        List<Person> persL = new ArrayList<>();

        String qwr = "SELECT person_id AS id FROM person";
        List<Integer> personsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer personId : personsIds){
            Person person = new PersonImpl(jdbcTemplate);
            person.load(personId);
            persL.add(person);
        }
        return persL;
    }

    public List<Accounts> getAllAccounts(){
        List<Accounts> accL = new ArrayList<>();

        String qwr = "SELECT account_id AS id FROM accounts";
        List<Integer> accountsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer accountId : accountsIds){
            Accounts account = new AccountsImpl(jdbcTemplate);
            account.load(accountId);
            accL.add(account);
        }
        return accL;
    }

    public List<BudgetType> getAllBudgetTypes(){
        List<BudgetType> budgetTypeL = new ArrayList<>();

        String qwr = "SELECT budget_type_id AS id FROM budget_type";
        List<Integer> budgetTypesIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer budgetTypeId : budgetTypesIds){
            BudgetType budgetType = new BudgetTypeImpl(jdbcTemplate);
            budgetType.load(budgetTypeId);
            budgetTypeL.add(budgetType);
        }
        return budgetTypeL;
    }

    public List<Budget> getAllBudgets(){
        List<Budget> budgetL = new ArrayList<>();

        String qwr = "SELECT budget_id AS id FROM budget";
        List<Integer> budgetsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer budgetId : budgetsIds){
            Budget budget = new BudgetImpl(jdbcTemplate);
            budget.load(budgetId);
            budgetL.add(budget);
        }
        return budgetL;
    }

    public List<PlanBudget> getAllPlanBudgets(){
        List<PlanBudget> planBudgetL = new ArrayList<>();

        String qwr = "SELECT plan_budget_id AS id FROM plan_budget";
        List<Integer> planBudgetsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer planBudgetId : planBudgetsIds){
            PlanBudget planBudget = new PlanBudgetImpl(jdbcTemplate);
            planBudget.load(planBudgetId);
            planBudgetL.add(planBudget);
        }
        return planBudgetL;
    }

    public boolean checkLogin(String sql) {
        List<String> list = jdbcTemplate.queryForList(sql, String.class);
        if(list.isEmpty()){
            return false;
        }
        return true;
    }

    public boolean checkAccountCardNumber(String personId, String cardNumber)
    {
        Person person = getPersonByLogin(personId);
        String sql = "select account_number from accounts where person_id_fk = '"+ person.getPersonId() +"'" +
                " and account_number = '"+ cardNumber +"'";
        List<String> list = jdbcTemplate.queryForList(sql, String.class);

        if(list.isEmpty())
        {
            return false;
        }
        return true;
    }

    public List<Accounts> getByPersonIdAccounts(String personId)
    {
        List<Accounts> listAccounts = new ArrayList<Accounts>();
        Person person = getPersonByLogin(personId);
        String qwr = "SELECT account_id AS id FROM accounts where person_id_fk = '"+ person.getPersonId() + "'";
        List<Integer> accountsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer accountId : accountsIds){
            Accounts account = new AccountsImpl(jdbcTemplate);
            account.load(accountId);
            listAccounts.add(account);
        }
        return listAccounts;
    }

    public void deleteAccount(String personId, String id)
    {
        Person person = getPersonByLogin(personId);
        String qwr = "SELECT account_id AS id FROM accounts where person_id_fk = '"+ person.getPersonId() + "' and account_id = '"+ id +"'";
        List<Integer> accountsIds = jdbcTemplate.queryForList(qwr, Integer.class);
        for(Integer accountId : accountsIds){
            Accounts account = new AccountsImpl(jdbcTemplate);
            account.load(accountId);
            account.delete();
        }
    }

    public void addAccount(String personId, String cardNumber, String currency, BigDecimal balance, String description)
    {
        Person person = getPersonByLogin(personId);
        AccountsImpl accounts = new AccountsImpl(jdbcTemplate);
        accounts.setPersonId(person.getPersonId());
        accounts.setAccountNumber(cardNumber);
        accounts.setCurrency(currency);
        accounts.setBalance(balance);
        accounts.setDescription(description);
        accounts.create();
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
