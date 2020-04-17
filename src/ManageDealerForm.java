
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;

public class ManageDealerForm extends JFrame {

    static ManageDealerForm frame;
    JLabel lTitle;
    JLabel lDealerAddress;
    JLabel lDealerName;
    JLabel lPhoneNo;
    JLabel lBookDetails;
    JTextField tfDealerName;
    JTextField tfDealerAddress;
    JTextField tfPhoneNo;
    JComboBox cmbCatagory;
    JComboBox cmbDealerName;
    JButton btnUpdate;
    JButton btnDelete;
    JButton btnRefresh;
    JButton btnExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new ManageDealerForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public ManageDealerForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(175, 238, 238);
        this.getContentPane().setBackground(color);

        tfDealerName = new JTextField();
        tfPhoneNo = new JTextField();
        tfDealerAddress = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfDealerName.getPreferredSize();
        dimMax = tfDealerName.getMaximumSize();
        dimMin = tfDealerName.getMinimumSize();
        dimOwn = new Dimension(100, 37);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18));
        lTitle.setForeground(new java.awt.Color(204, 0, 102));
        lTitle.setText("Manage Dealer");
        lTitle.setBounds(10, 10, 300, 50);
        this.add(lTitle);

        lDealerName = new javax.swing.JLabel();
        lDealerName.setText("Dealer Name");
        lDealerName.setPreferredSize(dimOwn);
        lDealerName.setMaximumSize(dimOwn);
        lDealerName.setMinimumSize(dimOwn);

        lBookDetails = new javax.swing.JLabel();
        lBookDetails.setText("Book specialized");
        lBookDetails.setPreferredSize(dimOwn);
        lBookDetails.setMaximumSize(dimOwn);
        lBookDetails.setMinimumSize(dimOwn);

        lDealerAddress = new javax.swing.JLabel();
        lDealerAddress.setText("Dealer Address");
        lDealerAddress.setPreferredSize(dimOwn);
        lDealerAddress.setMaximumSize(dimOwn);
        lDealerAddress.setMinimumSize(dimOwn);

        lPhoneNo = new javax.swing.JLabel();
        lPhoneNo.setText("Phone No. ");
        lPhoneNo.setPreferredSize(dimOwn);
        lPhoneNo.setMaximumSize(dimOwn);
        lPhoneNo.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lDealerName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lDealerAddress);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPhoneNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookDetails);

        lableBox.setBounds(50, 90, 150, 200);
        this.add(lableBox);

        String[] catagoryStrings = {"science", "commerce", "arts", "others", "comics", "entertainment"};
        cmbCatagory = new JComboBox(catagoryStrings);

        String[] DealerName = {"Select Dealer Name"};
        cmbDealerName = new JComboBox(DealerName);
        DealerDao.getAllDealerName(cmbDealerName);
        cmbDealerName.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String Dealer = cmbDealerName.getSelectedItem().toString();
                boolean found = DealerDao.validatDealer(Dealer, tfDealerAddress, tfPhoneNo, cmbCatagory);

            }
        });

        Box InputBox = Box.createVerticalBox();
        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbDealerName);
//      
        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfDealerAddress);

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
                String Dealer = cmbDealerName.getSelectedItem().toString();
                String sCatagory = cmbCatagory.getSelectedItem().toString();
//                String Dealer=cmbDealerName.getItemAt(cmbDealerName.getSelectedIndex());
//                String sCatagory=cmbCatagory.getItemAt(cmbCatagory.getSelectedIndex());
                String phoneNo = tfPhoneNo.getText();
                long phoneno= Long.parseLong(phoneNo);

                String DealerAddress = tfDealerAddress.getText();
                int i = DealerDao.update_Dealer(Dealer, sCatagory, phoneNo, DealerAddress);
                if (i > 0) {
                    JOptionPane.showMessageDialog(ManageDealerForm.this, Dealer + " is Succesfully updated", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                        staffLoginSuccsess.main(new String[]{});
//                        ManageBookForm.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(ManageDealerForm.this, "Unable to update dealer record", "Error", JOptionPane.ERROR_MESSAGE);

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
                String DealerName;
                DealerName = cmbDealerName.getSelectedItem().toString();
                int i = DealerDao.deleteDealer(DealerName);
                if (i > 0) {
                    JOptionPane.showMessageDialog(ManageDealerForm.this, " Dealer is Succesfully deleted", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                    staffLoginSuccsess.main(new String[]{});
//                    ManageDealerForm.this.dispose();

                } else {
                    JOptionPane.showMessageDialog(ManageDealerForm.this, "Unable to Delete Dealer", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }

        });

        btnRefresh = new javax.swing.JButton();
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRefresh) {
                    frame.dispose();
                    frame = new ManageDealerForm();
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
        this.setTitle("Manage Dealer");
        this.setLayout(null);
        this.setVisible(true);

    }

}
