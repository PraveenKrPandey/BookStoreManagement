
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PurchaseDao {

    public static int save(int BookNo, String sDealerName, String sBookName, int NoOfBook, double unitPrice, double TotalPrice, double Tax, java.time.LocalDate dateTime) {
        int status = 0;
        try {
            Connection conn;
            conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into purchase(bookno,dealername,bookname,noofbook,price,totalprice,tax,purchdate) values (?,?,?,?,?,?,?,?) ");
            ps.setInt(1, BookNo);
            ps.setString(2, sDealerName);
            ps.setString(3, sBookName);
            ps.setInt(4, NoOfBook);
            ps.setDouble(5, unitPrice);
            ps.setDouble(6, TotalPrice);
            ps.setDouble(7, Tax);

            java.sql.Date d = java.sql.Date.valueOf(dateTime);
            ps.setDate(8, d);

            status = ps.executeUpdate();
            conn.close();

        } catch (IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }
        return status;
    }

//////
    static Object dataResults[][];
    static Object columns[] = new Object[]{"Purchase ID", "Book No", "Dealer Name", "bookname","NO.of Books Purchased", "Price", "Total Price", "Tax", "Purchase Date"};

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

            String selectStuff = "select * from  purchase";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getInt(2), rows.getString(3), rows.getString(4), rows.getInt(5), rows.getDouble(6), rows.getDouble(7), rows.getDouble(8), rows.getDate(9)};
                dtableModel.addRow(tempRow);

            }
        } catch (IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }

        return table;

    }
/*
    public static LocalDate getADate(String sDate) {
        LocalDate date=null;
        
        try{
        
        DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date = java.time.LocalDate.parse(sDate, formatter);
        }
        catch(DateTimeParseException ex){
                     JOptionPane.showMessageDialog(null, " invalid date"+ex.getMessage());
        }
        return date;

    }
    
*/
    
}
