
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class staffLoginSuccsess extends JFrame {

    public javax.swing.JButton addbookbtn;
    public javax.swing.JButton adddealerbtn;
    public javax.swing.JLabel imglabel;
    public javax.swing.JLabel lTitle;
    public javax.swing.JButton logoutbtn;
    public javax.swing.JButton managebookbtn;
    public javax.swing.JButton addcustbtn;
    public javax.swing.JButton managecustbtn;
    public javax.swing.JButton managedealerbtn;
    public javax.swing.JButton managepurchbtn;
    public javax.swing.JButton managesalesbtn;
    public javax.swing.JButton reportsbtn;
    public javax.swing.JButton searchbookbtn;
    public javax.swing.JButton stockbtn;
    static staffLoginSuccsess frame;

    public static void main(String[] args) {
// Create the frame on the event dispatching thread.
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new staffLoginSuccsess();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    // creating the frame
    public staffLoginSuccsess() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 900, 750);

        Color color = new java.awt.Color(0, 204, 255);
        this.getContentPane().setBackground(color);

        //DECLARED addBookButton HERE TO GET DIMENSSION
        addbookbtn = new javax.swing.JButton();
        //DIMENSIONs
        Dimension dimPref, dimMax, dimMin, dimOwn, btnDim;
        dimPref = addbookbtn.getPreferredSize();
        dimMax = addbookbtn.getMaximumSize();
        dimMin = addbookbtn.getMinimumSize();
        dimOwn = new Dimension(200, 30);
        btnDim = new Dimension(200, 30);

        addbookbtn = new javax.swing.JButton();
        addbookbtn.setPreferredSize(btnDim);
        addbookbtn.setMaximumSize(btnDim);
        addbookbtn.setMinimumSize(btnDim);
        addbookbtn.setText("Add Books");
// launching books Adding  form
        addbookbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addbookbtn) {
                    AddBookform.main(new String[]{});

                }

            }
        });

        managepurchbtn = new javax.swing.JButton();
        managepurchbtn.setText("Purchase Managment");
        managepurchbtn.setPreferredSize(dimOwn);
        managepurchbtn.setMaximumSize(dimOwn);
        managepurchbtn.setMinimumSize(dimOwn);
// launching Puchasemanagement form
        managepurchbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == managepurchbtn) {
                    ManagePurchaseForm.main(new String[]{});
                }

            }
        });

        addcustbtn = new javax.swing.JButton();
        addcustbtn.setText("Add Customers");
        addcustbtn.setPreferredSize(btnDim);
        addcustbtn.setMaximumSize(btnDim);
        addcustbtn.setMinimumSize(btnDim);
// launching customer management form
        addcustbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == addcustbtn) {
                    AddCustomerForm.main(new String[]{});
                }

            }
        });
   
        managecustbtn = new javax.swing.JButton();
        managecustbtn.setText("Manage Customers");
        managecustbtn.setPreferredSize(btnDim);
        managecustbtn.setMaximumSize(btnDim);
        managecustbtn.setMinimumSize(btnDim);
// launching customer management form
        managecustbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == managecustbtn) {
                    ManageCustomerForm.main(new String[]{});
                   
                }

            }
        });

        adddealerbtn = new javax.swing.JButton();
        adddealerbtn.setText("Add Dealers");
        adddealerbtn.setPreferredSize(btnDim);
        adddealerbtn.setMaximumSize(btnDim);
        adddealerbtn.setMinimumSize(btnDim);
// launching dealer adding form
        adddealerbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == adddealerbtn) {
                    AddDealerForm.main(new String[]{});
                }

            }
        });

        managedealerbtn = new javax.swing.JButton();
        managedealerbtn.setText("Dealer Managment");
        managedealerbtn.setPreferredSize(btnDim);
        managedealerbtn.setMaximumSize(btnDim);
        managedealerbtn.setMinimumSize(btnDim);
// launching Deamler management form
        managedealerbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == managedealerbtn) {
                    ManageDealerForm.main(new String[]{});
                }

            }
        });

        managesalesbtn = new javax.swing.JButton();
        managesalesbtn.setText("Sales Management");
        managesalesbtn.setPreferredSize(btnDim);
        managesalesbtn.setMaximumSize(btnDim);
        managesalesbtn.setMinimumSize(btnDim);
// launching Sales management form
        managesalesbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == managesalesbtn) {
                    ManageSaleForm.main(new String[]{});
                }
            }
        });

        searchbookbtn = new javax.swing.JButton();
        searchbookbtn.setText("Search Books");
        searchbookbtn.setPreferredSize(btnDim);
        searchbookbtn.setMaximumSize(btnDim);
        searchbookbtn.setMinimumSize(btnDim);
// launching Search Book form
        searchbookbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == searchbookbtn) {
                    SearchBookForm.main(new String[]{});
                }

            }
        });

        managebookbtn = new javax.swing.JButton();
        managebookbtn.setText("Books Management");
        managebookbtn.setPreferredSize(btnDim);
        managebookbtn.setMaximumSize(btnDim);
        managebookbtn.setMinimumSize(btnDim);
// launching Book management form
        managebookbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == managebookbtn) {
                    ManageBookForm.main(new String[]{});
                }

            }
        });

        stockbtn = new javax.swing.JButton();
        stockbtn.setText("Stock Available");
        stockbtn.setPreferredSize(btnDim);
        stockbtn.setMaximumSize(btnDim);
        stockbtn.setMinimumSize(btnDim);

        stockbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == stockbtn) {
                    StockForm.main(new String[]{});
                }

            }
        });

        imglabel = new javax.swing.JLabel();
        imglabel.setText("image of book store");

        logoutbtn = new javax.swing.JButton();
        logoutbtn.setText("Log out");
        logoutbtn.setPreferredSize(btnDim);
        logoutbtn.setMaximumSize(btnDim);
        logoutbtn.setMinimumSize(btnDim);
// launching Login form  
        logoutbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (e.getSource() == logoutbtn) {
                    setVisible(false);
                    dispose();
                    if (Stafflogin.frame == null) {
                        Stafflogin.main(new String[]{});
                    } else {
                        Stafflogin.clearTf();
                    }
                }

            }
        });

        reportsbtn = new javax.swing.JButton();
        reportsbtn.setText("View Reports");
        reportsbtn.setPreferredSize(btnDim);
        reportsbtn.setMaximumSize(btnDim);
        reportsbtn.setMinimumSize(btnDim);

        reportsbtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == reportsbtn) {
                    ReportsMenu.main(new String[]{});
                }

            }
        });

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(255, 255, 0));
        lTitle.setText("Book Shop Management System");
        lTitle.setBounds(200, 10, 400, 50);
        this.add(lTitle);
        Box theBox = Box.createVerticalBox();

        theBox.add(addbookbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));
        theBox.add(managebookbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(searchbookbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(stockbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(addcustbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(managecustbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(adddealerbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(managedealerbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(managepurchbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(managesalesbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(reportsbtn);
        theBox.add(Box.createVerticalStrut(10 + 10));

        theBox.add(logoutbtn);
        theBox.add(Box.createVerticalStrut(20));

        theBox.setBounds(20, 100, 170, 600);

        // no effects    theBox.add(Box.createRigidArea(new Dimension(100, 25)));
        this.add(theBox);

        imglabel = new JLabel();
        imglabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/photo-1463320726281-696a485928c7.jpg")));
        imglabel.setBounds(250, 100, 600, 550);
        this.add(imglabel);

        this.setTitle("Main menu");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
}
