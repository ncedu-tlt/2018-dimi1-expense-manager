package com.netcracker.ncedu.tlt.dimi1.expensemanager.tools;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.*;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.reports.Report1;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.reports.Report2;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.reports.Report3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DatabaseWork {
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(DatabaseWork.class);

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

    public Boolean checkRegularMask(String regM){
        String[] mask = regM.split(" ");
        String seconds, minutes, hours, dayMonth, dayWeek;
        seconds = mask[0];
        minutes = mask[1];
        hours = mask[2];
        dayMonth = mask[3];
        dayWeek = mask[5];
        if(seconds.equals("*") && minutes.equals("*") && hours.equals("*")){
            log.info("Time must be set");
            return false;
        } else if(seconds.equals("*") || minutes.equals("*") || hours.equals("*")){
            log.info("Both HOURS and MINUTES must be set");
            return false;
        } else if(dayMonth.equals("*") && dayWeek.equals("*")){
            log.info("A DAYS_MONTH or DAYS_WEEK field must be filled");
            return false;
        } else if(!dayMonth.equals("*") && dayWeek.equals("*")){
            log.info("Incorrect DAYS_WEEK value. Either a specific value or '?' must be set");
            return false;
        } else if(dayMonth.equals("*") && !dayWeek.equals("*")){
            log.info("Incorrect DAYS_MONTH value. Either a specific value or '?' must be set");
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

    public List<Report3> getReport3(String startDate, String endDate){
        List<PlanBudget> planBudgetL = getAllPlanBudgets();
        List<Report3> report3L = new ArrayList<>();
        Report3 rep3 = new Report3(jdbcTemplate);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        LocalDateTime start = LocalDateTime.parse(startDate, dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(endDate, dateTimeFormatter);

        rep3.getReportRow(planBudgetL, report3L, Date.from(start.atZone(ZoneId.systemDefault()).toInstant()),
                Date.from(end.atZone(ZoneId.systemDefault()).toInstant()));
        return report3L;
    }
}

