/**
*PROBLEM STATEMENT: 
*  This project is designed for small scale retail book shop stores.
* PRESENT SYSTEM :
* some of the problem being faced in the present System is as follows:
*   1.Fast report generation is not possible.
*   2.Information about sales and Purchases are not properly maintained.
*   3.poor management of stock of books.
*   4.No management Decision can be taken because relevant Information is not available in database.
* PROPOSED SYSTEM:
*   Three Major Feature of Proposed System :
*       1.Stock Maintenance.
*       2.Proper recording of all the transaction entries in the central database.
*       3.Relevant Reports for Decision making.
*   Proposed system provides with the following  solution:
*       1.It facilitates staffs in servicing the customer in better and efficient manner
*       2.Faster retrieval of information about the desired book
*       3.Reduce paper work
*       4.Data security
*       5.Facilitates proper management of sales and purchase transactions.
*       6.All Details will be available on click.
*/

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class Stafflogin extends JFrame{

// static frame is declared  in order to access / open /close this satic login form 
//from other forms of the application
    public static Stafflogin frame;                

   
    
    private JLabel lTitle;
    private JLabel lUserName;
    private JLabel lpassword;
    
    private JTextField tfUserName;
    private JPasswordField tfPassword;
    
    
    
    private JButton btnLogin;
    private JButton btnReset;
    private JButton btnExit;
    
    // running the aplication
    public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Stafflogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    
    // creating the frame
    public Stafflogin(){
        
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //color
        Color color=new java.awt.Color(0,204,255);
        Color red=new java.awt.Color(255,99,71);
        this.getContentPane().setBackground(color);
        
        //
        JPanel InputPanel= new JPanel();
        InputPanel.setBackground(color);
        InputPanel.setLayout(null);
        InputPanel.setBounds(50, 50, 300, 200);
               
                tfUserName=new JTextField();        // textField is declared here to get the dimension
         
                //dimension to arrange and resize the component
                Dimension dimPref,dimMax,dimMin,dimOwn,dimCmbBox;
                dimPref=tfUserName.getPreferredSize();
                dimMax=tfUserName.getMaximumSize();
                dimMin=tfUserName.getMinimumSize();
                dimOwn= new Dimension(150, 37);     ///** good to arrange
                dimCmbBox= new Dimension(150, 35);  ///** good to arrange for combo box  


                //labels
        lUserName=new JLabel("User Name");
        lUserName.setFont(new Font("Arial", Font.BOLD, 14));
        lUserName.setBounds(30, 50, 100, 30);
        
        lpassword=new JLabel("Password");
        lpassword.setFont(new Font("Arial", Font.BOLD, 14));
        lpassword.setBounds(30, 130, 100, 30);

        // textfields and password field
        tfUserName=new JTextField();
        tfUserName.setBounds(150, 50, 100, 30);
        tfUserName.setToolTipText("Enter UserName");
        
        tfPassword=new JPasswordField();
        tfPassword.setBounds(150, 130, 100, 30);
        tfPassword.setToolTipText("Enter Password");
               
        // Adding to the Input pannel
                InputPanel.add(lUserName);
                InputPanel.add(tfUserName);
                InputPanel.add(lpassword);
                InputPanel.add(tfPassword);
                
                
                Border redLine= BorderFactory.createLineBorder(Color.WHITE,2);
                TitledBorder loginBorder= BorderFactory.createTitledBorder(redLine,"Login");
                loginBorder.setTitleFont(new Font("Arial Rounded MT", Font.BOLD, 14));
                InputPanel.setBorder(loginBorder);
                
        this.add(InputPanel);
    
        //btn panel for holding buttons
        JPanel btnPanel= new JPanel();
        //for positioning the components
        btnPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 25, 10));
            //for arrangement of buttons
        btnPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        btnPanel.setBackground(color);
        btnPanel.setBounds(50, 250, 300,50);
        
        //login Btn
        btnLogin= new JButton("Login");
        //Defining its action 
        btnLogin.addActionListener(new ActionListener(){
          @Override
            public void actionPerformed(ActionEvent e) {
                String name = tfUserName.getText();
                String password = String.valueOf(tfPassword.getText());
                if (StaffDao.validate(name, password)) {
                    staffLoginSuccsess.main(new String[]{});

                } else {
                    JOptionPane.showMessageDialog(null, "username or password is incorrect");
                }

            }

        });
            
            
        
        //btnReset= new JButton("Reset");
        //        exit button
        btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnExit) {
                    System.exit(0);
                }
            }

        });

        
  btnPanel.add(btnLogin);      
  //btnPanel.add(btnReset);      
  btnPanel.add(btnExit);      
  
        
  this.add(btnPanel);
    // title
     lTitle = new JLabel();
     lTitle.setFont(new java.awt.Font("Tahoma",Font.BOLD, 18)); 
     lTitle.setForeground(new Color(51, 51, 255));
     lTitle.setText("BookShop Managment System");
     lTitle.setBounds(50, 10, 300, 40);
    this.add(lTitle);

        tfUserName.requestFocus();
        this.setLayout(null);
        //this.setSize(500,500);
        this.setBounds(300,150,500,500);
        this.getContentPane().setBounds(200,200,500,500);
        this.setTitle("Staff Login");
    }
    
    
    static void clearTf(){
        frame.tfPassword.setText("");
//        frame.tfUserName.setText("");
          frame.tfPassword.requestFocus();
    }
    
}
 