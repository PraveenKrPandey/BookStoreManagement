
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class ManagePurchaseForm extends JFrame {

    static ManagePurchaseForm frame;
    JLabel lTitle;
    JLabel lBookNo;
    JLabel lDealerName;
    JLabel lNoOfPages;
    JLabel lBookName;
    JLabel lUnitPrice;
    JLabel lTotalPrice;
    JLabel lTax;
    JLabel lDate;
    JTextField tfBookName;
    JTextField tfUnitPrice;
    JTextField tfTotalPrice;
    JTextField tfTax;
    JTextField tfDate;
    JComboBox cmbBookNo;
    JComboBox cmbDealerName;
    JComboBox cmbNoOfBooks;
    JButton btnSave;
    JButton btnExit;
    JButton btnRefresh;

    public static void main(String[] string) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ManagePurchaseForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ManagePurchaseForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(255, 255, 0);
        this.getContentPane().setBackground(color);

        //TextField Created
        tfBookName = new JTextField();
        tfUnitPrice = new JTextField();
        tfTotalPrice = new JTextField();
        tfTax = new JTextField();
        tfDate = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfBookName.getPreferredSize();
        dimMax = tfBookName.getMaximumSize();
        dimMin = tfBookName.getMinimumSize();
        dimOwn = new Dimension(100, 37);
        dimCmbBox = new Dimension(150, 37);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new java.awt.Color(204, 0, 102));
        lTitle.setText("Manage purchases Details");
        lTitle.setBounds(10, 10, 300, 50);
        this.add(lTitle);

        lBookNo = new javax.swing.JLabel();
        lBookNo.setText("Book No.");
        lBookNo.setPreferredSize(dimOwn);
        lBookNo.setMaximumSize(dimOwn);
        lBookNo.setMinimumSize(dimOwn);

        lDealerName = new javax.swing.JLabel();
        lDealerName.setText("Dealer Name");
        lDealerName.setPreferredSize(dimOwn);
        lDealerName.setMaximumSize(dimOwn);
        lDealerName.setMinimumSize(dimOwn);

        lBookName = new javax.swing.JLabel();
        lBookName.setText("Book Name");
        lBookName.setPreferredSize(dimOwn);
        lBookName.setMaximumSize(dimOwn);
        lBookName.setMinimumSize(dimOwn);

        lNoOfPages = new javax.swing.JLabel();
        lNoOfPages.setText("Number Of Books");
        lNoOfPages.setPreferredSize(dimOwn);
        lNoOfPages.setMaximumSize(dimOwn);
        lNoOfPages.setMinimumSize(dimOwn);

        lUnitPrice = new javax.swing.JLabel();
        lUnitPrice.setText("Unit Price");
        lUnitPrice.setPreferredSize(dimOwn);
        lUnitPrice.setMaximumSize(dimOwn);
        lUnitPrice.setMinimumSize(dimOwn);

        lTotalPrice = new javax.swing.JLabel();
        lTotalPrice.setText("Total Price");
        lTotalPrice.setPreferredSize(dimOwn);
        lTotalPrice.setMaximumSize(dimOwn);
        lTotalPrice.setMinimumSize(dimOwn);

        lTax = new javax.swing.JLabel();
        lTax.setText("Tax");
        lTax.setPreferredSize(dimOwn);
        lTax.setMaximumSize(dimOwn);
        lTax.setMinimumSize(dimOwn);

        lDate = new javax.swing.JLabel();
        lDate.setText("Date");
        lDate.setPreferredSize(dimOwn);;
        lDate.setMaximumSize(dimOwn);
        lDate.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lDealerName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lNoOfPages);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lUnitPrice);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lTotalPrice);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lTax);
        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lDate);

        //    lableBox.add(Box.createVerticalStrut(10));
        //    lableBox.add(lStock);
        //    
        lableBox.setBounds(50, 90, 150, 450);
        this.add(lableBox);

        String[] sbookNos = {"Select book no"};
        cmbBookNo = new JComboBox(sbookNos);
        BookDao.getAllBookNo(cmbBookNo);
        cmbBookNo.addItemListener(new ItemListener() {      @Override
            public void itemStateChanged(ItemEvent e) {

                try {
                    String sBookNo = cmbBookNo.getSelectedItem().toString();
                    int BookNo = Integer.parseInt(sBookNo);
                    ResultSet rs = null;

                    rs = BookDao.validate(BookNo, rs);
                    try {
                        rs.next();
                        tfBookName.setText(rs.getString("bookname"));
                        String sPrice = String.valueOf(rs.getDouble("price"));
                        tfUnitPrice.setText(sPrice);

                        String sNoOfBooks = String.valueOf(rs.getInt("stock"));
//                        cmbNoOfBooks.setSelectedItem(sNoOfBooks);

                        long millis = System.currentTimeMillis();
                        java.sql.Date date;
                        date = new java.sql.Date(millis);
                        tfDate.setText(date.toString());

                        DB.closeConnection();
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Unable to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");

                }
            }
        });

        String[] sDealerNames = {"Select a Dealer"};
        cmbDealerName = new JComboBox(sDealerNames);
        DealerDao.getAllDealers(cmbDealerName);
        cmbDealerName.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cmbDealerName.getSelectedItem().toString().equals("Select a Dealer")){
                    JOptionPane.showMessageDialog(null, "Invalid Input");
            }
            }
        });
        
        String[] sNoOfBooks = {"Select no of book "};
        cmbNoOfBooks = new JComboBox(sNoOfBooks);
        BookDao.getNoOfBooks(cmbNoOfBooks);
        cmbNoOfBooks.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

