package com.netcracker.ncedu.tlt.dimi1.expensemanager.reports;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import java.sql.ResultSet;
import java.sql.SQLException;

public class Report1RowMapper implements RowMapper<Report1> {
    private JdbcTemplate jdbcTemplate;

    Report1RowMapper(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Report1 mapRow(ResultSet row, int rowNum) throws SQLException {
        Report1 rep1 = new Report1(jdbcTemplate);
        rep1.setId(Integer.valueOf(row.getInt("budget_type_id")));
        rep1.setName(row.getString("NAME"));
        rep1.setSum(row.getDouble("SUMMA"));
        return rep1;
    }
}
