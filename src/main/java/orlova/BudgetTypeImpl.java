package orlova;

import interfaces.BudgetType;
import java.math.BigInteger;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BudgetTypeImpl implements BudgetType {
    private BigInteger budgetTypeId;
    private BigInteger groupId;
    private String name;
    private Boolean isRequired;
    private Double checkMax;
    private Connection connect;

    public BudgetTypeImpl(Connection connect){ this.connect = connect; }

    @Override
    public void create() {

        String insertPerson = "INSERT INTO budget_type (budget_type_id, group_id, name, required, check_max) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmtPers = connect.prepareStatement(insertPerson);
            prepareStmtPers.setInt(1, budgetTypeId.intValue());
            if (groupId == null){
                prepareStmtPers.setNull(2, Types.NUMERIC);
            } else {
                prepareStmtPers.setInt(2, groupId.intValue());
            }
            prepareStmtPers.setString(3, name);
            prepareStmtPers.setBoolean(4, isRequired);
            prepareStmtPers.setDouble(5, checkMax);

            prepareStmtPers.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while entering information into the database table BUDGET_TYPE");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String checkQuery = "SELECT COUNT(*) AS cnt FROM budget_type WHERE group_id = " + budgetTypeId.intValue();
        String checkPlanBudg = "SELECT COUNT(*) AS cnt FROM plan_budget WHERE budget_type_id_fk = " + budgetTypeId.intValue();
        String checkBudg = "SELECT COUNT(*) AS cnt FROM budget WHERE budget_type_id_fk = " + budgetTypeId.intValue();
        DataBaseWork check = new DataBaseWork(connect);
        if(check.checkExist(checkQuery)!=0 || check.checkExist(checkPlanBudg)!=0 ||
                check.checkExist(checkBudg)!=0){
            System.out.println("This record has a link in the other table(s).\nDelete all related entries first.");
            return;
        } else{
            try{
                String deletBudgetType = "DELETE FROM budget_type WHERE budget_type_id = " + budgetTypeId.intValue();
                Statement stmtDelBudgetType = connect.createStatement();
                stmtDelBudgetType.executeUpdate(deletBudgetType);
            } catch(SQLException e) {
                System.out.println("An error occured while deleting a record from the database table BUDGET_TYPE");
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update() {
        String updateBudgetType = "UPDATE budget_type SET group_id = ?, name = ?, " +
                "required = ?, check_max = ? " +
                "WHERE budget_type_id = ?";
        try{
            PreparedStatement preparedStmtPersonForUpdate =connect.prepareStatement(updateBudgetType);
            preparedStmtPersonForUpdate.setInt(1, groupId.intValue());
            preparedStmtPersonForUpdate.setString(2, name);
            preparedStmtPersonForUpdate.setBoolean(3, isRequired);
            preparedStmtPersonForUpdate.setDouble(4, checkMax);
            preparedStmtPersonForUpdate.setInt(5, budgetTypeId.intValue());

            preparedStmtPersonForUpdate.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while updating a record from the database table BUDGET_TYPE");
            e.printStackTrace();
        }
    }

    @Override
    public boolean load(BigInteger id) {
        try{
            Statement stmtCheckRecord = connect.createStatement();
            ResultSet resultCheckBudgetType = stmtCheckRecord.executeQuery("SELECT COUNT(*) AS cnt FROM budget_type WHERE budget_type_id = " + id.intValue());
            resultCheckBudgetType.next();
            if (resultCheckBudgetType.getInt("cnt") != 0){
                String dataBudgetType = "SELECT * FROM budget_type WHERE budget_type_id = " + id.intValue();
                Statement stmtBudgType = connect.createStatement();
                ResultSet resultBudgType = stmtBudgType.executeQuery(dataBudgetType);
                while (resultBudgType.next()){
                    budgetTypeId = id;
                    groupId = BigInteger.valueOf(resultBudgType.getInt("group_id"));
                    name = resultBudgType.getString("name");
                    isRequired = resultBudgType.getBoolean("required");
                    checkMax = resultBudgType.getDouble("check_max");

                    System.out.println(String.format("BudgetTypeId: %5d| groupId: %15d| name: %15s| " +
                                    "required: %5b| check_max: %10f.2",
                            id, groupId, name, isRequired, checkMax));
                }
                return true;
            } else {
                System.out.println("Budget type with the specified ID is not in the table BUDGET_TYPE");
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table BUDGET_TYPE");
            e.printStackTrace();
        }
        return false;
    }

    public void showTable(){
        String qwre = "SELECT * FROM budget_type";
        int field1, field2;
        String field3;
        Boolean field4;
        Double field5;
        try {
            Statement stmtPers =connect.createStatement();
            ResultSet resultBudgetType = stmtPers.executeQuery(qwre);
            while (resultBudgetType.next()){
                field1 = resultBudgetType.getInt("budget_type_id");
                field2 = resultBudgetType.getInt("group_id");
                field3 = resultBudgetType.getString("name");
                field4 = resultBudgetType.getBoolean("required");
                field5 = resultBudgetType.getDouble("check_max");
                System.out.println(String.format("BudgetTypeId: %5d| groupId: %5d| name: %20s| " +
                                "required: %5b| check_max: %.2f",
                        field1, field2, field3, field4, field5));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table BUDGET_TYPE");
            e.printStackTrace();
        }
    }

    public void showGroups(){
        String qwre = "SELECT budget_type_id, name, group_id " +
                "FROM budget_type " +
                "WHERE group_id IS NULL";
        HashMap<Integer, String> budgetGroups = new HashMap<>();
        try {
            Statement stmtBudgTypeGroup = connect.createStatement();
            ResultSet resultGroup = stmtBudgTypeGroup.executeQuery(qwre);
            while (resultGroup.next()){
                budgetGroups.put(resultGroup.getInt("budget_type_id"),
                        resultGroup.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while finding budget groups from the database table BUDGET_TYPE");
            e.printStackTrace();
        }
        System.out.println(String.format("\tid\t\t\t\t\tname"));
        for(Map.Entry entry : budgetGroups.entrySet()){
            System.out.println(String.format("%6d\t%20s", entry.getKey(), entry.getValue()));
        }
    }

    public boolean isGroupExsist(int checkGroup){
        String qwre = "SELECT budget_type_id, group_id " +
                "FROM budget_type " +
                "WHERE group_id IS NULL";
        try {
            Statement stmtBudgTypeGroup = connect.createStatement();
            ResultSet resultGroup = stmtBudgTypeGroup.executeQuery(qwre);
            while (resultGroup.next()){
                if (checkGroup == resultGroup.getInt("budget_type_id")){
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("An error occured while finding budget groups from the database table BUDGET_TYPE");
            e.printStackTrace();
        }
        System.out.println("The group with the specified id is missing...");
        return false;
    }

    public boolean isBudgetTypeExists(BigInteger id){
        String checkBudgetTypeId = "SELECT COUNT(*) AS cnt FROM budget_type WHERE budget_type_id = " + id.intValue();
        try {
            Statement stmtcheckRecord = connect.createStatement();
            ResultSet checkRes = stmtcheckRecord.executeQuery(checkBudgetTypeId);
            checkRes.next();
            if(checkRes.getInt("cnt") != 0){
                return true;
            } else {
                System.out.println("Budget type with the specified ID is not in the table BUDGET_TYPE");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public BigInteger getBudgetTypeId() {
        return budgetTypeId;
    }

    public void setBudgetTypeId(){
        String qwr = "SELECT max(budget_type_id) AS id FROM budget_type";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            budgetTypeId = BigInteger.valueOf(res.getInt("id") + 1);
        } catch (SQLException e) {
            System.out.println("An error occured while determining the primary key for an entry in the database table BUDGET_TYPE");
            e.printStackTrace();
        }
    }

    public void setBudgetTypeId(BigInteger budgetTypeId) {
        this.budgetTypeId = budgetTypeId;
    }

    @Override
    public BigInteger getGroupId() {
        return groupId;
    }

    public void setGroupId(BigInteger groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Boolean getRequired() {
        return isRequired;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    @Override
    public Double getCheckMax() {
        return checkMax;
    }

    public void setCheckMax(Double checkMax) {
        this.checkMax = checkMax;
    }
}