//                 String sBookNo=cmbBookNo.getSelectedItem().toString();
//                 int BookNo=Integer.parseInt(sBookNo);
//         
                try {
                    String squantity = cmbNoOfBooks.getSelectedItem().toString();
                    int qty = Integer.parseInt(squantity);

//                 BookDao.validate(BookNo,qty);
                    if (!(tfUnitPrice.getText().equals(""))) {
                        String sunitPrice = tfUnitPrice.getText();
                        double unitPrice = Double.parseDouble(sunitPrice);
                        Double totalPrice = unitPrice * qty;

                        String data = String.valueOf(totalPrice);

                        tfTotalPrice.setText(data);
                        tfTax.setText(String.valueOf(totalPrice * 0.07));
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input");

                }
            }
        });

        Box InputBox = Box.createVerticalBox();

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbBookNo);
        cmbBookNo.setPreferredSize(dimCmbBox);
        cmbBookNo.setMaximumSize(dimCmbBox);
        cmbBookNo.setMinimumSize(dimCmbBox);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbDealerName);
        cmbDealerName.setPreferredSize(dimCmbBox);
        cmbDealerName.setMaximumSize(dimCmbBox);
        cmbDealerName.setMinimumSize(dimCmbBox);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfBookName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbNoOfBooks);
        cmbNoOfBooks.setPreferredSize(dimCmbBox);
        cmbNoOfBooks.setMaximumSize(dimCmbBox);
        cmbNoOfBooks.setMinimumSize(dimCmbBox);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfUnitPrice);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfTotalPrice);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfTax);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfDate);

        InputBox.setBounds(200, 80, 150, 400);
        this.add(InputBox);

        btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String sBookNo = cmbBookNo.getSelectedItem().toString();
                    int BookNo = Integer.parseInt(sBookNo);
                    String sDealerName = cmbBookNo.getSelectedItem().toString();
                        if(cmbDealerName.getSelectedItem().toString().equals("Select a Dealer")){
                            throw new IllegalArgumentException("Plese Enter a valid dealer Name");
                        }
    
                    String sBookName = tfBookName.getText();
                    String sNoOfBook = cmbNoOfBooks.getSelectedItem().toString();
                    int NoOfBook = Integer.parseInt(sNoOfBook);

                    String sunitPrice = tfUnitPrice.getText();
                    double unitPrice = Double.parseDouble(sunitPrice);

                    String sTotalPrice = tfTotalPrice.getText();
                    double TotalPrice = Double.parseDouble(sTotalPrice);

                    String sTax = tfTax.getText();
                    double Tax = Double.parseDouble(sTax);

                    String sDate = tfDate.getText();
                    java.time.LocalDate lDate = SalesDao.getADate(sDate);

                    int i = PurchaseDao.save(BookNo, sDealerName, sBookName, NoOfBook, unitPrice, TotalPrice, Tax, lDate);
                    if (i > 0) {
                        ResultSet rs = null;
                        rs = BookDao.validate(BookNo, rs);
                        try {
                            rs.next();
                            int prevStock = rs.getInt("stock");
                            int updatedStock = prevStock + NoOfBook;
                            System.out.print(updatedStock);
                            //rs.setInt();//ps to be Used
                            int status = BookDao.updateStock(BookNo, updatedStock);
                            if (status > 0) {
                                JOptionPane.showMessageDialog(ManagePurchaseForm.this, " Stock Updated", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
                                JOptionPane.showMessageDialog(ManagePurchaseForm.this, " transaction is Succesfully Recorded", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Unable to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(ManagePurchaseForm.this, "Unable to Record transaction", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
                }
                 catch (DateTimeParseException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
                }
                 catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
                }
            }
        });

        btnExit = new JButton("Exit");;
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
                dispose();
                if (staffLoginSuccsess.frame == null) {
                    staffLoginSuccsess.main(new String[]{});
                }

            }
        });
        
              btnRefresh = new javax.swing.JButton();
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRefresh) {
                    dispose();
                    frame = new ManagePurchaseForm();
                }

            }
        });

        
        Box btnBox = Box.createHorizontalBox();
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnSave);
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);

        btnBox.setBounds(80, 500, 300, 60);
        this.add(btnBox);
        
        btnRefresh.setBounds(200 + 150 + 20, 80 +10, 100, 30);
        this.add(btnRefresh);

        this.setTitle("Manage Purchase Details");
        this.setLayout(null);
        this.setVisible(true);

    }

}
