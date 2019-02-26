package implementations;

import java.math.BigInteger;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseWork {
    private Connection connect;
    private Statement stmt;

    public DatabaseWork(Connection connect) {
        this.connect = connect;
    }

    public DatabaseWork() {
        try {
            Class.forName("org.h2.Driver");
            connect = DriverManager.getConnection("jdbc:h2:mem:testdb", "sa", "");
            stmt = connect.createStatement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean checkLogin(String sql)
    {
        try
        {
            Statement checkStmt = connect.createStatement();
            ResultSet checkRes = checkStmt.executeQuery(sql);
            if(checkRes.next())
            {
                return true;
            }
        }catch (SQLException ex)
        {
            System.out.println("An error occured while checking child records in database tables");
            ex.printStackTrace();
        }

        return false;
    }

    public Integer checkExist(String query) {
        try {
            Statement checkStmt = connect.createStatement();
            ResultSet checkRes = checkStmt.executeQuery(query);
            checkRes.next();
            if (checkRes.getInt(1) != 0) {
                return 1;
            }
        } catch (SQLException ex) {
            System.out.println("An error occured while checking child records in database tables");
            ex.printStackTrace();
        }
        return 0;
    }
}
