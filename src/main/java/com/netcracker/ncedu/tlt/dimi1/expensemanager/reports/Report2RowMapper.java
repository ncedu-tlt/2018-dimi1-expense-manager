package com.netcracker.ncedu.tlt.dimi1.expensemanager.reports;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Report2RowMapper implements RowMapper<Report2> {
    private JdbcTemplate jdbcTemplate;

    Report2RowMapper(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Report2 mapRow(ResultSet row, int rowNum) throws SQLException{
        Report2 rep2 = new Report2(jdbcTemplate);
        rep2.setId(Integer.valueOf(row.getInt("ID")));
        rep2.setName(row.getString("NAME"));
        rep2.setRequired(row.getBoolean("REQUIRED"));
        rep2.setSum(row.getDouble("SUMMA"));
        return rep2;
    }
}
