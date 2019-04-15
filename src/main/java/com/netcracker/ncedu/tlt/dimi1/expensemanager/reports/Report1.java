package com.netcracker.ncedu.tlt.dimi1.expensemanager.reports;

import lombok.Data;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

@Data
public class Report1 {
    private Integer id;
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

    public void getReportPersonRow(Integer personId, int accountId, List<Report1> rL) {
        String rep1 = "select b2.budget_type_id, b2.name, sum(budget.charge_value) as SUMMA from person \n" +
                "left join accounts on person_id = accounts.person_id_fk\n" +
                "right join budget on budget.account_id_fk = accounts.account_id\n" +
                "left join budget_type as b1 on budget.budget_type_id_fk = b1.budget_type_id\n" +
                "left join budget_type as b2 on b2.budget_type_id = b1.group_id\n" +
                "where person_id = '"+ personId +"' and account_id = '"+ accountId +"'" +
                "group by(b1.budget_type_id)" +
                "order by b2.budget_type_id asc;";
        RowMapper<Report1> rowMapper = new Report1RowMapper(jdbcTemplate);
        List<Report1> getRows = jdbcTemplate.query(rep1, rowMapper);
        for(Report1 i : getRows){
            rL.add(i);
        }
    }

    public void setTotalSum(Integer personId, Integer accountId){
        String getTotalSum = "select sum(summa) as totalSumma from\n" +
                "(select sum(budget.charge_value) as SUMMA from person \n" +
                "left join accounts on person_id = accounts.person_id_fk\n" +
                "right join budget on budget.account_id_fk = accounts.account_id\n" +
                "left join budget_type as b1 on budget.budget_type_id_fk = b1.budget_type_id\n" +
                "left join budget_type as b2 on b2.budget_type_id = b1.group_id\n" +
                "where person_id = '"+ personId +"' and account_id = '"+ accountId +"'\n" +
                "group by b2.budget_type_id\n" +
                "order by b2.budget_type_id asc)";
        totalSum = jdbcTemplate.queryForObject(getTotalSum, Double.class);
    }

    public void setPercent(Double sum, Double totSum){
        percent = Double.valueOf(Math.round(sum*100/totSum));
    }
}
