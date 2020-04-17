
import java.awt.Font;
import java.sql.*;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookDao {

    public static int save(int BookNo, String BookName, String sCatagory, String AuthorName, String publisher, int NoOfPages, double Price, int Stock) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("insert Into book(bookno,bookname,catagory,authorname,publisher,no_of_pages,price,stock) values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, BookNo);
            ps.setString(2, BookName);
            ps.setString(3, sCatagory);
            ps.setString(4, AuthorName);
            ps.setString(5, publisher);
            ps.setInt(6, NoOfPages);
            ps.setDouble(7, Price);
            ps.setInt(8, Stock);

            status = ps.executeUpdate();
            con.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(null, ""+e.getMessage(), "Error In Entry", JOptionPane.WARNING_MESSAGE);

        } catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return status;
    }

    public static void getAllBookNo(JComboBox cmbBookNos) {
        try {
            Connection con = DB.getConnection();
            String sql = "select bookno from book";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                cmbBookNos.addItem(rs.getString("bookno"));
            }
            if(con!=null)
               con.close();
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

    }

    public static boolean validatBook(int BookNo) {
        Boolean status = false;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from book where bookno=?");
            ps.setInt(1, BookNo);
            ResultSet rs = ps.executeQuery();
            status = rs.next();
            con.close();
        } catch (SQLException  | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
                    System.out.print(ex);
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
             System.out.print(e);
        }
        return status;
    }

    public static int update_book(int BookNo, String BookName, String sCatagory, String AuthorName, String publisher, int NoOfPages, double Price) {
        int status = 0;
        try {
            Connection Con = DB.getConnection();
            PreparedStatement ps = Con.prepareStatement("update book set bookname=?,catagory=?,authorname=?,publisher=?,no_of_pages=?,price=?  where bookno=?");
            ps.setString(1, BookName);
            ps.setString(2, sCatagory);
            ps.setString(3, AuthorName);
            ps.setString(4, publisher);
            ps.setInt(5, NoOfPages);
            ps.setDouble(6, Price);
            ps.setInt(7, BookNo);
            status = ps.executeUpdate();
            Con.close();
        }catch (SQLException  | IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return status;

    }

    public static int updateStock(int BookNo, int UpdatedStock) {
        int status = 0;
        try {

            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("update book set stock=? where bookno=?");
            ps.setInt(1, UpdatedStock);
            ps.setInt(2, BookNo);
            status = ps.executeUpdate();
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

    public static int deleteBoook(int bookNo) {
        int status = 0;
        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from book where bookno=?");
            ps.setInt(1, bookNo);
            status = ps.executeUpdate();
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

    ///////   //
    static Object dataResults[][];
    static Object columns[] = new Object[]{"BookID", "BookNo", "bookName", "catagory", "authorname", "publisher", "no of pages", "price", "stock"};

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

    public static JTable getTable(JTable table1, int bookNo) {
        dtableModel.setRowCount(0);
        table1 = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select bookID,bookno,bookname,catagory,authorname,publisher,no_of_pages,price,stock from book  where bookno=?";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            sqlState.setInt(1, bookNo);
            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getInt(2), rows.getString(3), rows.getString(4), rows.getString(5), rows.getString(6), rows.getInt(7), rows.getDouble(8), rows.getInt(9)};
                dtableModel.addRow(tempRow);

            }
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        table1.setFont(new Font("Serif", Font.PLAIN, 11));

        // Increase the size of the cells to allow for bigger fonts
        table1.setRowHeight(table1.getRowHeight());

        // Allows the user to sort the data
        table1.setAutoCreateRowSorter(true);

        // Adds the table to a scrollpane
        return table1;

    }

    public static JTable getTable(JTable table1, String catagory) {

        dtableModel.setRowCount(0);

        table1 = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select bookID,bookno,bookname,catagory,authorname,publisher,no_of_pages,price,stock from book  where catagory=?";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            sqlState.setString(1, catagory);
            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getInt(2), rows.getString(3), rows.getString(4), rows.getString(5), rows.getString(6), rows.getInt(7), rows.getDouble(8), rows.getInt(9)};
                dtableModel.addRow(tempRow);

            }
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        table1.setFont(new Font("Serif", Font.PLAIN, 11));

        // Increase the size of the cells to allow for bigger fonts
        table1.setRowHeight(table1.getRowHeight());

        // Allows the user to sort the data
        table1.setAutoCreateRowSorter(true);

        // Adds the table to a scrollpane
        return table1;

    }

    public static JTable getTableAut(JTable table1, String AuthorName) {

        dtableModel.setRowCount(0);
        table1 = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select bookID,bookno,bookname,catagory,authorname,publisher,no_of_pages,price,stock from book  where authorname=?";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            sqlState.setString(1, AuthorName);
            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getInt(2), rows.getString(3), rows.getString(4), rows.getString(5), rows.getString(6), rows.getInt(7), rows.getDouble(8), rows.getInt(9)};
                dtableModel.addRow(tempRow);

            }
        } catch (Exception e) {
            System.out.println("SQLException: " + e.getMessage());

        }

        table1.setFont(new Font("Serif", Font.PLAIN, 11));

        // Increase the size of the cells to allow for bigger fonts
        table1.setRowHeight(table1.getRowHeight());

        // Allows the user to sort the data
        table1.setAutoCreateRowSorter(true);

        // Adds the table to a scrollpane
        return table1;

    }
    
    
