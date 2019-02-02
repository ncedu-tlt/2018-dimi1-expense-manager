package orlova;

import interfaces.Person;
import java.math.BigInteger;
import java.sql.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;


public class PersonImpl implements Person {
    private BigInteger personId;
    private String login, pass, email, phonenumber, description;
    private Date regDate;
    private Connection connect;

    Scanner in = new Scanner(System.in);

    public PersonImpl(Connection connect){ this.connect = connect; }

    @Override
    public void create() {

        String insertPerson = "INSERT INTO person (person_id, login, pass, email, description, reg_date, phone_number) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            PreparedStatement prepareStmtPers = connect.prepareStatement(insertPerson);
            prepareStmtPers.setInt(1, personId.intValue());
            prepareStmtPers.setString(2, login);
            prepareStmtPers.setString(3, pass);
            prepareStmtPers.setString(4, email);
            prepareStmtPers.setString(5, description);
            prepareStmtPers.setObject(6, regDate);
            prepareStmtPers.setString(7, phonenumber);

            prepareStmtPers.execute();
        } catch (SQLException e) {
            System.out.println("An error occured while entering information into the database table PERSON");
            e.printStackTrace();
        }
    }

    @Override
    public void delete() {
        String checkQuery = "SELECT * FROM accounts WHERE person_id_fk = " + personId.intValue();
        try{
            Statement checkIdStmt = connect.createStatement();
            ResultSet checkRes = checkIdStmt.executeQuery(checkQuery);
            if(checkRes.next()){
                System.out.println("This record has a link in the table ACCOUNTS.\nDelete all related entries first.");
                return;
            }
            String deletPerson = "DELETE FROM person WHERE person_id = " + personId.intValue();
            Statement stmtDelPers = connect.createStatement();
            stmtDelPers.executeUpdate(deletPerson);
        } catch(SQLException e) {
            System.out.println("An error occured while deleting a record from the database table PERSON");
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        String updatePerson = "UPDATE person SET login = ?, pass = ?, email = ?, " +
                "description = ?, phone_number = ?, reg_date = ? " +
                "WHERE person_id = ?";
            try{
                PreparedStatement preparedStmtPersonForUpdate =connect.prepareStatement(updatePerson);
                preparedStmtPersonForUpdate.setString(1, login);
                preparedStmtPersonForUpdate.setString(2, pass);
                preparedStmtPersonForUpdate.setString(3, email);
                preparedStmtPersonForUpdate.setString(4, description);
                preparedStmtPersonForUpdate.setString(5, phonenumber);
                preparedStmtPersonForUpdate.setObject(6, regDate);
                preparedStmtPersonForUpdate.setInt(7, personId.intValue());

                preparedStmtPersonForUpdate.execute();
            } catch (SQLException e) {
                System.out.println("An error occured while updating a record from the database table PERSON");
                e.printStackTrace();
            }
    }

    @Override
    public void load(BigInteger id) {
        String dataPerson = "SELECT * FROM person WHERE person_id = " + id.intValue();
        try {
            Statement stmtPers = connect.createStatement();
            ResultSet resultPers = stmtPers.executeQuery(dataPerson);
            while (resultPers.next()){
                login = resultPers.getString("login");
                pass = resultPers.getString("pass");
                email = resultPers.getString("email");
                description = resultPers.getString("description");
                regDate = resultPers.getDate("reg_date");
                phonenumber = resultPers.getString("phone_number");

                System.out.println(String.format("Id: %d| login: %s| pass: %s| email: %s| " +
                        "phone: %s| registration date: %tD| description: %s",
                        id, login, pass, email, phonenumber, regDate, description));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table PERSON");
            e.printStackTrace();
        }
    }

    public boolean isPersonExist(BigInteger id){
        try {
            PreparedStatement checkRecord = connect.prepareStatement("SELECT * FROM person WHERE person_id = ?");
            checkRecord.setInt(1, id.intValue());

            try (ResultSet checkRes = checkRecord.executeQuery()) {
                if (checkRes.next()){
                    return true;
                } else {
                    System.out.println("Record with the specified ID is not in the table PERSON");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void showTable(){
        String qwre = "SELECT * FROM person";
        int field1;
        String field2, field3, field4, field5, field7;
        java.sql.Date field6;
        try {
            Statement stmtPers =connect.createStatement();
            ResultSet resultPers = stmtPers.executeQuery(qwre);
            while (resultPers.next()){
                field1 = resultPers.getInt("person_id");
                field2 = resultPers.getString("login");
                field3 = resultPers.getString("pass");
                field4 = resultPers.getString("email");
                field5 = resultPers.getString("description");
                field6 = resultPers.getDate("reg_date");
                field7 = resultPers.getString("phone_number");
                System.out.println(String.format("Id: %5d| login: %15s| pass: %15s| email: %15s| " +
                                "phone: %12s| registration date: %tD| description: %s",
                        field1, field2, field3, field4, field7, field6, field5));
            }
        } catch (SQLException e) {
            System.out.println("An error occured while displaying information from the database table PERSON");
            e.printStackTrace();
        }
    }

    @Override
    public BigInteger getPersonId() { return personId; }

    public void setPersonId() {
        String qwr = "SELECT max(person_id) AS id FROM person";
        try {
            Statement stmt = connect.createStatement();
            ResultSet res = stmt.executeQuery(qwr);
            res.next();
            personId = BigInteger.valueOf(res.getInt("id") + 1);
        } catch (SQLException e) {
            System.out.println("An error occured while determining the primary key for an entry in the database table PERSON");
            e.printStackTrace();
        }
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
