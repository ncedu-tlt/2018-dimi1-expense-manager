package implementations;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Report1 {
    private BigInteger id;
    private String name;
    private Double sum;
    private Double totalSum;
    private Double percent;
    private List<Integer> groups = new ArrayList<>();
    private Connection connect;

    public Report1(Connection connect){ this.connect = connect; }

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

    public void getReportRow(int groupId, List<Report1> rL) {
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery("WITH RECURSIVE Report11 (budget_type_id, name, summa) AS( " +
                    "SELECT b_type.budget_type_id, b_type.name, sum(b.charge_value) " +
                    "FROM budget_type b_type, budget b " +
                    "WHERE b_type.budget_type_id = " + groupId + " " +
                    "AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type " +
                    "WHERE group_id = " + groupId + " " +
                    "UNION ALL " +
                    "SELECT budget_type_id FROM budget_type WHERE budget_type_id = " + groupId + " " +
                    ") " +
                    "GROUP BY b_type.budget_type_id " +
                    "UNION ALL " +
                    "SELECT nested_budgT.budget_type_id, nested_budgT.name, " +
                    "sum(nested_budg.charge_value) " +
                    "FROM Report11 rep " +
                    "INNER JOIN budget_type nested_budgT, budget nested_budg " +
                    "WHERE nested_budgT.group_id = rep.budget_type_id " +
                    "AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk " +
                    "GROUP BY(nested_budgT.budget_type_id) " +
                    ") " +
                    "SELECT budget_type_id AS id, name, summa FROM Report11 ORDER BY budget_type_id;");
            while (res.next()) {
                Report1 obj = new Report1(connect);
                obj.setId(BigInteger.valueOf(res.getInt("id")));
                obj.setName(res.getString("name"));
                obj.setSum(res.getDouble("summa"));
                rL.add(obj);
            }

        } catch(SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void setTotalSum(){
        try {
            Statement stmt = connect.createStatement();
            ResultSet resSum = stmt.executeQuery("SELECT SUM(charge_value) AS sum FROM budget " +
                    "WHERE budget_type_id_fk NOT IN (" +
                    "SELECT budget_type_id FROM budget_type WHERE group_id = 11 AND 11) ");
            resSum.next();
            totalSum = resSum.getDouble("sum");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Double getTotalSum(){
        return totalSum;
    }

    public void setPercent(Double sum, Double totSum){
        percent = Double.valueOf(Math.round(sum*100/totSum));
    }

    public Double getPercent(){
        return percent;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getSum() {
        return sum;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }
}
