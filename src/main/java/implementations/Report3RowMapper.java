package implementations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Report3RowMapper implements RowMapper<Report3> {
    private JdbcTemplate jdbcTemplate;

    Report3RowMapper(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Report3 mapRow(ResultSet row, int rowNum) throws SQLException {
        Report3 rep3 = new Report3(jdbcTemplate);
        rep3.setId(BigInteger.valueOf(row.getInt("ID")));
        //rep3.setDate(row.getDate("NAME"));
        rep3.setSum(row.getBigDecimal("SUMMA"));
        //rep3.setRequired(row.getBoolean("REQUIRED"));
        rep3.setDescription(row.getString("DESCRIPTION"));
        return rep3;
    }
}
