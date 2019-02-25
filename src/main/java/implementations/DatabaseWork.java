package implementations;

import interfaces.*;

import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWork {
    private Connection connect;
    private Statement stmt;

    public DatabaseWork(Connection connect){
        this.connect = connect;
    }
    public DatabaseWork() {
        try{
            Class.forName("org.h2.Driver");
            connect= DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt = connect.createStatement();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    public Integer checkExist(String query) {
        try {
            Statement checkStmt = connect.createStatement();
            ResultSet checkRes = checkStmt.executeQuery(query);
            checkRes.next();
            if (checkRes.getInt(1) != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("An error occured while checking child records in database tables");
            ex.printStackTrace();
        }
        return 0;
    }

    public Person getPersonByLogin(String login){
        BigInteger personId = BigInteger.valueOf(-1);
        String qwr = "SELECT person_id AS id FROM person where login = '" + login.trim() + "'";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            personId = BigInteger.valueOf(res.getInt("id"));
        } catch (SQLException e) {
            System.out.println("An error occurred while determining the ID of PERSON by the login entered");
            e.printStackTrace();
        }
        Person person = new PersonImpl(connect);
        person.load(personId);
        return person;
    }

    public List<Person> getAllPersons(){
        List<Person> persL = new ArrayList<>();
        BigInteger personId;
        String qwr = "SELECT person_id AS id FROM person";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                personId = BigInteger.valueOf(res.getInt("id"));
                Person person = new PersonImpl(connect);
                person.load(personId);
                persL.add(person);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persL;
    }

    public List<Accounts> getAllAccounts(){
        List<Accounts> accL = new ArrayList<>();
        BigInteger accountId;
        String qwr = "SELECT account_id AS id FROM accounts";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                accountId = BigInteger.valueOf(res.getInt("id"));
                Accounts account = new AccountsImpl(connect);
                account.load(accountId);
                accL.add(account);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return accL;
    }

    public List<BudgetType> getAllBudgetTypes(){
        List<BudgetType> budgetTypeL = new ArrayList<>();
        BigInteger budgetTypeId;
        String qwr = "SELECT budget_type_id AS id FROM budget_type";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                budgetTypeId = BigInteger.valueOf(res.getInt("id"));
                BudgetType budgetType = new BudgetTypeImpl(connect);
                budgetType.load(budgetTypeId);
                budgetTypeL.add(budgetType);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgetTypeL;
    }

    public List<Budget> getAllBudgets(){
        List<Budget> budgetL = new ArrayList<>();
        BigInteger budgetId;
        String qwr = "SELECT budget_id AS id FROM budget";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                budgetId = BigInteger.valueOf(res.getInt("id"));
                Budget budget = new BudgetImpl(connect);
                budget.load(budgetId);
                budgetL.add(budget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return budgetL;
    }

    public List<PlanBudget> getAllPlanBudgets(){
        List<PlanBudget> planBudgetL = new ArrayList<>();
        BigInteger planBudgetId;
        String qwr = "SELECT plan_budget_id AS id FROM plan_budget";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                planBudgetId = BigInteger.valueOf(res.getInt("id"));
                PlanBudget planBudget = new PlanBudgetImpl(connect);
                planBudget.load(planBudgetId);
                planBudgetL.add(planBudget);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planBudgetL;
    }

    public List<Report1> getReport1(){
        List<Report1> report1L = new ArrayList<>();
        Report1 repObj = new Report1(connect);
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
        Report1 repObj = new Report1(connect);
        repObj.setTotalSum();
        Double totSum = repObj.getTotalSum();
        return totSum;
    }

    public List<Report2> getReport2Required(){
        List<Report2> report2L = new ArrayList<>();
        Report2 repObj = new Report2(connect);
        List<Integer> expenseGroups = repObj.getAllExpenseGroups();
        for(int i=0; i<expenseGroups.size(); i++){
            repObj.getReportRow(expenseGroups.get(i), report2L, true);
        }
        return report2L;
    }

    public List<Report2> getReport2Unrequired(){
        List<Report2> report2L = new ArrayList<>();
        Report2 repObj = new Report2(connect);
        List<Integer> expenseGroups = repObj.getAllExpenseGroups();
        for(int i=0; i<expenseGroups.size(); i++){
            repObj.getReportRow(expenseGroups.get(i), report2L, false);
        }
        return report2L;
    }

    public Double getTotalSum(boolean required){
        Report2 repObj = new Report2(connect);
        repObj.setTotalSum(required);
        Double totSum = repObj.getTotalSum();
        return totSum;
    }

    public List<Report3> getReport3(){
        List<PlanBudget> planBudgetL = new ArrayList<>();
        String qwr = "SELECT * FROM plan_budget";
        try {
            ResultSet res = stmt.executeQuery(qwr);
            while(res.next()){
                PlanBudget plBObj = new PlanBudgetImpl(connect);
                plBObj.load(BigInteger.valueOf(res.getInt("plan_budget_id")));
                planBudgetL.add(plBObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<Report3> report3L = new ArrayList<>();
        Report3 rep3 = new Report3(connect);
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate startLocal = LocalDate.parse("03.01.2019", format);
        LocalDate endLocal = LocalDate.parse("01.04.2019", format);
        Date start = Date.valueOf(startLocal);
        Date end = Date.valueOf(endLocal);
        rep3.getReportRow(planBudgetL, report3L, start, end);
        return report3L;
    }
}