////******not used
//
//    public static void validate1(int BookNo, JTextField tfBookName, JTextField tfUnitPrice) {
//        try {
//            Connection con;
//            con = DB.getConnection();
//            PreparedStatement ps = con.prepareStatement("select bookname,price from book where bookno=?");
//            ps.setInt(1, BookNo);
//            ResultSet rs = ps.executeQuery();
//            rs.next();
//            tfBookName.setText(rs.getString("bookname"));
//            tfUnitPrice.setText(rs.getString("price"));
//            con.close();
//        }catch ( IllegalArgumentException | SQLException ex) {
//            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
//            System.out.print(ex);
//        }catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
//            System.out.print(e);
//        }
//    }
////


    public static ResultSet validate(int BookNo, ResultSet returnRs) {

        try {
            Connection con = DB.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from book where bookno=?");
            ps.setInt(1, BookNo);
            ResultSet rs = ps.executeQuery();
            returnRs = rs;
//            con.close();                  CLOSE THIS AFTER OPERATION 
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        return returnRs;

    }
            
//  
    public static void getNoOfBooks(JComboBox cmbNoOfBooks) {

        for (int i = 1; i <= 100; i++) {
            cmbNoOfBooks.addItem(String.valueOf(i));
        }
    }

    public static JTable getTableReport(JTable table) {
        dtableModel.setRowCount(0);
        table = new JTable(dtableModel);
        Connection conn;
        conn = DB.getConnection();
        try {

            String selectStuff = "select bookID,bookno,bookname,catagory,authorname,publisher,no_of_pages,price,stock from book ";

            PreparedStatement sqlState = conn.prepareStatement(selectStuff);

            ResultSet rows = sqlState.executeQuery();
            Object[] tempRow;

            while (rows.next()) {

                tempRow = new Object[]{rows.getInt(1), rows.getInt(2), rows.getString(3), rows.getString(4), rows.getString(5), rows.getString(6), rows.getInt(7), rows.getDouble(8), rows.getInt(9)};
                dtableModel.addRow(tempRow);

            }
        }catch ( IllegalArgumentException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(ex);
        }catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);
            System.out.print(e);
        }

        table.setFont(new Font("Serif", Font.PLAIN, 15));

        table.setRowHeight(table.getRowHeight() + 1);

        table.setAutoCreateRowSorter(true);

        return table;
    }

}
