
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class ManageCustomerForm extends JFrame {

    static ManageCustomerForm  frame;
    JLabel lTitle;
    JLabel lCustomerAddress;
    JLabel lCustomerName;
    JLabel lPhoneNo;
    JLabel lBookDetails;
    JTextField tfCustomerName;
    JTextField tfCustomerAddress;
    JTextField tfPhoneNo;
    JComboBox cmbCatagory;
    JComboBox cmbCustomerName;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnRefresh;
    JButton btnExit;

    public static void main(String[] string) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ManageCustomerForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ManageCustomerForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(175, 238, 238);
        this.getContentPane().setBackground(color);

        tfCustomerName = new JTextField();
        tfPhoneNo = new JTextField();
        tfCustomerAddress = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfCustomerName.getPreferredSize();
        dimMax = tfCustomerName.getMaximumSize();
        dimMin = tfCustomerName.getMinimumSize();
        dimOwn = new Dimension(150, 37);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new java.awt.Color(204, 0, 102));
        lTitle.setText("Manage Customer");
        lTitle.setBounds(10, 10, 300, 50);
        this.add(lTitle);

        lCustomerName = new javax.swing.JLabel();
        lCustomerName.setText("Customer Name");
        lCustomerName.setPreferredSize(dimOwn);
        lCustomerName.setMaximumSize(dimOwn);
        lCustomerName.setMinimumSize(dimOwn);

        lBookDetails = new javax.swing.JLabel();
        lBookDetails.setText("Books interested in");
        lBookDetails.setPreferredSize(dimOwn);
        lBookDetails.setMaximumSize(dimOwn);
        lBookDetails.setMinimumSize(dimOwn);

        lCustomerAddress = new javax.swing.JLabel();
        lCustomerAddress.setText("Customer Address");
        lCustomerAddress.setPreferredSize(dimOwn);
        lCustomerAddress.setMaximumSize(dimOwn);
        lCustomerAddress.setMinimumSize(dimOwn);

        lPhoneNo = new javax.swing.JLabel();
        lPhoneNo.setText("Phone No. ");
        lPhoneNo.setPreferredSize(dimOwn);
        lPhoneNo.setMaximumSize(dimOwn);
        lPhoneNo.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lCustomerName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lCustomerAddress);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPhoneNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookDetails);

        lableBox.setBounds(50, 90, 150, 200);
        this.add(lableBox);

        String[] catagoryStrings = {"science", "commerce", "arts", "others", "comics", "entertainment"};
        cmbCatagory = new JComboBox(catagoryStrings);

        String[] CustomerName = {"Select Customer Name"};
        cmbCustomerName = new JComboBox(CustomerName);
        CustomerDao.getAllCustomers(cmbCustomerName);
        cmbCustomerName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                     String Customer = cmbCustomerName.getSelectedItem().toString();
                boolean found = CustomerDao.validatCustomer(Customer, tfCustomerAddress, tfPhoneNo, cmbCatagory);
            }
            });

        Box InputBox = Box.createVerticalBox();
        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbCustomerName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfCustomerAddress);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfPhoneNo);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbCatagory);

        InputBox.setBounds(200, 80, 150, 200);
        this.add(InputBox);

        JPanel btnPanel = new JPanel();

        btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
           try{
                String Customer = cmbCustomerName.getSelectedItem().toString();
                String sCatagory = cmbCatagory.getSelectedItem().toString();
//                String Customer=cmbCustomerName.getItemAt(cmbCustomerName.getSelectedIndex());
//                String sCatagory=cmbCatagory.getItemAt(cmbCatagory.getSelectedIndex());
                String phoneNo = tfPhoneNo.getText();
                long phoneno= Long.parseLong(phoneNo);

                String CustomerAddress = tfCustomerAddress.getText();
                int i = CustomerDao.update_Customer(Customer, sCatagory, phoneNo, CustomerAddress);
                if (i > 0) {
                    JOptionPane.showMessageDialog(ManageCustomerForm.this, Customer + " is Succesfully updated", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                        staffLoginSuccsess.main(new String[]{});
//                        ManageBookForm.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(ManageCustomerForm.this, "Unable to update dealer record", "Error", JOptionPane.ERROR_MESSAGE);

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
                String CustomerName;
                CustomerName = cmbCustomerName.getSelectedItem().toString();
                int i = CustomerDao.deleteCustomer(CustomerName);
                if (i > 0) {
                    JOptionPane.showMessageDialog(ManageCustomerForm.this, " Customer is Succesfully deleted", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                    staffLoginSuccsess.main(new String[]{});
//                    ManageCustomerForm.this.dispose();

                } else {
                    JOptionPane.showMessageDialog(ManageCustomerForm.this, "Unable to Delete Customer", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        btnRefresh = new javax.swing.JButton();
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRefresh) {
                    frame.dispose();
                    frame = new ManageCustomerForm();
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
        btnBox.add(btnUpdate);

        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnDelete);

        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);

        btnBox.setBounds(150, 400, 300, 60);
        this.add(btnBox);

        btnRefresh.setBounds(200 + 150 + 20, 80 + 10, 100, 30);
        this.add(btnRefresh);
        this.setTitle("Manage Customer");
        this.setLayout(null);
        this.setVisible(true);

    }

}
