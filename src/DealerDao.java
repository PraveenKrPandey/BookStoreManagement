
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


public class DealerDao {

    public static int save(String DealerName, String DealerAddress, String Phone, String sCatagory) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into dealer(dealer_name,dealer_address,phoneno,book_catgry) values (?,?,?,?)");
            ps.setString(1, DealerName);
            ps.setString(2, DealerAddress);
            ps.setString(3, Phone);
            ps.setString(4, sCatagory);
            status = ps.executeUpdate();
            con.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, "Duplicate Entry made Integrity Constraint Violation" + e.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
        } catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }
        return status;

    }

    public static int update_Dealer(String Dealer, String sCatagory, String phoneNo, String DealerAddress) {
        int status = 0;
        try {
            Connection Con = DB.getConnection();
            PreparedStatement ps = Con.prepareStatement("update dealer set book_catgry=?,dealer_address=?,phoneno=?  where dealer_name=?");
//            ps.setString(1, Dealer);
            ps.setString(1, sCatagory);
            ps.setString(2, DealerAddress);
            ps.setString(3, phoneNo);
            ps.setString(4, Dealer);
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

    public static int deleteDealer(String dealername) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from dealer where dealer_name=?");
            ps.setString(1, dealername);
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

    public static void getAllDealerName(JComboBox cmbDealerName) {
        try {
            Connection con = DB.getConnection();
            String sql = "select dealer_name from dealer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbDealerName.addItem(rs.getString("dealer_name"));
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

    public static boolean validatDealer(String DealerName, JTextField tfDealerAddress, JTextField tfPhoneNo, JComboBox cmbCatagory) {
        Boolean status = false;
        try {
            Connection con;
            con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select dealer_address,phoneno,book_catgry from dealer where dealer_name=?");
            ps.setString(1, DealerName);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
//            cmbDealerName.setSelectedItem(rs.getString("dealer_name"));
            cmbCatagory.setSelectedItem(rs.getString("book_catgry"));
            tfDealerAddress.setText(rs.getString("dealer_address"));
            tfPhoneNo.setText(rs.getString("phoneno"));

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

    public static void getAllDealers(JComboBox cmbDealerName) {
        try {
            Connection con = DB.getConnection();
            String sql = "select dealer_name from dealer";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbDealerName.addItem(rs.getString("dealer_name"));
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
    ////
    static Object dataResults[][];
    static Object columns[] = new Object[]{"Dealer ID", "Dealer Name", "Dealer Address", "Phone No", "Book Catagory"};

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

            String selectStuff = "select * from  dealer";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getString(2), rows.getString(3), rows.getString(4), rows.getString(5)};
                dtableModel.addRow(tempRow);
            }
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return table;

    }

}
