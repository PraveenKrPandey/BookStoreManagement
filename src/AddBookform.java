
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;

public class AddBookform extends JFrame {

    static AddBookform frame;

    JLabel lTitle;
    JLabel lBookNo;
    JLabel lBookName;
    JLabel lcatagory;
    JLabel lAuthrName;
    JLabel lPublisher;
    JLabel lNo_of_pages;
    JLabel lPrice;
    JLabel lStock;

    JTextField tfBookNo;
    JTextField tfBookName;
    JTextField tfAuthrName;
    JTextField tfpublisher;
    JTextField tfNoOfPages;
    JTextField tfprice;
    JTextField tfstock;

    JComboBox catagory;

    JButton btnsave;
    JButton btnExit;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new AddBookform();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddBookform() {

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        Color color = new java.awt.Color(0, 191, 255);
        this.getContentPane().setBackground(color);

        //TextField Created
        tfBookName = new JTextField();
        tfBookNo = new JTextField();
        tfAuthrName = new JTextField();
        tfpublisher = new JTextField();
        tfNoOfPages = new JTextField();
        tfprice = new JTextField();
        tfstock = new JTextField();

        /// Dimension of textField is Used to Aling Lable
        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfBookNo.getPreferredSize();
        dimMax = tfBookNo.getMaximumSize();
        dimMin = tfBookNo.getMinimumSize();
        dimOwn = new Dimension(100, 40);
        dimCmbBox = new Dimension(150, 35);

        //Label Created And Arranged
        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(220, 20, 60));
        lTitle.setText("Add Book Details");
        lTitle.setBounds(100, 10, 300, 60);
        this.add(lTitle);

        lBookNo = new javax.swing.JLabel();
        lBookNo.setText("Book No");
        lBookNo.setPreferredSize(dimOwn);
        lBookNo.setMaximumSize(dimOwn);
        lBookNo.setMinimumSize(dimOwn);

        lBookName = new javax.swing.JLabel();
        lBookName.setText("Book Name");
        lBookName.setPreferredSize(dimOwn);
        lBookName.setMaximumSize(dimOwn);
        lBookName.setMinimumSize(dimOwn);

        lcatagory = new javax.swing.JLabel();
        lcatagory.setText("category");
        lcatagory.setPreferredSize(dimOwn);
        lcatagory.setMaximumSize(dimOwn);
        lcatagory.setMinimumSize(dimOwn);

        lAuthrName = new javax.swing.JLabel();
        lAuthrName.setText("Author Name");
        lAuthrName.setPreferredSize(dimOwn);
        lAuthrName.setMaximumSize(dimOwn);
        lAuthrName.setMinimumSize(dimOwn);

        lPublisher = new javax.swing.JLabel();
        lPublisher.setText("Publisher");
        lPublisher.setPreferredSize(dimOwn);
        lPublisher.setMaximumSize(dimOwn);
        lPublisher.setMinimumSize(dimOwn);

        lNo_of_pages = new javax.swing.JLabel();
        lNo_of_pages.setText("Number of pages");
        lNo_of_pages.setPreferredSize(dimOwn);
        lNo_of_pages.setMaximumSize(dimOwn);
        lNo_of_pages.setMinimumSize(dimOwn);

        lPrice = new javax.swing.JLabel();
        lPrice.setText("Price");
        lPrice.setPreferredSize(dimOwn);
        lPrice.setMaximumSize(dimOwn);
        lPrice.setMinimumSize(dimOwn);

        lStock = new javax.swing.JLabel();
        lStock.setText("Stock");
        lStock.setPreferredSize(dimOwn);
        lStock.setMaximumSize(dimOwn);
        lStock.setMinimumSize(dimOwn);

        Box lableBox = Box.createVerticalBox();

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookNo);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lBookName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lcatagory);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lAuthrName);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPublisher);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lNo_of_pages);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lPrice);

        lableBox.add(Box.createVerticalStrut(10));
        lableBox.add(lStock);

        lableBox.setBounds(50, 80, 150, 450);
        this.add(lableBox);

        String[] catagoryStrings = {"science", "commerce", "arts", "others", "comics", "entertainment"};
        catagory = new JComboBox(catagoryStrings);
        Box InputBox = Box.createVerticalBox();

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfBookNo);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfBookName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(catagory);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfAuthrName);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfpublisher);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfNoOfPages);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfprice);

        InputBox.add(Box.createVerticalStrut(10));
        InputBox.add(tfstock);

        InputBox.setBounds(200, 80, 150, 400);
        this.add(InputBox);

        btnsave = new JButton("SAVE");
        btnsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int Bookno = 0;
                String BookName = "";
                String sCatagory = "";
                String AuthorName = "";
                String publisher = "";
                int NoOfPages = 0;
                double Price = 0;
                int Stock = 0;
                try {
                    Bookno = Integer.parseInt(tfBookNo.getText());
                    BookName = tfBookName.getText();
                    sCatagory = (String) catagory.getItemAt(catagory.getSelectedIndex());
                    AuthorName = tfAuthrName.getText();
                    publisher = tfpublisher.getText();
                    NoOfPages = Integer.parseInt(tfNoOfPages.getText());
                    Price = Double.parseDouble(tfprice.getText());
                    Stock = Integer.parseInt(tfstock.getText());

                    int i = BookDao.save(Bookno, BookName, sCatagory, AuthorName, publisher, NoOfPages, Price, Stock);
                    if (i > 0) {
                        JOptionPane.showMessageDialog(AddBookform.this, BookName + " is Succesfully Added", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
//                        staffLoginSuccsess.main(new String[]{});
//                        AddBookform.this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(AddBookform.this, "Unable to Add Book", "Error", JOptionPane.ERROR_MESSAGE);

                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddBookform.this, "Plese Enter  Valid Inputs"+ex.getMessage(), "Invalid Input", JOptionPane.ERROR_MESSAGE);
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

        btnBox.add(Box.createHorizontalStrut(100));
        btnBox.add(btnsave);

        btnBox.add(Box.createHorizontalStrut(20));
        btnBox.add(btnExit);

        btnBox.setBounds(40, 500, 300, 60);
        this.add(btnBox);
        this.setTitle("Add Books");
        this.setLayout(null);
        this.setVisible(true);

    }

}
