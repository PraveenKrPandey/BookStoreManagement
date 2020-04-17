
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CustomerDao {

    public static int save(String CustomerName, String CustomerAddress, String phone, String Catagory) {
        int status = 0;
        try {
            Connection conn;
            conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into customer(customername,customeraddress,phone,customerintrest) values (?,?,?,?) ");
            ps.setString(1, CustomerName);
            ps.setString(2, CustomerAddress);
            ps.setString(3, phone);
            ps.setString(4, Catagory);
            status = ps.executeUpdate();
            conn.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Duplicate Entry made Integrity Constraint Violation" + e.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return status;

    }

    public static void getAllCustomers(JComboBox cmbCustomerName) {
        try {
            Connection con = DB.getConnection();
            String sql = "select customername from customer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbCustomerName.addItem(rs.getString("customername"));
            }
            con.close();
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

    }

    ////        INITIALIZING DATAMODAL FOR jTABLE  
    static Object dataResults[][];
    static Object columns[] = new Object[]{"customer ID", "Customer Name", "Customer Address", "Phone No.", "Customer Major Intrest"};

    static DefaultTableModel dtableModel = new DefaultTableModel(dataResults, columns) {
        public Class getColumnClass(int column) {
            Class returnValue;
            if (column >= 0 && column < getColumnCount()) {
                returnValue = getValueAt(0, column).getClass();

            } else {
                returnValue = Object.class;
            }
            return returnValue;
        }

    };

    
    public static JTable getTableReport(JTable table) {
        dtableModel.setRowCount(0);
        table = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select * from  customer";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getString(2), rows.getString(3), rows.getString(4), rows.getString(5)};
                dtableModel.addRow(tempRow);

            }
        } catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }
        return table;
    }
        
    
    
    
    // update Customer

     public static int update_Customer(String Customer, String sCatagory, String phoneNo, String CustomerAddress) {
        int status = 0;
        try {
            Connection Con = DB.getConnection();
            PreparedStatement ps = Con.prepareStatement("update Customer set customerintrest=?,customeraddress=?,phone=?  where customername=?");
//            ps.setString(1, Customer);
            ps.setString(1, sCatagory);
            ps.setString(2, CustomerAddress);
            ps.setString(3, phoneNo);
            ps.setString(4, Customer);
            status = ps.executeUpdate();
            Con.close();

        }catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Duplicate Entry made Integrity Constraint Violation" + e.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return status;

    }

      public static int deleteCustomer(String Customername) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from Customer where customername=?");
            ps.setString(1, Customername);
            status = ps.executeUpdate();
            con.close();

        } 
        catch (SQLIntegrityConstraintViolationException e) {
          /*dealer name unique key hi isliye integrity must be checked
            */
            JOptionPane.showMessageDialog(null, "Duplicate Entry made Integrity Constraint Violation" + e.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return status;

    }
      
          public static boolean validatCustomer(String CustomerName, JTextField tfCustomerAddress, JTextField tfPhoneNo, JComboBox cmbCatagory) {
        Boolean status = false;
        try {
            Connection con;
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select customeraddress,phone,customerintrest from customer where customername=?");
            ps.setString(1, CustomerName);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
//            cmbCustomerName.setSelectedItem(rs.getString("dealer_name"));
            cmbCatagory.setSelectedItem(rs.getString("customerintrest"));
            tfCustomerAddress.setText(rs.getString("customeraddress"));
            tfPhoneNo.setText(rs.getString("phone"));

            con.close();
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }
        return status;

    }

    
    
    
}
