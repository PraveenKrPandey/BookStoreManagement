
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;


public class ReportsMenu extends JFrame {

    public javax.swing.JButton btnBookDetails;
    public javax.swing.JButton btnCustomerReport;
    public javax.swing.JButton btnDealerReport;
    public javax.swing.JButton btnPurchaseReport;
    public javax.swing.JButton btnSalesReport;
    public javax.swing.JButton btnExit;
//    public javax.swing.JLabel imglabel;
    public javax.swing.JLabel lTitle;
    static ReportsMenu frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ReportsMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ReportsMenu() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        Color color = new java.awt.Color(154, 205, 50);
        this.getContentPane().setBackground(color);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(0, 0, 128));
        lTitle.setText("Report Menu");
        lTitle.setBounds(150, 10, 300, 50);
        this.add(lTitle);

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimOwn = new Dimension(150, 37);
        dimCmbBox = new Dimension(150, 37);

        btnBookDetails = new JButton();
        btnBookDetails.setPreferredSize(dimOwn);
        btnBookDetails.setMaximumSize(dimOwn);
        btnBookDetails.setMinimumSize(dimOwn);

        btnBookDetails.setText("Books Report");
        btnBookDetails.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnBookDetails) {
                    JTable table = null;
                    table = BookDao.getTableReport(table);

                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight());
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Books Report");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }

            }
        });

        btnCustomerReport = new JButton();
        btnCustomerReport.setText("Customer Report");
        btnCustomerReport.setPreferredSize(dimOwn);
        btnCustomerReport.setMaximumSize(dimOwn);
        btnCustomerReport.setMinimumSize(dimOwn);
        btnCustomerReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnCustomerReport) {
                    JTable table = null;
                    table = CustomerDao.getTableReport(table);

                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight() + 2);
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Customer Report");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }
            }
        });

        btnDealerReport = new JButton();
        btnDealerReport.setPreferredSize(dimOwn);
        btnDealerReport.setMaximumSize(dimOwn);
        btnDealerReport.setMinimumSize(dimOwn);
        btnDealerReport.setText("Dealer Report");
        btnDealerReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnDealerReport) {
                    JTable table = null;
                    table = DealerDao.getTableReport(table);

                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight() + 2);
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Dealer Report");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }

            }
        });

        btnPurchaseReport = new JButton();
        btnPurchaseReport.setPreferredSize(dimOwn);
        btnPurchaseReport.setMaximumSize(dimOwn);
        btnPurchaseReport.setMinimumSize(dimOwn);
        btnPurchaseReport.setText("Purchase Report");
        btnPurchaseReport.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnPurchaseReport) {
                    JTable table = null;
                    table = PurchaseDao.getTableReport(table);

                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight() + 2);
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Purchase Report");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                }

            }
        });

        btnSalesReport = new JButton();
        btnSalesReport.setText("Sales Report");
        btnSalesReport.setPreferredSize(dimOwn);
        btnSalesReport.setMaximumSize(dimOwn);
        btnSalesReport.setMinimumSize(dimOwn);
        btnSalesReport.addActionListener(new ActionListener() {
            			public void actionPerformed(ActionEvent e) {
                saleReportform.main(new String[]{});
            }
        });

        btnExit = new JButton("Exit");
        btnExit.setPreferredSize(dimOwn);
        btnExit.setMaximumSize(dimOwn);
        btnExit.setMinimumSize(dimOwn);
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

        Box btnBox = Box.createVerticalBox();
        btnBox.add(Box.createVerticalStrut(10));
        btnBox.add(btnBookDetails);
        btnBox.add(Box.createVerticalStrut(20));
        btnBox.add(btnCustomerReport);
        btnBox.add(Box.createVerticalStrut(20));
        btnBox.add(btnDealerReport);
        btnBox.add(Box.createVerticalStrut(20));
        btnBox.add(btnPurchaseReport);
        btnBox.add(Box.createVerticalStrut(20));
        btnBox.add(btnSalesReport);
        btnBox.add(Box.createVerticalStrut(20));
        btnBox.add(btnExit);
        btnBox.setBounds(150, 90, 200, 450);
        this.add(btnBox);

        this.setTitle("Reports ");
        this.setLayout(null);
        this.setVisible(true);

    }

}
