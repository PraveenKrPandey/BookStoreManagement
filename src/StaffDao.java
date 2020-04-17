
import java.sql.Connection;
import java.sql.*;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;


public class StaffDao {
    public static Boolean validate(String name, String password ){
        boolean Status=false;
       try{
       Connection conn=DB.getConnection();
       PreparedStatement ps=conn.prepareStatement("select * from staff where username=? AND password=?");
       

        //Compleate the quary with setString()
       ps.setString(1,name);
       ps.setString(2,password);
       
       //Execute the quary after completing it
       ResultSet rs=ps.executeQuery();
       Status=rs.next();        //result set return bool value
       
       conn.close();        //IMP to close 
        }catch (IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }

        return Status;    
    }
    
    
    
    
    
    
}
