package implementations;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Report1RowMapper implements RowMapper<Report1> {
    private JdbcTemplate jdbcTemplate;

    Report1RowMapper(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate; }

    @Override
    public Report1 mapRow(ResultSet row, int rowNum) throws SQLException {
        Report1 rep1 = new Report1(jdbcTemplate);
        rep1.setId(BigInteger.valueOf(row.getInt("ID")));
        rep1.setName(row.getString("NAME"));
        rep1.setSum(row.getDouble("SUMMA"));
        return rep1;
    }
}
