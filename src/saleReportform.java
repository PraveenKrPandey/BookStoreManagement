
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;


public class saleReportform extends JFrame{
        
        static   saleReportform frame;
        JLabel lTitle; 
        JLabel lDate; 
        JLabel lMonth; 
        JLabel lYear; 
        JLabel lReportFor; 
        JComboBox cmbDay;
        JComboBox cmbMonth;
        JComboBox cmbYear;
        JButton btnReport;
        JButton btnExit;
        
        JRadioButton rbDaily, rbMonthly, rbYearly;
        JPanel thePanel = new JPanel();

        public static void main(String[] string) {
    EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new saleReportform();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});


        }

    public saleReportform() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setBounds(100, 100, 700, 600);

        Color color=new java.awt.Color(176,196,222);
        this.getContentPane().setBackground(color);

        this.setTitle("Sale Report");
     
     lTitle = new javax.swing.JLabel();
     lTitle.setFont(new java.awt.Font("Tahoma",Font.BOLD, 24)); 
     lTitle.setForeground(new java.awt.Color(204,0,102));
     lTitle.setText("Sales Report");
     lTitle.setBounds(100, 10, 300, 60);
     this.add(lTitle);
     
     Dimension dimPref,dimMax,dimMin,dimOwn,dimCmbBox;
     dimOwn= new Dimension(150,50);     //finally able to arrange at this dim
     dimCmbBox= new Dimension(150, 35);    

        lDate = new javax.swing.JLabel();
        lDate.setText("Day");
        lDate.setPreferredSize(dimOwn);
        lDate.setMaximumSize(dimOwn);
        lDate.setMinimumSize(dimOwn);;
       
        lMonth = new javax.swing.JLabel();
        lMonth.setText("Month");
        lMonth.setPreferredSize(dimOwn);
        lMonth.setMaximumSize(dimOwn);
        lMonth.setMinimumSize(dimOwn);;
       
        lYear = new javax.swing.JLabel();
        lYear.setText("Year");
        lYear.setPreferredSize(dimOwn);
        lYear.setMaximumSize(dimOwn);
        lYear.setMinimumSize(dimOwn);
       
//      adding to the lable Box1 
                Box lableBox  = Box.createHorizontalBox();
    

            lableBox.add(Box.createHorizontalStrut(10));
            lableBox.add(lDate);

            lableBox.add(Box.createHorizontalStrut(10));
            lableBox.add(lMonth);
            lableBox.add(Box.createHorizontalStrut(10));
            lableBox.add(lYear);

             lableBox.setBounds(50, 90, 450, 50);
             this.add(lableBox);
             
             
                    String[] sDay = {"Select a day"};
        cmbDay = new JComboBox(sDay);
        cmbDay.setEnabled(false);
        SalesDao.fillDay(cmbDay);
         
        String[] sMonth = {"Select a Month"};
        cmbMonth = new JComboBox(sMonth);
        cmbMonth.setEnabled(false);
        SalesDao.fillMonth(cmbMonth);
         
        String[] sYear = {"Select a Year"};
        cmbYear = new JComboBox(sYear);
        cmbYear.setEnabled(false);
        SalesDao.fillYear(cmbYear);
                
        
        Box cmbBox = Box.createHorizontalBox();
		cmbBox.add(Box.createHorizontalStrut(10));
            		cmbBox.add(cmbDay);
		cmbBox.add(Box.createHorizontalStrut(10));
            		cmbBox.add(cmbMonth);
	
                cmbBox.add(Box.createHorizontalStrut(10));
                cmbBox.add(cmbYear);

           cmbBox.setBounds(56, 300,400, 40);
           this.add(cmbBox);



             
        rbDaily= new JRadioButton("Daily");
        rbDaily.setPreferredSize(dimOwn);
        rbDaily.setMaximumSize(dimOwn);;
        rbDaily.setMinimumSize(dimOwn);
        rbDaily.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                    cmbDay.setEnabled(true);
                         cmbMonth.setEnabled(true);
                         cmbYear.setEnabled(true);

        }
    });

        rbMonthly= new JRadioButton("Monthly");
        rbMonthly.setPreferredSize(dimOwn);
        rbMonthly.setMaximumSize(dimOwn);;
        rbMonthly.setMinimumSize(dimOwn);
        rbMonthly.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                         cmbDay.setEnabled(false);
                         cmbMonth.setEnabled(true);
                         cmbYear.setEnabled(true);

        }
    });
        
        
        rbYearly= new JRadioButton("Yearly");
        rbYearly.setPreferredSize(dimOwn);
        rbYearly.setMaximumSize(dimOwn);;
        rbYearly.setMinimumSize(dimOwn);
        rbYearly.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
                         cmbDay.setEnabled(false);
                         cmbMonth.setEnabled(false);
                         cmbYear.setEnabled(true);

        }
    });
    
        ButtonGroup bgrpReportType = new ButtonGroup();
    bgrpReportType.add(rbDaily);
    bgrpReportType.add(rbMonthly);
    bgrpReportType.add(rbYearly);
    

        Box rbBox  = Box.createHorizontalBox();
    

            rbBox.add(Box.createHorizontalStrut(10));
            rbBox.add(rbDaily);

            rbBox.add(Box.createHorizontalStrut(10));
            rbBox.add(rbMonthly);
            rbBox.add(Box.createHorizontalStrut(10));
            rbBox.add(rbYearly);

             rbBox.setBounds(41, 150, 450, 50);
             this.add(rbBox);
        
        
        btnReport = new JButton();
        btnReport.setText("Sales report");
        	btnReport.addActionListener(new ActionListener() {;
			public void actionPerformed(ActionEvent e) {
                            if(e.getSource()==btnReport){
                                JTable table=null;

                                String sYear=cmbYear.getSelectedItem().toString();
                                String sMonth=cmbMonth.getSelectedItem().toString();
                                String sDay=cmbDay.getSelectedItem().toString();
                                
                                
                                
                                if(rbDaily.isSelected()){
                                    String sqlStmt="select * from sales where DATE(sellingdate)=\""+sYear+"-"+sMonth+"-"+sDay+"\"";
                                  //  System.out.println(sqlStmt);
                                    table=SalesDao.getTableReport(table,sqlStmt);
                                }
                                
                                if(rbMonthly.isSelected()){
                                    String sqlStmt="select * from sales where YEAR(sellingdate)="+sYear+" AND MONTH(sellingdate)= "+sMonth;
                                    table=SalesDao.getTableReport(table,sqlStmt);
                                }
                                
                                if(rbYearly.isSelected()){
                                    String sqlStmt="select * from sales where YEAR(sellingdate)="+sYear ;
                                    table=SalesDao.getTableReport(table,sqlStmt);
                                }
                                
                                    table.setFont(new Font("Serif", Font.PLAIN, 15));
                                    table.setRowHeight(table.getRowHeight()+2);
                                    table.setAutoCreateRowSorter(true);



                                    JScrollPane scrollPane = new JScrollPane(table);
                                    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
                                    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                                    JFrame f=new JFrame("Sales Report");
                                    f.add(scrollPane, BorderLayout.CENTER);
                                    f.setSize(600,700);
                                    f.setVisible(true);
                                    f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

                Box btnBox = Box.createHorizontalBox();
		btnBox.add(Box.createHorizontalStrut(100));
            		btnBox.add(btnReport);
		btnBox.add(Box.createHorizontalStrut(20));
            		btnBox.add(btnExit);
	                
            btnBox.setBounds(80, 390,400, 60);
            this.add(btnBox);

                
    this.setTitle("Sales Report");
    this.setLayout(null);
    this.setVisible(true);

    }
    
}       

