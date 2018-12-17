import java.sql.*;

public class Main {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:~/test";

    static final String USER = "sa";
    static final String PASS = "as";

    static final String CHECK_TABLE_SQL =  "select count(TABLE_NAME) as CNT " +
            "from INFORMATION_SCHEMA.TABLES  " +
            "where TABLE_NAME  = 'CATS'";

    static final String CREATE_TABLE_SQL =  "CREATE TABLE CATS " +
            "(id serial PRIMARY KEY, " +
            " name VARCHAR(100)) ";

    static final String GET_DATA_SQL =  "SELECT * FROM CATS ";

    static Connection conn;
    static Statement stmt;

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database...");

            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();

            if (!checkConnection()) {
                createTableCats();
                System.out.println("Created table CATS.");
            } else {
                System.out.println("Found table CATS.");
            }

            addData();

            getData();

            stmt.close();
            conn.close();

        } catch(SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();

        } catch(Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();

        } finally {
            //finally block used to close resources
            try{
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            } // nothing we can do
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se){
                se.printStackTrace();
            } //end finally try
        } //end try
            System.out.println("Goodbye!");
    }


    static boolean checkConnection() throws SQLException {
        ResultSet res = stmt.executeQuery(CHECK_TABLE_SQL);

        while (res.next()){
            if (res.getInt("CNT") > 0) return true;
        }
        return false;
    }

    static void createTableCats() throws SQLException {
        stmt.execute(CREATE_TABLE_SQL);
    }

    static void addData() throws SQLException {
        stmt.execute("INSERT INTO CATS (name) VALUES ('Pirat');");
        stmt.execute("INSERT INTO CATS (NAME) VALUES ('Tom')");
        stmt.execute("INSERT INTO CATS (NAME) VALUES ('Mars')");
        stmt.execute("INSERT INTO CATS (NAME) VALUES ('Garic')");
        stmt.execute("INSERT INTO CATS (NAME) VALUES ('Barsik')");
    }

    static void getData() throws SQLException {
        ResultSet res = stmt.executeQuery(GET_DATA_SQL);

        System.out.println("ID\tNAME");

        while (res.next()){
            System.out.println(res.getInt("Id") + "\t" + res.getString("Name"));
        }

    }


}