package orlova;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseWork {
    private Connection connect;

    public DataBaseWork(Connection connect){
        this.connect = connect;
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
