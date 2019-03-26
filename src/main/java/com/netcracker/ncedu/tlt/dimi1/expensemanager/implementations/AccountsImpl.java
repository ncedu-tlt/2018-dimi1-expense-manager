package com.netcracker.ncedu.tlt.dimi1.expensemanager.implementations;

import com.netcracker.ncedu.tlt.dimi1.expensemanager.interfaces.Accounts;
import com.netcracker.ncedu.tlt.dimi1.expensemanager.tools.DatabaseWork;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Scanner;

public class AccountsImpl implements Accounts
{
    private Integer accountId;
    private String accountNumber;
    private Integer personId;
    private String currency;
    private BigDecimal balance;
    private String description;
    private JdbcTemplate jdbcTemplate;

    Scanner in = new Scanner(System.in);

    public AccountsImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public void create() {
        String insertAccount = "INSERT INTO accounts (account_number, person_id_fk, currency, balance, description) " +
                "VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertAccount, accountNumber, personId, currency, balance, description);
    }

    @Override
    public void delete(){
        DatabaseWork check = new DatabaseWork(jdbcTemplate);
        String deleteAccount = "DELETE FROM accounts WHERE account_id= ? AND person_id_fk = ?";
        jdbcTemplate.update(deleteAccount, accountId, personId);
    }

    @Override
    public void update() {
        String updateAccount = "UPDATE accounts SET account_number = ?, person_id_fk = ?, " +
                "currency = ?, balance = ?, description = ? " +
                "WHERE account_id = ?";
        jdbcTemplate.update(updateAccount, accountNumber, personId, currency, balance, description, accountId);
    }

    @Override
    public boolean load(Integer id) {
        String checkExistAccount = "SELECT COUNT(*) AS cnt FROM accounts WHERE account_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistAccount, Integer.class, id);
        if(checkResult != 0){
            String dataAccount = "SELECT account_number, person_id_fk, currency, " +
                    "balance, description FROM accounts WHERE account_id = ?";
            Map result = jdbcTemplate.queryForMap(dataAccount, id);
            this.accountId = id;
            this.accountNumber = (String)result.get("ACCOUNT_NUMBER");
            this.personId = (Integer)result.get("PERSON_ID_FK");//Заменила тип на Integer
            this.currency = (String)result.get("CURRENCY");
            this.balance = (BigDecimal)result.get("BALANCE");//Зкаменила тип на BigDecimal
            this.description = (String)result.get("DESCRIPTION");
            return true;
        } else {
            System.out.println("Account with the specified ID is not in the table ACCOUNTS");
        }
        return false;
    }

    public boolean isAccountNumberExist(String accNum, Integer persId) {
        String checkAccountId = "SELECT COUNT(*) AS cnt FROM accounts " +
                "WHERE account_number = ? AND person_id_fk = ?";
        Integer checkExist = jdbcTemplate.queryForObject(checkAccountId, Integer.class, accNum, persId);
        if(checkExist != 0){
            return true;
        } else {
            System.out.println("You haven't got account with such id");
        }
        return false;
    }

    public boolean isAccountExists(Integer id) {
        String checkAccountId = "SELECT COUNT(*) AS cnt FROM accounts WHERE account_id = ?";
        Integer checkExist = jdbcTemplate.queryForObject(checkAccountId, Integer.class, id);
        if(checkExist != 0){
            return true;
        } else {
            System.out.println("Account with the specified ID is not in the table ACCOUNTS");
        }
        return false;
    }

    @Override
    public Integer getAccountId() {
        return accountId;
    }

    public Integer findAccountId(String accNum, Integer persId){
        String qwr = "SELECT account_id AS id FROM accounts WHERE account_number = ? AND person_id_fk = ?";
        Integer findAccId = jdbcTemplate.queryForObject(qwr, Integer.class, accNum, persId);
        if(findAccId != Integer.valueOf(0)){
            return findAccId;
        } else {
            System.out.println("Account with the specified ACCOUNT_NUMBER and PERSON_ID is not in the table ACCOUNTS");
        }
        return Integer.valueOf(0);
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public void createUniqId(){
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        accountId = dbObj.getUniqAccountId();
    }

    public String getAccountNumber(){ return accountNumber; }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    @Override
    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Override
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setCurrency() {
        System.out.println("Выберите валюту: ");
        System.out.println("1 - RUB");
        System.out.println("2 - USD");
        System.out.println("3 - EUR");
        System.out.println("4 - GBP");
        System.out.println("5 - другое");
        int choice = -1;
        try{
            choice = in.nextInt();
        } catch(Exception ex) {
            System.out.print("Вы ввели некорректное число. Повторите: ");
            choice = in.nextInt();
        }
        switch (choice) {
            case 1:
                currency = "RUB";
                break;
            case 2:
                currency = "USD";
                break;
            case 3:
                currency = "EUR";
                break;
            case 4:
                currency = "GBP";
                break;
            case 5:
                currency = in.nextLine();
                break;
                default:
                    System.out.println("Такого пункта нет. Повторите ввод: ");
                    try{
                        choice = in.nextInt();
                    } catch(Exception ex) {
                        System.out.print("Вы ввели некорректное число. Повторите: ");
                        choice = in.nextInt();
                    }
        }
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
