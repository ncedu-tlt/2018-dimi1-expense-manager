package implementations;

import interfaces.Person;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;

public class PersonImpl implements Person {
    private BigInteger personId;
    private String login, pass, email, phonenumber, description, access;
    private Date regDate;
    private JdbcTemplate jdbcTemplate;

    Scanner in = new Scanner(System.in);

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
            System.out.println("This record has a link in the other table(s).\nDelete all related entries first.");
            return;
        } else {
            String deletPerson = "DELETE FROM person WHERE person_id = ?";
            jdbcTemplate.update(deletPerson, personId);
        }
    }

    @Override
    public void update() {
        String updatePerson = "UPDATE person SET login = ?, pass = ?, access = ?, email = ?, " +
                "description = ?, phone_number = ?, reg_date = ? " +
                "WHERE person_id = ?";
        jdbcTemplate.update(updatePerson, login, pass, email, access, description, phonenumber, regDate, personId);
    }

    @Override
    public boolean load(BigInteger id) {
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
            this.access = (String) result.get("ACCESS");
            this.email = (String)result.get("EMAIL");
            this.description = (String)result.get("DESCRIPTION");
            this.regDate = (Date) result.get("REG_DATE");
            this.phonenumber = (String)result.get("PHONE_NUMBER");
            return true;
        } else{
            System.out.println("Record with the specified ID is not in the table PERSON");
        }
        return false;
    }

    public boolean isPersonExist(BigInteger id){
        String checkPersonId = "SELECT COUNT(*) AS cnt FROM person WHERE person_id = ?";
        Integer checkExist = jdbcTemplate.queryForObject(checkPersonId, Integer.class, id);
        if(checkExist != 0){
            return true;
        } else{
            System.out.println("Record with the specified ID is not in the table PERSON");
        }
        return false;
    }

    @Override
    public BigInteger getPersonId() { return personId; }

    public void createUniqId() {
        DatabaseWork dbObj = new DatabaseWork(jdbcTemplate);
        personId = dbObj.getUniqPersonId();
    }

    public void setPersonId(BigInteger personId) {
        this.personId = personId;
    }

    @Override
    public String getLogin() { return login; }

    public void setLogin(String login) {
        if (login.trim().length() != 0){
            this.login = login;
        } else {
            while (login.trim().length() == 0){
                System.out.print("Login can not be empty.\tRepeat imput: ");
                login = in.nextLine();
            }
            this.login = login;
        }
    }

    @Override
    public String getPass() { return pass; }

    public void setPass(String pass) {
        if (pass.trim().length() != 0){
            this.pass = pass;
        } else {
            while (pass.trim().length() == 0){
                System.out.print("Password can not be empty.\tRepeat imput: ");
                pass = in.nextLine();
            }
            this.pass = pass;
        }
    }

    @Override
    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }

    @Override
    public String getDescription() { return description; }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getPhonenumber() { return phonenumber; }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    @Override
    public Date getRegDate() { return regDate; }

    public void setRegDate() {
        LocalDate localRegDate = LocalDate.now();
        regDate = java.sql.Date.valueOf(localRegDate);
    }
}
