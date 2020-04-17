
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class ManageBookForm extends JFrame {

    static ManageBookForm frame;
    JLabel lTitle;
    JLabel lBookNo;
    JLabel lBookName;
    JLabel lcatagory;
    JLabel lAuthrName;
    JLabel lPublisher;
    JLabel lNo_of_pages;
    JLabel lPrice;
//    JLabel lStock; 
    //JTextField tfBookNo;
    JTextField tfBookName;
    JTextField tfAuthrName;
    JTextField tfpublisher;
    JTextField tfNoOfPages;
    JTextField tfprice;
    //  JTextField tfstock;
    JComboBox cmbCatagory;
    JComboBox cmbBookNo;
    JButton btnUptdate;
    JButton btnDelete;
    JButton btnExit;
    JButton btnAddBook;
    JButton btnRefresh;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ManageBookForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();    // it is trying to cover all the exceptions 
                }
            }
        });
    }

    public ManageBookForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(70, 130, 180);
        this.getContentPane().setBackground(color);

        tfBookName = new JTextField();
        tfAuthrName = new JTextField();
        tfpublisher = new JTextField();
        tfNoOfPages = new JTextField();
        tfprice = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfBookName.getPreferredSize();
        dimMax = tfBookName.getMaximumSize();
        dimMin = tfBookName.getMinimumSize();
        dimOwn = new Dimension(100, 43);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new java.awt.Color(255, 215, 0));
        lTitle.setText("Manage Book Details");
        lTitle.setBounds(10, 10, 300, 50);
        this.add(lTitle);

        lBookNo = new javax.swing.JLabel();
        lBookNo.setText("Book No.");
        lBookNo.setForeground(new java.awt.Color(255, 215, 255));
        lBookNo.setPreferredSize(dimOwn);
        lBookNo.setMaximumSize(dimOwn);
        lBookNo.setMinimumSize(dimOwn);
//       System.out.println(dimPref);
//       System.out.println(dimMax);
//       System.out.println(dimMin);
//       

        lBookName = new javax.swing.JLabel();
        lBookName.setText("Book Name");
        lBookName.setForeground(new java.awt.Color(255, 215, 255));
        lBookName.setPreferredSize(dimOwn);
        lBookName.setMaximumSize(dimOwn);
        lBookName.setMinimumSize(dimOwn);

        lcatagory = new javax.swing.JLabel();
        lcatagory.setText("Category");
        lcatagory.setForeground(new java.awt.Color(255, 215, 255));

        lcatagory.setPreferredSize(dimOwn);
        lcatagory.setMaximumSize(dimOwn);
        lcatagory.setMinimumSize(dimOwn);

        lAuthrName = new javax.swing.JLabel();
        lAuthrName.setText("Author Name");
        lAuthrName.setForeground(new java.awt.Color(255, 215, 255));
        lAuthrName.setPreferredSize(dimOwn);
        lAuthrName.setMaximumSize(dimOwn);
        lAuthrName.setMinimumSize(dimOwn);

        lPublisher = new javax.swing.JLabel();
        lPublisher.setText("Publisher");
        lPublisher.setForeground(new java.awt.Color(255, 215, 255));
        lPublisher.setPreferredSize(dimOwn);
        lPublisher.setMaximumSize(dimOwn);
        lPublisher.setMinimumSize(dimOwn);

        lNo_of_pages = new javax.swing.JLabel();
        lNo_of_pages.setText("Number of pages");
        lNo_of_pages.setForeground(new java.awt.Color(255, 215, 255));
        lNo_of_pages.setPreferredSize(dimOwn);
        lNo_of_pages.setMaximumSize(dimOwn);
        lNo_of_pages.setMinimumSize(dimOwn);

        lPrice = new javax.swing.JLabel();
        lPrice.setText("Price");
        lPrice.setForeground(new java.awt.Color(255, 215, 255));
        lPrice.setPreferredSize(dimOwn);
        lPrice.setMaximumSize(dimOwn);
        lPrice.setMinimumSize(dimOwn);

//        lStock = new javax.swing.JLabel();
//        lStock.setText("Stock");
//        lStock.setPreferredSize(dimOwn);
//        lStock.setMaximumSize(dimOwn);
//        lStock.setMinimumSize(dimOwn);
//       
        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10 - 5));
        lableBox.add(lBookNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookName);

        lableBox.add(Box.createVerticalStrut(10 - 5));
        lableBox.add(lcatagory);

        lableBox.add(Box.createVerticalStrut(10 - 5));
        lableBox.add(lAuthrName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPublisher);

        lableBox.add(Box.createVerticalStrut(15));
        lableBox.add(lNo_of_pages);

        lableBox.add(Box.createVerticalStrut(30));
        lableBox.add(lPrice);

