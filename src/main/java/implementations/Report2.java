package implementations;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Report2 {
    private BigInteger id;
    private String name;
    private Boolean isRequired;
    private Double sum;
    private Double totalSum;
    private List<Integer> groups = new ArrayList<>();
    private Connection connect;

    public Report2(Connection connect){ this.connect = connect; }

    public List<Integer> getAllExpenseGroups() {
        try {
            Statement stmt = connect.createStatement();
            ResultSet expenseGroups = stmt.executeQuery("SELECT budget_type_id AS id FROM budget_type " +
                    "WHERE group_id IS NULL AND budget_type_id <> 11");
            while (expenseGroups.next()) {
                groups.add(expenseGroups.getInt("id"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return groups;
    }

    public void getReportRow(int groupId, List<Report2> rL, boolean required) {
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery("WITH RECURSIVE Report21 (budget_type_id, name, required, summa) AS( " +
                    "SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value) " +
                    "FROM budget_type b_type, budget b " +
                    "WHERE b_type.budget_type_id = " + groupId + " " +
                    "AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type " +
                    "WHERE group_id = " + groupId + " " +
                    "AND required = " + required + " " +
                    "UNION ALL " +
                    "SELECT budget_type_id FROM budget_type WHERE budget_type_id = " + groupId + " " +
                    "AND required = " + required + " " +
                    ") " +
                    "GROUP BY b_type.budget_type_id " +
                    "UNION ALL " +
                    "SELECT nested_budgT.budget_type_id, nested_budgT.name, " +
                    "nested_budgT.required, sum(nested_budg.charge_value) " +
                    "FROM Report21 rep " +
                    "INNER JOIN budget_type nested_budgT, budget nested_budg " +
                    "WHERE nested_budgT.group_id = rep.budget_type_id " +
                    "AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk " +
                    "AND nested_budgT.required = " + required + " " +
                    "GROUP BY(nested_budgT.budget_type_id) " +
                    ") " +
                    "SELECT budget_type_id AS id, name, required, summa FROM Report21 ORDER BY budget_type_id;");
            while (res.next()) {
                Report2 obj = new Report2(connect);
                obj.setId(BigInteger.valueOf(res.getInt("id")));
                obj.setName(res.getString("name"));
                obj.setRequired(res.getBoolean("required"));
                obj.setSum(res.getDouble("summa"));
                rL.add(obj);
            }
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setTotalSum(boolean required){
        try {
            Statement stmt = connect.createStatement();
            ResultSet resSum = stmt.executeQuery("SELECT SUM(charge_value) AS sum " +
                    "FROM budget, budget_type " +
                    "WHERE budget_type_id_fk NOT IN (" +
                    "SELECT budget_type_id FROM budget_type WHERE group_id = 11 AND 11) " +
                    "AND budget.budget_type_id_fk = budget_type.budget_type_id " +
                    "AND budget_type.required = " + required);
            resSum.next();
            totalSum = resSum.getDouble("sum");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Double getTotalSum(){
        return totalSum;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getRequired(){ return isRequired; }

    public Double getSum() {
        return sum;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequired(Boolean required) {
        isRequired = required;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
