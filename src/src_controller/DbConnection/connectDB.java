package DbConnection;

import com.mysql.cj.Session;

import java.sql.*;
import java.util.ArrayList;

public class connectDB {

    private static Connection connection = null;
    private static Session session = null;
    public static ArrayList list = new ArrayList();

    public static Object get_dataUsername(String query, String database) {

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        Object username = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            switch (database) {
                case "prod":
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://54.169.68.90/staging_fdbr_salon", "serverteam", "DDKW31Kr31");
                    break;
                case "staging":
                    con = (Connection) DriverManager.getConnection("jdbc:mysql://54.169.68.90/staging_fdbr_salon", "serverteam", "DDKW31Kr31");
                    break;
                default:
                    throw new Exception("No Database with that name");
            }

            stmt = (Statement) con.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) { //get row

                System.out.println(rs.getInt(1));       //1 usrapo_id
                System.out.println(rs.getInt(2));       //2 user_id
                System.out.println(rs.getString(3));    //3 fullname
                System.out.println(rs.getString(4));    //4 username
                System.out.println(rs.getString(5));    //5 password
                System.out.println(rs.getString(6));    //6 dob
                System.out.println(rs.getString(7));    //7 email
            }

        } catch (Exception e) {
            return "Error:" + e.getMessage();
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return username;
    }


}
