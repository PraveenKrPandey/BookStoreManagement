
import java.sql.Connection;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class SalesDao {

// Will convert from string to date
    public static LocalDate getADate(String sDate) {
        LocalDate date=null;
        try{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
         date = LocalDate.parse(sDate, formatter);

        } catch (DateTimeParseException ex){
            JOptionPane.showMessageDialog(null, "Invalid Date at get a date"+ex.getMessage());

        }
            return date;

    }

    public static int save(String sCustomerName, int BookNo, String sBookName, String sAuthorName, double unitPrice, int qty, double TotalPrice, double Tax, java.time.LocalDate dateTime) {
        int status = 0;
        try {
            Connection conn;
            conn = DB.getConnection();
            PreparedStatement ps = conn.prepareStatement("insert into sales( Customername, bookno , bookname , authorname , unitprice , saleqty , totalsaleprice, salestax, sellingdate) values (?,?,?,?,?,?,?,?,?) ");
            ps.setString(1, sCustomerName);
            ps.setInt(2, BookNo);
            ps.setString(3, sBookName);
            ps.setString(4, sAuthorName);
            ps.setDouble(5, unitPrice);
            ps.setInt(6, qty);
            ps.setDouble(7, TotalPrice);
            ps.setDouble(8, Tax);
            java.sql.Date d = java.sql.Date.valueOf(dateTime);
            ps.setDate(9, d);
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

///     If you have timestamp type date field in the data base
    // not used
    /*
    public static Timestamp getTimeStamp(String sDate) {

        Date dateDate;
        Timestamp sqlDate = null;

        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            dateDate = dateFormatter.parse(sDate);
            sqlDate = new Timestamp(dateDate.getTime());
            return sqlDate;

        } catch (ParseException e1) {
            e1.printStackTrace();

            return null;
        }
    }
    */

//////
    static Object dataResults[][];
    static Object columns[] = new Object[]{"Sales ID", "Customer Name", "Book No.", "Book Name", "Author  Name", "Unit Price", "Sale Quantity", "Total Sale Price", "Sales Tax", "SellingDate"};

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

    //not used ****
    /*
    
    public static JTable getTableReport(JTable table) {
        dtableModel.setRowCount(0);
        table = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select * from  sales";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {
                tempRow = new Object[]{rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getString(4), rows.getString(5), rows.getDouble(6), rows.getInt(7), rows.getDouble(8), rows.getDouble(9), rows.getDate(10)};
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
    
    */

    public static void fillDay(JComboBox cmbDay) {

        for (int i = 1; i <= 31; i++) {
            cmbDay.addItem("" + i);
        }
    }

    public static void fillMonth(JComboBox cmbMonth) {

        for (int i = 1; i <= 12; i++) {
            cmbMonth.addItem("" + i);
        }
    }

    public static void fillYear(JComboBox cmbYear) {
        for (int i = 1950; i <= 2022; i++) {
            cmbYear.addItem("" + i);

        }
    }

    public static JTable getTableReport(JTable table, String sqlStmt) {
        dtableModel.setRowCount(0);
        table = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            PreparedStatement sqlState = conn.prepareStatement(sqlStmt);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getString(2), rows.getInt(3), rows.getString(4), rows.getString(5), rows.getDouble(6), rows.getInt(7), rows.getDouble(8), rows.getDouble(9), rows.getDate(10)};
                dtableModel.addRow(tempRow);

            }
        }catch (IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }

        return table;
    }

}
