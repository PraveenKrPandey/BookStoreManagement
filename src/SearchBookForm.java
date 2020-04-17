
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.layout.Border;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;


public class SearchBookForm extends JFrame {

    static SearchBookForm frame;
    JLabel lTitle;
    JButton btnSearch;
    JButton btnExit;
    JTextField tfBookNo;
    JTextField tfBookAuthor;
    JTextField tfBookCatagory;
    JRadioButton BookNo, BookAuthor, BookCatagory;
    JPanel thePanel = new JPanel();

    public static void main(String[] string) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new SearchBookForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public SearchBookForm() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);

        Color color = new java.awt.Color(25, 25, 112);
        this.getContentPane().setBackground(color);
        this.setTitle("Search Books");

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma", Font.BOLD, 24));
        lTitle.setForeground(new java.awt.Color(127, 255, 0));
        lTitle.setText("Search Desired Books");
        lTitle.setBounds(100, 10, 300, 60);
        this.add(lTitle);

        tfBookNo = new JTextField("Enter Book No.");
        tfBookNo.setEditable(true);

        tfBookAuthor = new JTextField("Enter Author Name");
        tfBookAuthor.setEditable(false);

        tfBookCatagory = new JTextField("Enter Book Category");
        tfBookCatagory.setEditable(false);

        Dimension dimPref, dimMax, dimMin, dimOwn, dimCmbBox;
        dimPref = tfBookAuthor.getPreferredSize();
        dimMax = tfBookAuthor.getMaximumSize();
        dimMin = tfBookAuthor.getMinimumSize();
        dimOwn = new Dimension(150, 50);
        dimCmbBox = new Dimension(150, 35);

        BookNo = new JRadioButton("Book No");
        BookNo.setPreferredSize(dimOwn);
        BookNo.setMaximumSize(dimOwn);
        BookNo.setMinimumSize(dimOwn);
        BookNo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBookCatagory.setEditable(false);
                tfBookNo.setEditable(true);
                tfBookAuthor.setEditable(false);

            }
        });

        BookAuthor = new JRadioButton("Book Author ");
        BookAuthor.setPreferredSize(dimOwn);
        BookAuthor.setMaximumSize(dimOwn);
        BookAuthor.setMinimumSize(dimOwn);
        BookAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBookCatagory.setEditable(false);
                tfBookNo.setEditable(false);
                tfBookAuthor.setEditable(true);

            }
        });

        BookCatagory = new JRadioButton("Book Catagory");
        BookCatagory.setPreferredSize(dimOwn);
        BookCatagory.setMaximumSize(dimOwn);
        BookCatagory.setMinimumSize(dimOwn);
        BookCatagory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tfBookCatagory.setEditable(true);
                tfBookNo.setEditable(false);
                tfBookAuthor.setEditable(false);

            }
        });

        ButtonGroup searchGroup = new ButtonGroup();
        searchGroup.add(BookNo);
        searchGroup.add(BookAuthor);
        searchGroup.add(BookCatagory);

        Box InputBox = Box.createVerticalBox();

        InputBox.add(Box.createVerticalStrut(20));
        InputBox.add(BookNo);

        InputBox.add(Box.createVerticalStrut(12));
        InputBox.add(BookAuthor);

        InputBox.add(Box.createVerticalStrut(12));
        InputBox.add(BookCatagory);
        InputBox.setBounds(30, 80, 200, 200);
        this.add(InputBox);

        BookNo.setSelected(true);

        Box searchBox = Box.createVerticalBox();
        searchBox.add(Box.createVerticalStrut(10));
        searchBox.add(tfBookNo);

        searchBox.add(Box.createVerticalStrut(10));
        searchBox.add(tfBookAuthor);

        searchBox.add(Box.createVerticalStrut(10));
        searchBox.add(tfBookCatagory);

        searchBox.setBounds(230, 80, 200, 200);

        this.add(searchBox);

        btnSearch = new JButton("Search");
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if (BookNo.isSelected()) {
                 try{
                    int Bookno = 0;
                    JTable table = null;
                    Bookno = Integer.parseInt(tfBookNo.getText());
                    table = BookDao.getTable(table, Bookno);

                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight());
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Search Result");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                 }catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid Input" + ex.getMessage());
                    }
        
                    
                } else if (BookAuthor.isSelected()) {
                    JTable table = null;
                    String Author = tfBookAuthor.getText();

                    table = BookDao.getTableAut(table, Author);
                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight());
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    // Adds the scrollpane to the frame0
                    JFrame f = new JFrame("Search Result");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                } else if (BookCatagory.isSelected()) {

                    JTable table = null;
                    String catagory = tfBookCatagory.getText();

                    table = BookDao.getTable(table, catagory);
                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                    table.setRowHeight(table.getRowHeight());
                    table.setAutoCreateRowSorter(true);

                    JScrollPane scrollPane = new JScrollPane(table);
                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                    JFrame f = new JFrame("Search Result");
                    f.add(scrollPane, BorderLayout.CENTER);
                    f.setSize(600, 700);
                    f.setVisible(true);
                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                }
                
            }
        });
        btnSearch.setBounds(450 + 20, 200 - 30, 80, 30);
        this.add(btnSearch);

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

        btnExit.setBounds(200, 350, 80, 30);
        this.add(btnExit);

        this.setLayout(null);
        this.setVisible(true);

    }

}
