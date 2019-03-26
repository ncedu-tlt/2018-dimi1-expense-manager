package com.netcracker.ncedu.tlt.dimi1.expensemanager.reports;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

public class Report2 {
    private Integer id;
    private String name;
    private Boolean isRequired;
    private Double sum;
    private Double totalSum;
    private List<Integer> groups = new ArrayList<>();
    private JdbcTemplate jdbcTemplate;

    public Report2(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    public List<Integer> getAllExpenseGroups() {
        String qwery = "SELECT budget_type_id AS id FROM budget_type " +
                "WHERE group_id IS NULL AND budget_type_id <> 11";
        groups = jdbcTemplate.queryForList(qwery, Integer.class);
        return groups;
    }

    public void getReportRow(int groupId, List<Report2> rL, boolean required) {
        String rep21 = "WITH RECURSIVE Report21 (budget_type_id, name, required, summa) AS( " +
                "SELECT b_type.budget_type_id, b_type.name, b_type.required, sum(b.charge_value) " +
                "FROM budget_type b_type, budget b " +
                "WHERE b_type.budget_type_id = ? " +
                "AND b.budget_type_id_fk IN (SELECT budget_type_id FROM budget_type " +
                "WHERE group_id = ? " +
                "AND required = ? " +
                "UNION ALL " +
                "SELECT budget_type_id FROM budget_type WHERE budget_type_id = ? " +
                "AND required = ? " +
                ") " +
                "GROUP BY b_type.budget_type_id " +
                "UNION ALL " +
                "SELECT nested_budgT.budget_type_id, nested_budgT.name, " +
                "nested_budgT.required, sum(nested_budg.charge_value) " +
                "FROM Report21 rep " +
                "INNER JOIN budget_type nested_budgT, budget nested_budg " +
                "WHERE nested_budgT.group_id = rep.budget_type_id " +
                "AND nested_budgT.budget_type_id = nested_budg.budget_type_id_fk " +
                "AND nested_budgT.required = ? " +
                "GROUP BY(nested_budgT.budget_type_id) " +
                ") " +
                "SELECT budget_type_id AS id, name, required, summa FROM Report21 ORDER BY budget_type_id;";
        Object[] args = new Object[]{
                groupId, groupId, required, groupId, required, required
        };
        RowMapper<Report2> rowMapper = new Report2RowMapper(jdbcTemplate);
        List<Report2> getRows = jdbcTemplate.query(rep21, args, rowMapper);
        for(Report2 i : getRows){
            rL.add(i);
        }
    }

    public void setTotalSum(boolean required){
        String getTotalSum = "SELECT SUM(charge_value) AS sum " +
                "FROM budget, budget_type " +
                "WHERE budget_type_id_fk NOT IN (" +
                "SELECT budget_type_id FROM budget_type WHERE group_id = 11 AND 11) " +
                "AND budget.budget_type_id_fk = budget_type.budget_type_id " +
                "AND budget_type.required = ?";
        totalSum = jdbcTemplate.queryForObject(getTotalSum, Double.class, required);
    }

    public Double getTotalSum(){
        return totalSum;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getRequired(){ return isRequired; }

    public Double getSum() {
        return sum;
    }

    public void setId(Integer id) {
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
