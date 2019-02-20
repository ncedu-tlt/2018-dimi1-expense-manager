package implementations;

import interfaces.Budget;
import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class BudgetImpl implements Budget {
    private BigInteger budgetId;
    private String operationType;
    private BigInteger budgetTypeId;
    private String description;
    private BigInteger accountId;
    private Date operationDate;
    private Double chargeValue;
    private Connection connect;

    public BudgetImpl(Connection connect){ this.connect = connect; }

    @Override
    public void create() {
        String insertBudget = "INSERT INTO budget (budget_id, operation_type, budget_type_id_fk, description, " +
                "account_id_fk, operation_date, charge_value) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmtBudg = connect.prepareStatement(insertBudget);
            prepareStmtBudg.setInt(1, budgetId.intValue());
            prepareStmtBudg.setString(2, operationType);
            prepareStmtBudg.setInt(3, budgetTypeId.intValue());
            prepareStmtBudg.setString(4, description);
            prepareStmtBudg.setInt(5, accountId.intValue());
            prepareStmtBudg.setObject(6, operationDate);
            prepareStmtBudg.setDouble(7, chargeValue);

            prepareStmtBudg.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while entering information into the database table BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try{
            String deletBudget = "DELETE FROM budget WHERE budget_id = " + budgetId.intValue();
            Statement stmtDelBudget = connect.createStatement();
            stmtDelBudget.executeUpdate(deletBudget);

        } catch(SQLException e) {
            System.out.println("An error occured while deleting a record from the database table BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        String updateBudg = "UPDATE budget SET operation_type = ?, budget_type_id_fk = ?, description = ?, " +
                "account_id_fk = ?, operation_date = ?, charge_value = ? " +
                "WHERE budget_id = ?";
        try{
            PreparedStatement prepareStmtBudgForUpdate = connect.prepareStatement(updateBudg);
            prepareStmtBudgForUpdate.setString(1, operationType);
            prepareStmtBudgForUpdate.setInt(2, budgetTypeId.intValue());
            prepareStmtBudgForUpdate.setString(3, description);
            prepareStmtBudgForUpdate.setInt(4, accountId.intValue());
            prepareStmtBudgForUpdate.setObject(5, operationDate);
            prepareStmtBudgForUpdate.setDouble(6, chargeValue);
            prepareStmtBudgForUpdate.setInt(7, budgetId.intValue());

            prepareStmtBudgForUpdate.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while updating a record from the database table BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public boolean load(BigInteger id) {
        try{
            Statement stmtCheckRecord = connect.createStatement();
            ResultSet resultCheckBudget = stmtCheckRecord.executeQuery("SELECT COUNT(*) AS cnt FROM budget WHERE budget_id = " + id.intValue());
            resultCheckBudget.next();
            if (resultCheckBudget.getInt("cnt") != 0){
                String dataBudget = "SELECT * FROM budget WHERE budget_id = " + id.intValue();
                Statement stmtBudg = connect.createStatement();
                ResultSet resultBudg = stmtBudg.executeQuery(dataBudget);
                while (resultBudg.next()){
                    budgetId = id;
                    operationType = resultBudg.getString("operation_type");
                    budgetTypeId = BigInteger.valueOf(resultBudg.getInt("budget_type_id_fk"));
                    description = resultBudg.getString("description");
                    accountId = BigInteger.valueOf(resultBudg.getInt("account_id_fk"));
                    operationDate = resultBudg.getDate("operation_date");
                    chargeValue = resultBudg.getDouble("charge_value");
                }
                return true;
            } else {
                System.out.println("Budget with the specified ID is not in the table BUDGET");
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table BUDGET");
            e.printStackTrace();
        }
        return false;
    }

    public void showTable() {
        String qwre = "SELECT * FROM budget";
        BigInteger field1, field3, field5;
        Double field7;
        String field2, field4;
        java.sql.Date field6;
        try {
            Statement stmtBudg =connect.createStatement();
            ResultSet resultBudget = stmtBudg.executeQuery(qwre);
            while (resultBudget.next()){
                field1 = BigInteger.valueOf(resultBudget.getInt("budget_id"));
                field2 = resultBudget.getString("operation_type");
                field3 = BigInteger.valueOf(resultBudget.getInt("budget_type_id_fk"));
                field4 = resultBudget.getString("description");
                field5 = BigInteger.valueOf(resultBudget.getInt("account_id_fk"));
                field6 = resultBudget.getDate("operation_date");
                field7 = resultBudget.getDouble("charge_value");
                System.out.println(String.format("Id: %5d| operation type: %12s| budgetTypeId: %5d| description: %45s| " +
                                "accountId: %5d| operation date: %10tD| charge value: %7.2f",
                        field1, field2, field3, field4, field5, field6, field7));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public BigInteger getBudgetId() {
        return budgetId;
    }

    public void setBudgetId() {
        String qwr = "SELECT max(budget_id) AS id FROM budget";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            budgetId = BigInteger.valueOf(res.getInt("id") + 1);
        } catch (SQLException e) {
            System.out.println("An error occured while determining the primary key for an entry in the database table BUDGET");
            e.printStackTrace();
        }
    }

    public void setBudgetId(BigInteger budgetId) {
        this.budgetId = budgetId;
    }

    @Override
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public BigInteger getBudgetTypeId() {
        return budgetTypeId;
    }

    public void setBudgetTypeId(BigInteger budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }

    @Override
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BigInteger getAccountId() {
        return accountId;
    }

    public void setAccountId(BigInteger accountId) {
        this.accountId = accountId;
    }

    @Override
    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public Double getChargeValue() {
        return chargeValue;
    }

    public void setChargeValue(Double chargeValue) {
        this.chargeValue = chargeValue;
    }
}
