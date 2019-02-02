package orlova;

import interfaces.PlanBudget;

import java.math.BigInteger;
import java.sql.*;
import java.util.Date;
import java.util.Scanner;

public class PlanBudgetImpl implements PlanBudget {
    private BigInteger planBudgetId;
    private String operationType;
    private BigInteger budgetTypeId;
    private String description;
    private BigInteger accountId;
    private Date operationDate;
    private Double chargeValue;
    private String regularMask;
    private Integer repeatCount;
    private Date startDate;
    private Date endDate;
    private Connection connect;

    public PlanBudgetImpl(Connection connect){ this.connect = connect; }

    @Override
    public void create() {
        String insertPlanBudg = "INSERT INTO plan_budget (plan_budget_id, operation_type, budget_type_id_fk, description, " +
                "account_id_fk, operation_date, charge_value, regular_mask, repeat_count, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmtPlanBudg = connect.prepareStatement(insertPlanBudg);
            prepareStmtPlanBudg.setInt(1, planBudgetId.intValue());
            prepareStmtPlanBudg.setString(2, operationType);
            prepareStmtPlanBudg.setInt(3, budgetTypeId.intValue());
            prepareStmtPlanBudg.setString(4, description);
            prepareStmtPlanBudg.setInt(5, accountId.intValue());
            prepareStmtPlanBudg.setObject(6, operationDate);
            prepareStmtPlanBudg.setDouble(7, chargeValue);
            prepareStmtPlanBudg.setString(8, regularMask);
            prepareStmtPlanBudg.setInt(9, repeatCount);
            prepareStmtPlanBudg.setObject(10, startDate);
            prepareStmtPlanBudg.setObject(11, endDate);

            prepareStmtPlanBudg.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while entering information into the database table PLAN_BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        try{
            String deletPlanBudg = "DELETE FROM plan_budget WHERE plan_budget_id = " + planBudgetId.intValue();
            Statement stmtDelPlanBudg = connect.createStatement();
            stmtDelPlanBudg.executeUpdate(deletPlanBudg);
        } catch(SQLException e) {
            System.out.println("An error occured while deleting a record from the database table PLAN_BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        String updatePlanBudg = "UPDATE plan_budget SET operation_type = ?, budget_type_id_fk = ?, description = ?, " +
                "account_id_fk = ?, operation_date = ?, charge_value = ?, regular_mask = ?, " +
                "repeat_count = ?, start_date = ?, end_date = ?" +
                "WHERE plan_budget_id = ?";
        try{
            PreparedStatement prepareStmtPlanBudgForUpdate = connect.prepareStatement(updatePlanBudg);
            prepareStmtPlanBudgForUpdate.setString(1, operationType);
            prepareStmtPlanBudgForUpdate.setInt(2, budgetTypeId.intValue());
            prepareStmtPlanBudgForUpdate.setString(3, description);
            prepareStmtPlanBudgForUpdate.setInt(4, accountId.intValue());
            prepareStmtPlanBudgForUpdate.setObject(5, operationDate);
            prepareStmtPlanBudgForUpdate.setDouble(6, chargeValue);
            prepareStmtPlanBudgForUpdate.setString(7, regularMask);
            prepareStmtPlanBudgForUpdate.setInt(8, repeatCount);
            prepareStmtPlanBudgForUpdate.setObject(9, startDate);
            prepareStmtPlanBudgForUpdate.setObject(10, endDate);
            prepareStmtPlanBudgForUpdate.setInt(11, planBudgetId.intValue());

            prepareStmtPlanBudgForUpdate.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while updating a record from the database table PLAN_BUDGET");
            e.printStackTrace();
        }
    }

    @Override
    public void load(BigInteger id) {
        String dataPerson = "SELECT * FROM plan_budget WHERE plan_budget_id = " + id.intValue();
        try {
            Statement stmtPlanBudg = connect.createStatement();
            ResultSet resultPlanBudget = stmtPlanBudg.executeQuery(dataPerson);
            while (resultPlanBudget.next()){
                operationType = resultPlanBudget.getString("operation_type");
                budgetTypeId = BigInteger.valueOf(resultPlanBudget.getInt("budget_type_id_fk"));
                description = resultPlanBudget.getString("description");
                accountId = BigInteger.valueOf(resultPlanBudget.getInt("account_id_fk"));
                operationDate = resultPlanBudget.getDate("operation_date");
                chargeValue = resultPlanBudget.getDouble("charge_value");
                regularMask = resultPlanBudget.getString("regular_mask");
                repeatCount = resultPlanBudget.getInt("repeat_count");
                startDate = resultPlanBudget.getDate("start_date");
                endDate = resultPlanBudget.getDate("end_date");

                System.out.println(String.format("Id: %5d| operation type: %12s| budgetTypeId: %5d| description: %3s| " +
                                "accountId: %5d| operation date: %10tD| charge value: %7.2f| regular mask: %20s| " +
                                "repeat count: %4d| start date: %10tD| end date: %10td",
                        id, operationType, budgetTypeId, description, accountId, operationDate, chargeValue,
                        regularMask, repeatCount, startDate, endDate));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table PLAN_BUDGET");
            e.printStackTrace();
        }
    }

    public void showTable(){
        String qwre = "SELECT * FROM plan_budget";
        int field1, field3, field5, field9;
        Double field7;
        String field2, field4, field8;
        java.sql.Date field6, field10, field11;
        try {
            Statement stmtPlanBudg =connect.createStatement();
            ResultSet resultPlanBudget = stmtPlanBudg.executeQuery(qwre);
            while (resultPlanBudget.next()){
                field1 = resultPlanBudget.getInt("plan_budget_id");
                field2 = resultPlanBudget.getString("operation_type");
                field3 = resultPlanBudget.getInt("budget_type_id_fk");
                field4 = resultPlanBudget.getString("description");
                field5 = resultPlanBudget.getInt("account_id_fk");
                field6 = resultPlanBudget.getDate("operation_date");
                field7 = resultPlanBudget.getDouble("charge_value");
                field8 = resultPlanBudget.getString("regular_mask");
                field9 = resultPlanBudget.getInt("repeat_count");
                field10 = resultPlanBudget.getDate("start_date");
                field11 = resultPlanBudget.getDate("end_date");
                System.out.println(String.format("Id: %5d| operation type: %12s| budgetTypeId: %5d| description: %35s| " +
                                "accountId: %5d| operation date: %10tD| charge value: %7.2f| regular mask: %20s| " +
                                "repeat count: %4d| start date: %10tD| end date: %10td",
                        field1, field2, field3, field4, field5, field6, field7, field8, field9, field10, field11));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table PLAN BUDGET");
            e.printStackTrace();
        }
    }

    public boolean isPlanBudgetExists(BigInteger id) {
        try {
            PreparedStatement checkRecord = connect.prepareStatement("SELECT * FROM plan_budget WHERE plan_budget_id = ?");
            checkRecord.setInt(1, id.intValue());

            try (ResultSet checkRes = checkRecord.executeQuery()) {
                if (checkRes.next()){
                    return true;
                } else {
                    System.out.println("Plan budget with the specified ID is not in the table PLAN_BUDGET");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BigInteger getPlanBudgetId() {
        return planBudgetId;
    }

    public void setPlanBudgetId(){
        String qwr = "SELECT max(plan_budget_id) AS id FROM plan_budget";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            planBudgetId = BigInteger.valueOf(res.getInt("id") + 1);
        } catch (SQLException e) {
            System.out.println("An error occured while determining the primary key for an entry in the database table PLAN_BUDGET");
            e.printStackTrace();
        }
    }

    public void setPlanBudgetId(BigInteger planBudgetId) {
        this.planBudgetId = planBudgetId;
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

    @Override
    public String getRegularMask() {
        return regularMask;
    }

    public void setRegularMask(String regularMask) {
        this.regularMask = regularMask;
    }

    @Override
    public Integer getRepeatCount() {
        return repeatCount;
    }

    public void setRepeatCount(Integer repeatCount) {
        this.repeatCount = repeatCount;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
