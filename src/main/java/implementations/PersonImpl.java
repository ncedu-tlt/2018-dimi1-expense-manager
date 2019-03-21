package implementations;

import interfaces.Person;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Data
public class PersonImpl implements Person {
    private Integer personId;
    private String login, pass, email, phonenumber, description, access;
    private Date regDate;
    private JdbcTemplate jdbcTemplate;
    Logger log = LoggerFactory.getLogger(PersonImpl.class);

    public PersonImpl(JdbcTemplate jdbcTemplate){ this.jdbcTemplate = jdbcTemplate;  }

    @Override
    public void create() {
        String insertPerson = "INSERT INTO person (person_id, login, pass, access, email, description, reg_date, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertPerson, personId, login, pass, access, email, description, regDate, phonenumber);
    }

    @Override
    public void delete() {
        String checkQuery = "SELECT COUNT(*) AS cnt FROM accounts WHERE person_id_fk = ?";
        DatabaseWork check = new DatabaseWork(jdbcTemplate);
        if (check.checkExist(checkQuery, personId) != 0) {
            log.info("This record has a link in the other table(s).\nDelete all related entries first.");
            return;
        } else {
            String deletPerson = "DELETE FROM person WHERE person_id = ?";
            jdbcTemplate.update(deletPerson, personId);
        }
    }

    @Override
    public void update() {
        String updatePerson = "UPDATE person SET login = ?, pass = ?, email = ?, " +
                "access = ?, description = ?, phone_number = ?, reg_date = ? " +
                "WHERE person_id = ?";
        jdbcTemplate.update(updatePerson, login, pass, email, access, description, phonenumber, regDate, personId);
    }

    @Override
    public boolean load(Integer id) {
        String checkExistPerson = "SELECT COUNT(*) AS cnt FROM person WHERE person_id = ?";
        Integer checkResult = jdbcTemplate.queryForObject(checkExistPerson, Integer.class, id);
        if(checkResult != 0){
            String dataPerson = "SELECT login, pass, access, email, description, " +
                    "reg_date, phone_number " +
                    "FROM person WHERE person_id = ?";
            Map result = jdbcTemplate.queryForMap(dataPerson, id);
            this.personId = id;
            this.login = (String)result.get("LOGIN");
            this.pass = (String)result.get("PASS");
            this.email = (String)result.get("EMAIL");
            this.access = (String) result.get("ACCESS");
            this.description = (String)result.get("DESCRIPTION");
            this.regDate = (Date) result.get("REG_DATE");
            this.phonenumber = (String)result.get("PHONE_NUMBER");
            return true;
        } else{
            log.info("Record with the specified ID is not in the table PERSON");
        }
        return false;
    }

    public void createUniqId() {
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        personId = dbObj.getUniqPersonId();
    }

    public void setRegDate() {
        LocalDate localRegDate = LocalDate.now();
        regDate = java.sql.Date.valueOf(localRegDate);
    }
}
