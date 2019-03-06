package implementations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Report1 {
    private BigInteger id;
    private String name;
    private Double sum;
    private Double totalSum;
    private Double percent;
    private List<Integer> groups = new ArrayList<>();
    private JdbcTemplate jdbcTemplate;

    public Report1(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public List<Integer> getAllExpenseGroups() {
        String qwery = "SELECT budget_type_id AS id FROM budget_type " +
                "WHERE group_id IS NULL AND budget_type_id <> 11";
        groups = jdbcTemplate.queryForList(qwery, Integer.class);
        return groups;
    }

    public void getReportRow(int groupId, List<Report1> rL) {
        String rep1 = "WITH RECURSIVE Report11 (budget_type_id, name, summa) AS( " +
                "SELECT b_type.budget_type_id, b_type.name, sum(b.charge_value) " +
                "FROM budget_type b_type, budget b " +
                "WHERE b_type.budget_type_id = ? " +
                "AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type " +
                "WHERE group_id = ? " +
                "UNION ALL " +
                "SELECT budget_type_id FROM budget_type WHERE budget_type_id = ? " +
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
                "SELECT budget_type_id AS id, name, summa FROM Report11 ORDER BY budget_type_id;";
        Object[] args = new Object[]{
                groupId, groupId, groupId
        };
        RowMapper<Report1> rowMapper = new Report1RowMapper(jdbcTemplate);
        List<Report1> getRows = jdbcTemplate.query(rep1, args, rowMapper);
        for(Report1 i : getRows){
            rL.add(i);
        }
    }

    public void setTotalSum(){
        String getTotalSum = "SELECT SUM(charge_value) AS sum FROM budget " +
                "WHERE budget_type_id_fk NOT IN (" +
                "SELECT budget_type_id FROM budget_type WHERE group_id = 11 AND 11) ";
        totalSum = jdbcTemplate.queryForObject(getTotalSum, Double.class);
    }

    public Double getTotalSum(){
        return totalSum;
    }

    public void setPercent(Double sum, Double totSum){
        percent = Double.valueOf(Math.round(sum*100/totSum));
    }

    public Double getPercent(){ return percent; }

    public BigInteger getId(){ return id; }

    public String getName() { return name; }

    public Double getSum() { return sum; }

    public void setId(BigInteger id) { this.id = id; }

    public void setName(String name) { this.name = name; }

    public void setSum(Double sum) { this.sum = sum; }
}