//    lableBox.add(Box.createVerticalStrut(10));
//    lableBox.add(lStock);
//    
        lableBox.setBounds(50, 90, 150, 450);
        this.add(lableBox);

        String[] catagoryStrings = {"science", "commerce", "arts", "others", "comics", "entertainment"};
        cmbCatagory = new JComboBox(catagoryStrings);

        String[] bookNos = {"Select book no"};
        cmbBookNo = new JComboBox(bookNos);
        BookDao.getAllBookNo(cmbBookNo);
        cmbBookNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    String sBookNo = cmbBookNo.getSelectedItem().toString();
                    int BookNo = Integer.parseInt(sBookNo);     //Numberformat
                    ResultSet rs = null;                        //SQL

                    rs = BookDao.validate(BookNo, rs);

                    rs.next();
                    tfBookName.setText(rs.getString("bookname"));
                    tfAuthrName.setText(rs.getString("authorname"));
                    String sPrice = String.valueOf(rs.getDouble("price"));
                    tfprice.setText(sPrice);
//                     String sNoOfBooks=String.valueOf(rs.getInt("stock"));
//                     cmbNoOfBooks.setSelectedItem(sNoOfBooks);
                    String sCatagory = rs.getString("catagory");
                    cmbCatagory.setSelectedItem(sCatagory);
                    tfpublisher.setText(rs.getString("publisher"));
                    String sNoOfPages = String.valueOf(rs.getInt("no_of_pages"));
                    tfNoOfPages.setText(sNoOfPages);

                    DB.closeConnection();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
                    System.out.print(ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to conect to Database"+ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);

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
        InputBox.add(tfBookName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbCatagory);
        cmbCatagory.setPreferredSize(dimCmbBox);
        cmbCatagory.setMaximumSize(dimCmbBox);
        cmbCatagory.setMinimumSize(dimCmbBox);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfAuthrName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfpublisher);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfNoOfPages);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfprice);

        InputBox.setBounds(200, 80, 150, 400);
        this.add(InputBox);

        JPanel btnPanel = new JPanel();

        btnUptdate = new JButton("Update");
        btnUptdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int BookNo;
                try {
                    BookNo = Integer.parseInt((String) cmbBookNo.getItemAt(cmbBookNo.getSelectedIndex()));
                    boolean found = BookDao.validatBook(BookNo);
                    if (!found) {
                        JOptionPane.showMessageDialog(ManageBookForm.this, "Sorry book didn't found", "Error", JOptionPane.ERROR_MESSAGE);
                        ManageBookForm.this.dispose();
                    } else {
                        String BookName = tfBookName.getText();
                        String sCatagory = (String) cmbCatagory.getItemAt(cmbCatagory.getSelectedIndex());
                        String AuthorName = tfAuthrName.getText();
                        String publisher = tfpublisher.getText();
                        int NoOfPages = Integer.parseInt(tfNoOfPages.getText());
                        double Price = Double.parseDouble(tfprice.getText());
                        int i = BookDao.update_book(BookNo, BookName, sCatagory, AuthorName, publisher, NoOfPages, Price);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(ManageBookForm.this, BookName + " is Succesfully updated", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                            staffLoginSuccsess.main(new String[]{});
//                            ManageBookForm.this.dispose();
                        } else {
                            JOptionPane.showMessageDialog(ManageBookForm.this, "Unable to UpdateBook", "Error", JOptionPane.ERROR_MESSAGE);

                        }
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Plese Enter  Valid Inputs"+ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int BookNo;
                try {
                    BookNo = Integer.parseInt((String) cmbBookNo.getItemAt(cmbBookNo.getSelectedIndex()));
                    boolean found = BookDao.validatBook(BookNo);
                    if (!found) {
                        JOptionPane.showMessageDialog(ManageBookForm.this, "Soory book Dont found", "Error", JOptionPane.ERROR_MESSAGE);
                      //  ManageBookForm.this.dispose();
                    } else {
                        int i = BookDao.deleteBoook(BookNo);
                        if (i > 0) {
                            JOptionPane.showMessageDialog(ManageBookForm.this, " book is Succesfully deleted", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                            staffLoginSuccsess.main(new String[]{});
//                            ManageBookForm.this.dispose();

                        } else {
                            JOptionPane.showMessageDialog(ManageBookForm.this, "Unable to DeleteBook", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Plese Enter  Valid Inputs"+ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        btnAddBook = new javax.swing.JButton();
        btnAddBook.setText("Add Book");
        btnAddBook.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnAddBook) {
                    AddBookform.main(new String[]{});

                }

            }
        });
        btnRefresh = new javax.swing.JButton();
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRefresh) {
                    dispose();
                    frame = new ManageBookForm();
                }

            }
        });

        btnExit = new JButton("Exit");
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

        Box btnBox = Box.createHorizontalBox();
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnUptdate);

        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnDelete);

        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);
//          btnPanel.add(btnUptdate);
//          btnPanel.add(btnExit);

        btnBox.setBounds(150, 500, 300, 60);
        this.add(btnBox);

        btnAddBook.setBounds(200 + 150 + 20, 80 + 10, 100, 30);
        this.add(btnAddBook);

        btnRefresh.setBounds(200 + 150 + 20, 80 + 50, 100, 30);
        this.add(btnRefresh);

        this.setTitle("Manage Book Details");
        this.setLayout(null);
        this.setVisible(true);

    }

}
