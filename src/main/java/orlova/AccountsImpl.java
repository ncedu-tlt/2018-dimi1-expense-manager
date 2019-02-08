package orlova;

import interfaces.Accounts;

import java.math.BigInteger;
import java.sql.*;
import java.util.Scanner;

public class AccountsImpl implements Accounts {
    private BigInteger accountId;
    private String accountNumber;
    private BigInteger personId;
    private String currency;
    private Double balance;
    private String description;
    private Connection connect;

    Scanner in = new Scanner(System.in);

    public AccountsImpl(Connection connect){ this.connect = connect; }

    @Override
    public void create() {
        String insertAccount = "INSERT INTO accounts (account_id, account_number, person_id_fk, currency, balance, description) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmtPers = connect.prepareStatement(insertAccount);
            prepareStmtPers.setInt(1, accountId.intValue());
            prepareStmtPers.setString(2, accountNumber);
            prepareStmtPers.setInt(3, personId.intValue());
            prepareStmtPers.setString(4, currency);
            prepareStmtPers.setDouble(5, balance);
            prepareStmtPers.setString(6, description);

            prepareStmtPers.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while entering information into the database table ACCOUNTS");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(){
            String checkBudget = "SELECT COUNT(*) AS cnt FROM budget WHERE account_id_fk = " + accountId.intValue();
            String checkPlanBudg = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE account_id_fk = " + accountId.intValue();
            DataBaseWork check = new DataBaseWork(connect);
            if(check.checkExist(checkBudget)!=0 || check.checkExist(checkPlanBudg)!=0){
                System.out.println("This record has a link in the other table(s).\nDelete all related entries first.");
                return;
            } else{
                try{
                    String deletAccount = "DELETE FROM accounts WHERE account_number = " +
                            accountNumber + " AND person_id_fk = " + personId.intValue();
                    Statement stmtDelPers = connect.createStatement();
                    stmtDelPers.executeUpdate(deletAccount);
                } catch(SQLException e) {
                    System.out.println("An error occured while deleting a record from the database table ACCOUNTS");
                    e.printStackTrace();
                }
            }
    }

    @Override
    public void update() {
        String updateAccount = "UPDATE accounts SET account_number = ?, person_id_fk = ?, " +
                "currency = ?, balance = ?, description = ? " +
                "WHERE account_id = ?";
        try{
            PreparedStatement preparedStmtPersonForUpdate =connect.prepareStatement(updateAccount);
            preparedStmtPersonForUpdate.setString(1, accountNumber);
            preparedStmtPersonForUpdate.setInt(2, personId.intValue());
            preparedStmtPersonForUpdate.setString(3, currency);
            preparedStmtPersonForUpdate.setDouble(4, balance);
            preparedStmtPersonForUpdate.setString(5, description);
            preparedStmtPersonForUpdate.setInt(6, accountId.intValue());

            preparedStmtPersonForUpdate.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while updating a record from the database table ACCOUNTS");
            e.printStackTrace();
        }
    }

    @Override
    public boolean load(BigInteger id) {
        try{
            Statement stmtCheckRecord = connect.createStatement();
            ResultSet resultCheckAcc = stmtCheckRecord.executeQuery("SELECT COUNT(*) AS cnt FROM accounts WHERE account_id = " + id.intValue());
            resultCheckAcc.next();
            if (resultCheckAcc.getInt("cnt") != 0){
                String dataAcc = "SELECT * FROM accounts WHERE account_id = " + id.intValue();
                Statement stmtAcc = connect.createStatement();
                ResultSet resultAcc = stmtAcc.executeQuery(dataAcc);
                while (resultAcc.next()){
                    accountId = id;
                    accountNumber = resultAcc.getString("account_number");
                    personId = BigInteger.valueOf(resultAcc.getInt("person_id_fk"));
                    currency = resultAcc.getString("currency");
                    balance = resultAcc.getDouble("balance");
                    description = resultAcc.getString("description");

                    System.out.println(String.format("accountId: %d| personId: %d| currency: %s| " +
                                    "balance: %.2f| description: %s|",
                            id, personId, currency, balance, description));
                }
                return true;
            } else {
                System.out.println("Account with the specified ID is not in the table ACCOUNTS");
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table ACCOUNTS");
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAccountNumberExist(String accNum, BigInteger persId) {
        try {
            PreparedStatement checkRecord = connect.prepareStatement("SELECT COUNT(*) AS cnt FROM accounts " +
                    "WHERE account_number = ? AND person_id_fk = ?");
            checkRecord.setString(1, accNum);
            checkRecord.setInt(2, persId.intValue());

            try (ResultSet checkRes = checkRecord.executeQuery()) {
                checkRes.next();
                if (checkRes.getInt("cnt") != 0) {
                    return true;
                } else {
                    System.out.println("You haven't got account with such id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAccountExists(BigInteger id) {
        String checkAccountId = "SELECT COUNT(*) AS cnt FROM accounts WHERE account_id = " + id.intValue();
        try {
            Statement stmtCheckRecord = connect.createStatement();
            ResultSet checkRes = stmtCheckRecord.executeQuery(checkAccountId);
            checkRes.next();
            if(checkRes.getInt("cnt") != 0){
                return true;
            } else {
                System.out.println("Account with the specified ID is not in the table ACCOUNTS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showTable(){
        String qwre = "SELECT * FROM accounts";
        BigInteger field1;
        int field3;
        String field2, field4, field6;
        Double field5;
        try {
            Statement stmtPers =connect.createStatement();
            ResultSet resultPers = stmtPers.executeQuery(qwre);
            while (resultPers.next()){
                field1 = BigInteger.valueOf(resultPers.getInt("account_id"));
                field2 = resultPers.getString("account_number");
                field3 = resultPers.getInt("person_id_fk");
                field4 = resultPers.getString("currency");
                field5 = resultPers.getDouble("balance");
                field6 = resultPers.getString("description");
                System.out.println(String.format("accountId: %5d| accountNumber: %16s| personId: %5d| currency: %4s| " +
                                "balance: %10.2f| description: %s",
                        field1, field2, field3, field4, field5, field6));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table ACCOUNTS");
            e.printStackTrace();
        }
    }

    @Override
    public BigInteger getAccountId() {
        return accountId;
    }

    public BigInteger findAccountId(String accNum, BigInteger persId){
        String qwr = "SELECT account_id AS id FROM accounts WHERE account_number = " + accNum + " AND person_id_fk = " + persId;
        try{
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            return BigInteger.valueOf(res.getInt("id"));
        } catch (SQLException e){
            System.out.println("An error occured while finding primary key in the database table ACCOUNTS");
            e.printStackTrace();
        }
        return BigInteger.valueOf(0);
    }

    public void setAccountId(BigInteger accountId) {
        this.accountId = accountId;
    }

    public void setAccountId(){
        String qwr = "SELECT max(account_id) AS id FROM accounts";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            accountId = BigInteger.valueOf(res.getInt("id") + 1);
        } catch (SQLException e) {
            System.out.println("An error occured while determining the primary key for an entry in the database table ACCOUNTS");
            e.printStackTrace();
        }
    }

    public String getAccountNumber(){ return accountNumber; }

    public void setAccountNumber(String accountNumber){
        this.accountNumber = accountNumber;
    }

    @Override
    public BigInteger getPersonId() {
        return personId;
    }

    public void setPersonId(BigInteger personId) {
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
    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
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
