
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.*;

public class AddDealerForm extends JFrame {

    static AddDealerForm frame;
    JLabel lTitle;
    JLabel lDealerName;
    JLabel lDealerAddress;
    JLabel lPhoneNo;
    JLabel lBookDetails;
    JTextField tfDealerName;
    JTextField tfDealerAddress;
    JTextField tfPhoneNo;
    JComboBox cmbCatagory;
    JButton btnsave;
    JButton btnExit;

    public static void main(String[] string) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AddDealerForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public AddDealerForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(95, 158, 160);
        this.getContentPane().setBackground(color);

        tfDealerName = new JTextField();
        tfPhoneNo = new JTextField();
        tfDealerAddress = new JTextField();

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfDealerName.getPreferredSize();
        dimMax = tfDealerName.getMaximumSize();
        dimMin = tfDealerName.getMinimumSize();
        dimOwn = new Dimension(100+20, 37);     //finally able to arrange at this dim
        dimCmbBox = new Dimension(150, 35);

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(255, 215, 0));
        lTitle.setText("Add Dealer");
        lTitle.setBounds(100, 10, 300, 60);
        this.add(lTitle);

        lDealerName = new javax.swing.JLabel();
        lDealerName.setText("Dealer Name");
        lDealerName.setPreferredSize(dimOwn);
        lDealerName.setMaximumSize(dimOwn);
        lDealerName.setMinimumSize(dimOwn);

        lBookDetails = new javax.swing.JLabel();
        lBookDetails.setText("Book specialized in");
        lBookDetails.setPreferredSize(dimOwn);
        lBookDetails.setMaximumSize(dimOwn);
        lBookDetails.setMinimumSize(dimOwn);

        lDealerAddress = new javax.swing.JLabel();
        lDealerAddress.setText("Dealer Address");
        lDealerAddress.setPreferredSize(dimOwn);
        lDealerAddress.setMaximumSize(dimOwn);
        lDealerAddress.setMinimumSize(dimOwn);

        lPhoneNo = new javax.swing.JLabel();
        lPhoneNo.setText("Phone No.");
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

        Box InputBox = Box.createVerticalBox();

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfDealerName);
//            cmbBookNo.setPreferredSize(dimCmbBox);
//            cmbBookNo.setMaximumSize(dimCmbBox);
//            cmbBookNo.setMinimumSize(dimCmbBox);
//  

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfDealerAddress);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfPhoneNo);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(cmbCatagory);

        InputBox.setBounds(200, 80, 150, 200);
        this.add(InputBox);

        btnsave = new JButton("SAVE");
        btnsave.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {
                try {
                    String Phone = tfPhoneNo.getText();
                    long phoneno = Long.parseLong(Phone);
                    String DealerName = tfDealerName.getText();
                    String DealerAddress = tfDealerAddress.getText();
                    String sCatagory = (String) cmbCatagory.getItemAt(cmbCatagory.getSelectedIndex());
                    int i = DealerDao.save(DealerName, DealerAddress, Phone, sCatagory);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(AddDealerForm.this, DealerName + " is Succesfully Added", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                        staffLoginSuccsess.main(new String[]{});
//                        AddBookform.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddDealerForm.this, "Unable to Add Dealer", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Plese Enter  Valid Inputs" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "" + ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
                }
            }

        });

        btnExit = new JButton("EXIT");;
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
        btnBox.add(btnsave);
        btnBox.add(Box.createHorizontalStrut(10));
        btnBox.add(btnExit);

        btnBox.setBounds(200, 310, 300, 60);
        this.add(btnBox);

        this.setTitle("Add Dealer");
        this.setLayout(null);
        this.setVisible(true);

    }

}
