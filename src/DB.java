
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class DB {

    static Connection conn = null;

    public static Connection getConnection() {

        //try Establishing the connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookshopdb", "root", "");

        } 
        catch (ClassNotFoundException ex) {
            System.out.print(ex);
        }
        catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to Close connection", "Error in closing", JOptionPane.WARNING_MESSAGE);
                    System.out.print(ex);
        } 
        catch (Exception ex) {
                System.out.print(ex);
        }

        return conn;
    }   

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Failed to Close connection", "Error in closing", JOptionPane.WARNING_MESSAGE);
                System.out.print(ex);
            } catch (Exception ex) {
                System.out.print(ex);
            }

        }
    }
}
