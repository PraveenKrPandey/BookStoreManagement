import java.awt.Color;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.print.*;
import java.text.ParseException;
import java.time.format.DateTimeParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;


public class ManageSaleForm extends JFrame  {
static ManageSaleForm frame;
    JLabel lTitle; 
    JLabel lBookNo; 
    JLabel lCustomerName; 
    JLabel lAuthorName; 
    JLabel lQty; 
    JLabel lBookName; 
    JLabel lUnitPrice; 
    JLabel lTotalPrice; 
    JLabel lTax; 
    JLabel lDate; 
    JTextField tfBookName;
    JTextField tfAuthorName;
    JTextField tfUnitPrice;
    JTextField tfTotalPrice;
    JTextField tfTax;
    JTextField tfDate;
    JComboBox cmbBookNo;
    JComboBox cmbNoOfBooks;
    JComboBox cmbCustomerName;
    String recipt;
    JTextArea taRecipt;
    JButton btnSave;
    JButton btnExit;
    JButton btnRefresh;
    JButton btnPrintRecipt;

public     static void main(String[] string) {
        		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new ManageSaleForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

    }
    public ManageSaleForm(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setBounds(100, 100, 700, 600);
        
        Color color=new java.awt.Color(138,43,226);
        this.getContentPane().setBackground(color);

        Border blackline=BorderFactory.createLineBorder(Color.black, 2, true);
        Border redLine=BorderFactory.createLineBorder(Color.red, 2, true);
        Border titelBorderRed=BorderFactory.createTitledBorder(redLine, "Recipt");
        Border titelBorderBlack=BorderFactory.createTitledBorder(blackline, "Recipt");
        
        
        tfBookName=new JTextField();
        tfAuthorName=new JTextField();
        tfUnitPrice=new JTextField();
        tfTotalPrice=new JTextField();
        tfTax=new JTextField();
        tfDate=new JTextField();
        taRecipt=new JTextArea();
        taRecipt.setLineWrap(true);
        taRecipt.setBounds(400, 90, 200, 300);
        taRecipt.setBorder(titelBorderBlack);
        this.add(taRecipt);  
            
                    Dimension dimPref,dimMax,dimMin,dimOwn,dimCmbBox;
                dimOwn= new Dimension(100, 37);         
                dimCmbBox= new Dimension(150, 37);    
                dimPref=tfBookName.getPreferredSize();
                dimMax=tfBookName.getMaximumSize();
                dimMin=tfBookName.getMinimumSize();

        lTitle = new javax.swing.JLabel();
        lTitle.setFont(new java.awt.Font("Tahoma",Font.BOLD, 24)); 
        lTitle.setForeground(new java.awt.Color(255,99,71));
        lTitle.setText("Manage Sales Details");
        lTitle.setBounds(100, 10, 300, 60);
        this.add(lTitle);

        lCustomerName = new javax.swing.JLabel();
        lCustomerName.setText("Customer Name");
        lCustomerName.setPreferredSize(dimOwn);
        lCustomerName.setMaximumSize(dimOwn);
        lCustomerName.setMinimumSize(dimOwn);
       
        
        lBookNo = new javax.swing.JLabel();
        lBookNo.setText("Book No.");
        lBookNo.setPreferredSize(dimOwn);
        lBookNo.setMaximumSize(dimOwn);
        lBookNo.setMinimumSize(dimOwn);
       
        lBookName = new javax.swing.JLabel();
        lBookName.setText("Book Name");
        lBookName.setPreferredSize(dimOwn);
        lBookName.setMaximumSize(dimOwn);
        lBookName.setMinimumSize(dimOwn);
       
        lAuthorName = new javax.swing.JLabel();
        lAuthorName.setText("Author Name");
        lAuthorName.setPreferredSize(dimOwn);
        lAuthorName.setMaximumSize(dimOwn);
        lAuthorName.setMinimumSize(dimOwn);
       
        lUnitPrice = new javax.swing.JLabel();
        lUnitPrice.setText("Unit Price");
        lUnitPrice.setPreferredSize(dimOwn);
        lUnitPrice.setMaximumSize(dimOwn);
        lUnitPrice.setMinimumSize(dimOwn);
       
        lQty = new javax.swing.JLabel();
        lQty.setText("Quantity");
        lQty.setPreferredSize(dimOwn);
        lQty.setMaximumSize(dimOwn);
        lQty.setMinimumSize(dimOwn);
       
        lTotalPrice = new javax.swing.JLabel();
        lTotalPrice.setText("Total Price");
        lTotalPrice.setPreferredSize(dimOwn);
        lTotalPrice.setMaximumSize(dimOwn);
        lTotalPrice.setMinimumSize(dimOwn);
       
        lTax = new javax.swing.JLabel();
        lTax.setText("Tax");
        lTax.setPreferredSize(dimOwn);
        lTax.setMaximumSize(dimOwn);
        lTax.setMinimumSize(dimOwn);
       
        lDate = new javax.swing.JLabel();
        lDate.setText("Date");
        lDate.setPreferredSize(dimOwn);
        lDate.setMaximumSize(dimOwn);
        lDate.setMinimumSize(dimOwn);
       
//      adding to the lable Box1 
                Box lableBox  = Box.createVerticalBox();
    

            lableBox.add(Box.createVerticalStrut(0));
            lableBox.add(lCustomerName);

            lableBox.add(Box.createVerticalStrut(10-2));
            lableBox.add(lBookNo);
        
            lableBox.add(Box.createVerticalStrut(10-2));
            lableBox.add(lBookName);
        
            lableBox.add(Box.createVerticalStrut(10-2));
            lableBox.add(lAuthorName);
        
            lableBox.add(Box.createVerticalStrut(10-3));
            lableBox.add(lUnitPrice);
         
            lableBox.add(Box.createVerticalStrut(10-3));
            lableBox.add(lQty);
        
            lableBox.add(Box.createVerticalStrut(10-3));
            lableBox.add(lTotalPrice);
        
            lableBox.add(Box.createVerticalStrut(10-3));
            lableBox.add(lTax);
        
            lableBox.add(Box.createVerticalStrut(10-3));
            lableBox.add(lDate);
                    
            lableBox.setBounds(50, 90, 150, 450);
             this.add(lableBox);

             String[] sbookNos = {"Select book no"};
            cmbBookNo = new JComboBox(sbookNos);
            BookDao.getAllBookNo(cmbBookNo);
            cmbBookNo.addItemListener(new ItemListener() {
             @Override
             public void itemStateChanged(ItemEvent e) {
               try{
                 String sBookNo=cmbBookNo.getSelectedItem().toString();
                 int BookNo=Integer.parseInt(sBookNo);
                 ResultSet rs=null;
                 
                 rs=BookDao.validate(BookNo,rs);
                 try {
                     rs.next();
                     tfBookName.setText(rs.getString("bookname"));
                     tfAuthorName.setText(rs.getString("authorname"));
                     String sPrice=String.valueOf(rs.getDouble("price"));
                     tfUnitPrice.setText(sPrice);
                     String sNoOfBooks=String.valueOf(rs.getInt("stock"));
//                     cmbNoOfBooks.setSelectedItem(sNoOfBooks);
                     
                     DB.closeConnection();      // CLOSE THE CONNECTION
                 } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Unable to conect to Database", "Error Connection", JOptionPane.WARNING_MESSAGE);    

                 }
               }
               catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());

            }  
             }
         });
            
                      String[] sCustomerName = {"Select a Customer"};
            cmbCustomerName = new JComboBox(sCustomerName);
            CustomerDao.getAllCustomers(cmbCustomerName);
            cmbCustomerName.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(cmbCustomerName.getSelectedItem().toString().equals("Select a Customer")){
                    JOptionPane.showMessageDialog(null, "Invalid Input");
            }
            }
        });
            
            String[] sNoOfBooks = {"Select no of book"};
            cmbNoOfBooks = new JComboBox(sNoOfBooks);
            BookDao.getNoOfBooks(cmbNoOfBooks);
            cmbNoOfBooks.addItemListener(new ItemListener() {
             @Override
             public void itemStateChanged(ItemEvent e) {
            try{     
//                 String sBookNo=cmbBookNo.getSelectedItem().toString();
//                 int BookNo=Integer.parseInt(sBookNo);
                 
                 String squantity =cmbNoOfBooks.getSelectedItem().toString();
                 int qty=Integer.parseInt(squantity);

//                 BookDao.validate(BookNo,qty);
                     if(!(tfUnitPrice.getText().equals(""))){
                    String  sunitPrice=tfUnitPrice.getText();
                 double unitPrice=Double.parseDouble(sunitPrice);
                Double totalPrice=unitPrice*qty;
                
                 String data=String.valueOf(totalPrice);
                 
    
            tfTotalPrice.setText(data);
            tfTax.setText(String.valueOf(totalPrice*0.07));
           
         long millis= System.currentTimeMillis();
         java.sql.Date date;
         date= new java.sql.Date(millis);
            tfDate.setText(date.toString());
                     }
            }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());

        } 
            
            }
         });
            
            
            Box InputBox= Box.createVerticalBox();
  
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(cmbCustomerName);
              cmbCustomerName.setPreferredSize(dimCmbBox);
            cmbCustomerName.setMaximumSize(dimCmbBox);
            cmbCustomerName.setMinimumSize(dimCmbBox);
  
  
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(cmbBookNo);
              cmbBookNo.setPreferredSize(dimCmbBox);
            cmbBookNo.setMaximumSize(dimCmbBox);
            cmbBookNo.setMinimumSize(dimCmbBox);
  
            
            
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfBookName);
  
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfAuthorName);
  
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfUnitPrice);
            
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(cmbNoOfBooks);
            cmbNoOfBooks.setPreferredSize(dimCmbBox);
            cmbNoOfBooks.setMaximumSize(dimCmbBox);
            cmbNoOfBooks.setMinimumSize(dimCmbBox);
            
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfTotalPrice);
            
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfTax);
            
            InputBox.add(Box.createVerticalStrut(10));
            InputBox.add(tfDate);
                
            InputBox.setBounds(200, 80, 150, 400);
            this.add(InputBox);
  
                btnSave=new JButton("SAVE");
            btnSave.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
             // this rs is used for updating the stock column
              ResultSet rs=null;

             try{
              String sCustomerName=cmbCustomerName.getSelectedItem().toString();
               if(cmbCustomerName.getSelectedItem().toString().equals("Select a Customer")){
                            throw new IllegalArgumentException("Plese Enter a valid Customer Name");
                        }
              String sBookNo=cmbBookNo.getSelectedItem().toString();
              int BookNo=Integer.parseInt(sBookNo);
              
              String sBookName=tfBookName.getText();
              String sAuthorName=tfAuthorName.getText();
                
              String  sunitPrice=tfUnitPrice.getText();
                 double unitPrice=Double.parseDouble(sunitPrice);
              
              String sNoOfBook=cmbNoOfBooks.getSelectedItem().toString();
                int qty=Integer.parseInt(sNoOfBook);
                
                
                String  sTotalPrice=tfTotalPrice.getText();
                 double TotalPrice=Double.parseDouble(sTotalPrice);
               
                String  sTax=tfTax.getText();
                 double Tax=Double.parseDouble(sTax);
                    
                String sDate = tfDate.getText();
                java.time.LocalDate  lDate= SalesDao.getADate(sDate);
               
               recipt="\n+-----------Recipt--------------------+\nCustomer Name :"+sCustomerName+"\n  Book no   :"+BookNo+"\n Book Nmae   :"+sBookName+"\n Author Name  : "+sAuthorName+"\n price  :"+unitPrice+"\n tax   :"+Tax;
               recipt+="\n Quantity :"+qty;
               recipt+="\n Total price :"+sTotalPrice;
               recipt+="\n Date of sale :"+lDate;
               recipt+="\n+---------------------------------------+";
               
               // checking  condition for valid sales transaction\
               /**
                * 1 if stock is available then save the sale transaction
                * 2 if stock is updated then  save the sale transaction
                * 3. if stock is save transaction is completed then only update the stock
                */
               
                  rs=BookDao.validate(BookNo, rs);
                        rs.next();
                        int prevStock=rs.getInt("stock");
                        int updatedStock=prevStock - qty;
                        
                        //System.out.print(updatedStock);
                        //rs.setInt();//ps to be Used
                        int status=0;
    
                        if(updatedStock >= 0){
                            status=BookDao.updateStock(BookNo, updatedStock);
                        }else{
                         JOptionPane.showMessageDialog(ManageSaleForm.this, "Out of Stock ", "error", JOptionPane.ERROR_MESSAGE);
                            throw new Exception("Cannot perform the sale Operation");
                        }
                        if(status >0){
                            JOptionPane.showMessageDialog(ManageSaleForm.this, " Stock Updated", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else{
                            throw new Exception("Cannot perform the sale Operation");
                        }

                
               
               
                int i=SalesDao.save(sCustomerName,BookNo,sBookName,sAuthorName,unitPrice,qty,TotalPrice,Tax,lDate);
                  if(i>0){
                            JOptionPane.showMessageDialog(ManageSaleForm.this, " transaction is Succesfully Recorded", "Operation Succesful", JOptionPane.INFORMATION_MESSAGE);
                              taRecipt.setText(recipt);
    
                  
                  }
                else{
                    JOptionPane.showMessageDialog(ManageSaleForm.this, "Unable to Record transaction", "Error", JOptionPane.ERROR_MESSAGE);
                    BookDao.updateStock(BookNo, prevStock);

                  
                  }
          }catch(NumberFormatException ex){
            JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
         } 
          catch(DateTimeParseException ex){
             JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
         } 
          catch(IllegalArgumentException ex){
             JOptionPane.showMessageDialog(null, "Invalid Input"+ex.getMessage());
         }  catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed to conect to Database" + ex.getMessage(), "Error Connection", JOptionPane.WARNING_MESSAGE);
        }
           finally{
                  try {
                      rs.close();
                  } catch (SQLException ex) {
                    System.out.print(ex);
                  }
               DB.closeConnection();
           }
        }
    });

                          btnExit=new JButton("Exit");;
            btnExit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
               setVisible(false);
                dispose();

              if(staffLoginSuccsess.frame==null)  
                    staffLoginSuccsess.main(new String[]{});
                }
                  });
            
            
            
            btnPrintRecipt=new JButton("Print Recipt");;
            btnPrintRecipt.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              
              try {
                  
            taRecipt.print();
              } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(ManageSaleForm.this, "Unable to Print ", " Printing Error", JOptionPane.ERROR_MESSAGE);

              }
              
          recipt="";    // at end for next print job
          taRecipt.setText("");
          }
            });
                    
              btnRefresh = new javax.swing.JButton();
        btnRefresh.setText("Refresh");
        btnRefresh.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == btnRefresh) {
                    dispose();
                    frame = new ManageSaleForm();
                }

            }
        });

            
                Box btnBox = Box.createHorizontalBox();
		btnBox.add(Box.createHorizontalStrut(10));
            		btnBox.add(btnSave);
		btnBox.add(Box.createHorizontalStrut(10));
            		btnBox.add(btnExit);
	
		btnBox.add(Box.createHorizontalStrut(10));
            		btnBox.add(btnRefresh);
	
//                btnBox.add(Box.createHorizontalStrut(10));
//                btnBox.add(btnPrintRecipt);

            btnBox.setBounds(80, 500,400, 60);
            this.add(btnBox);

           btnPrintRecipt.setBounds(420, 400, 150, 30);
           this.add(btnPrintRecipt);
           
//       btnRefresh.setBounds(200+ 100+ 20,520, 100, 30);
//        this.add(btnRefresh); 

           
           
       this.setTitle("Manage Sales Details");
      this.setLayout(null);
      this.setVisible(true);

    }

    
    
   
    
}
