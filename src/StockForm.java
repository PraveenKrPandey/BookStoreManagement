
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

public class StockForm extends JFrame {

    static StockForm frame;
    JLabel lTitle;
    JLabel lBookNo;
    JLabel lStock;
    JComboBox cmbBookNo;
    JTextField tfstock;
    JButton btnExit;
    JButton btnRefresh;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new StockForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public StockForm() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(32, 178, 170);
        this.getContentPane().setBackground(color);

        tfstock = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfstock.getPreferredSize();
        dimMax = tfstock.getMaximumSize();
        dimMin = tfstock.getMinimumSize();
        dimOwn = new Dimension(150, 43);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);
        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(127, 255, 212));
        lTitle.setText("Stock Details");
        lTitle.setBounds(100, 10, 300, 60);

        this.add(lTitle);

        lBookNo = new javax.swing.JLabel();
        lBookNo.setText("Book No");
        lBookNo.setPreferredSize(dimOwn);
        lBookNo.setMaximumSize(dimOwn);
        lBookNo.setMinimumSize(dimOwn);

        lStock = new javax.swing.JLabel();
        lStock.setText("Stock");
        lStock.setPreferredSize(dimOwn);
        lStock.setMaximumSize(dimOwn);
        lStock.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lStock);

        lableBox.setBounds(50, 90, 150, 300);

        this.add(lableBox);

        String[] bookNos = {"Select book no"};
        cmbBookNo = new JComboBox(bookNos);
        BookDao.getAllBookNo(cmbBookNo);
        cmbBookNo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    String sBookNo = cmbBookNo.getSelectedItem().toString();
                    int BookNo = Integer.parseInt(sBookNo);
                    ResultSet rs = null;

                    rs = BookDao.validate(BookNo, rs);

                    rs.next();

                    String sNoOfBooks = String.valueOf(rs.getInt("stock"));
                    tfstock.setText(sNoOfBooks);

                    DB.closeConnection();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid Input" + ex.getMessage());
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Unable to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);

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
        InputBox.add(tfstock);
        tfstock.setPreferredSize(dimOwn);
        tfstock.setMaximumSize(dimOwn);
        tfstock.setMinimumSize(dimOwn);

        InputBox.setBounds(200, 80, 150, 300);
        this.add(InputBox);

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
                    frame = new StockForm();
                }

            }
        });

        Box btnBox = Box.createHorizontalBox();
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);

        btnBox.setBounds(80 + 60, 310 - 120 + 30, 300, 60);
        this.add(btnBox);

        btnRefresh.setBounds(200 + 150 + 20, 80 + 10, 100, 30);
        this.add(btnRefresh);

        this.setTitle("Stock Information");
        this.setLayout(null);
        this.setVisible(true);

    }

}
