
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddCustomerForm extends JFrame {

    static AddCustomerForm frame;
    JLabel lTitle;
    JLabel lCustomerName;
    JLabel lCustomerAddress;
    JLabel lPhoneNo;
    JLabel lBookIntrested;
    JTextField tfCustomerName;
    JTextField tfCustomerAddress;
    JTextField tfPhoneNo;
    JComboBox cmbCatagory;
    JButton btnSave;
    JButton btnExit;

    /*
     invoke later is used for java application and 
        invoke and wait used for java applet.
    */
    
    public static void main(String[] string) {
        EventQueue.invokeLater(new Runnable() {         
            public void run() {
                try {
                    frame = new AddCustomerForm();   
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public AddCustomerForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        Color color = new java.awt.Color(0, 204, 255);
        this.getContentPane().setBackground(color);
//         
        tfCustomerName = new JTextField();
        tfPhoneNo = new JTextField();
        tfCustomerAddress = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfCustomerName.getPreferredSize();
        dimMax = tfCustomerName.getMaximumSize();
        dimMin = tfCustomerName.getMinimumSize();
        dimOwn = new Dimension(120, 37);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(204, 0, 102));
        lTitle.setText("Add Customers ");
        lTitle.setBounds(100, 10, 300, 60);
        this.add(lTitle);

        lCustomerName = new javax.swing.JLabel();
        lCustomerName.setText("Customer Name");
        lCustomerName.setPreferredSize(dimOwn);
        lCustomerName.setMaximumSize(dimOwn);
        lCustomerName.setMinimumSize(dimOwn);

        lCustomerAddress = new javax.swing.JLabel();
        lCustomerAddress.setText("Customer Address");
        lCustomerAddress.setPreferredSize(dimOwn);
        lCustomerAddress.setMaximumSize(dimOwn);
        lCustomerAddress.setMinimumSize(dimOwn);

        lPhoneNo = new javax.swing.JLabel();
        lPhoneNo.setText("Phone No.");
        lPhoneNo.setPreferredSize(dimOwn);
        lPhoneNo.setMaximumSize(dimOwn);
        lPhoneNo.setMinimumSize(dimOwn);

        lBookIntrested = new javax.swing.JLabel();
        lBookIntrested.setText("Book interested in");
        lBookIntrested.setPreferredSize(dimOwn);
        lBookIntrested.setMaximumSize(dimOwn);
        lBookIntrested.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lCustomerName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lCustomerAddress);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPhoneNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookIntrested);

        lableBox.setBounds(50, 90, 150, 200);
        this.add(lableBox);

        String[] catagoryStrings = {"science", "commerce", "arts", "others", "comics", "entertainment"};
        cmbCatagory = new JComboBox(catagoryStrings);

        Box InputBox = Box.createVerticalBox();

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfCustomerName);
//            cmbBookNo.setPreferredSize(dimCmbBox);
//            cmbBookNo.setMaximumSize(dimCmbBox);
//            cmbBookNo.setMinimumSize(dimCmbBox);
//  

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfCustomerAddress);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfPhoneNo);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbCatagory);

        InputBox.setBounds(200, 80, 150, 200);
        this.add(InputBox);

        btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String Phone = tfPhoneNo.getText();
                    long phoneno= Long.parseLong(Phone);
                    String CustomerName = tfCustomerName.getText();
                    String CustomerAddress = tfCustomerAddress.getText();
                    String sCatagory = (String) cmbCatagory.getItemAt(cmbCatagory.getSelectedIndex());
                    int i = CustomerDao.save(CustomerName, CustomerAddress, Phone, sCatagory);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(AddCustomerForm.this, CustomerName + " is Succesfully Added", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                        staffLoginSuccsess.main(new String[]{});
//                        AddBookform.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddCustomerForm.this, "Unable to Add Customer", "Error", JOptionPane.ERROR_MESSAGE);

                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddCustomerForm.this, "Plese Enter  Valid Inputs"+ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddCustomerForm.this, "" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        btnExit = new JButton("EXIT");
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
        btnBox.add(btnSave);
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);

        btnBox.setBounds(80, 310, 300, 60);
        this.add(btnBox);

        this.setTitle("Add Customers");
        this.setLayout(null);
        this.setVisible(true);

    }
}
