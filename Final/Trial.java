import java.io.*;
import java.lang.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import java.text.*;
public class Trial
{
    //CONNECT
    Connection con;
    Statement st;
    ResultSet rs;
    
    //LOGIN PAGE
    JFrame loginframe=new JFrame("USER LOGIN");
    JLabel l=new JLabel("LOGIN ID:");
    JLabel l1=new JLabel("PASSWORD:");
    JTextField t=new JTextField(20);
    JPasswordField t1=new JPasswordField(20);
    JButton b=new JButton("LOGIN");
    String user,pass;
    String USER_TYPE[]={"ADMIN","GENERAL USER","LIBRARIAN"};
    
    //LIBRARIAN CHOICES
    JFrame LibMenuCHOICES;
    
    //LIBRARIAN MASTER UPDATE
    JFrame LibMenuMASTERUPDATE;
    
    //READER MASTER
    JFrame LibMenuRM;
    
    //NEW READER
    JFrame murmnr;
    JTextField murmnrfn1,murmnrln1,murmnrd1,murmnrid1;
    String STANDARD[]={"Nursery","Jr.Kg","Sr.Kg","1","2","3","4","5","6","7","8","9","10","11","12"};
    String ROLLNO[]=new String[100];
    JComboBox murmnrs1,murmnrrn1;
    ResultSet rsmurmnr;
    
    //MODIFY READER
    JTextField murmmrentryidtext;
    JTextField LibMenuMURMMRfn1,LibMenuMURMMRln1,LibMenuMURMMRd1,LibMenuMURMMRid1;
    JComboBox LibMenuMURMMRs1,LibMenuMURMMRrn1;
    ResultSet rsLibMenumurmmr;
    JFrame murmmr;
    
    //DELETE READER
    JTextField murmdrentryidtext;
    JLabel LibMenuMURMDRfn1,LibMenuMURMDRln1,LibMenuMURMDRs1,LibMenuMURMDRd1,LibMenuMURMDRrn1,LibMenuMURMDRid1;
    ResultSet rsLibMenumurmdr;
    JFrame murmdr;
    JTextField drremark;
    
    //BOOK MASTER
    JFrame LibMenubm;
    
    //NEW BOOK
    JFrame mubmnb;
    JComboBox mubmnbcu1,mubmnbgn1;
    String CURRENCY[]={"INR","EUR","USD"},GENRE[]={"Autobiography","Fiction","Fantasy","Suspense","Romance","Non-fiction","Mythology","Philosophy","Pshycology","Reference Books","Dictionaries and Thesaurus","Others","Combination of genres"};
    String idmubmnb,bnamemubmnb,authormubmnb,currencymubmnb;
    int pricemubmnb;
    JTextField mubmnbbn1,mubmnbau1,mubmnbpr1,mubmnbid1,genrestrial;
    ResultSet rsmubmnb;
    String genremubmnb;
    int temporary;
    
    //MODIFY BOOK
    JFrame mubmmb;
    JComboBox mubmmbcu1,mubmmbgn1;
    JTextField mubmmbentryidtext;
    String mubmmbID,mubmmbBN,mubmmbAU,mubmmbCU,mubmmbGN;
    int mubmmbPR;
    JTextField mubmmbbn1,mubmmbau1,mubmmbpr1,mubmmbid1;
    ResultSet rsmubmmb;
        
    //DELETE BOOK
    JTextField mubmdbentryidtext;
    JLabel mubmdbbn1,mubmdbau1,mubmdbpr1,mubmdbcu1,mubmdbgn1,mubmdbid1;
    ResultSet rsmubmdb;
    JFrame mubmdb;
    JTextField dbremarktext;
    
    //TRANSACTION MENU
    JFrame LibMenuTM;
    String today,duedate;
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
    
    //BOOK ISSUE
    JFrame tmbi;
    JTextField tmbirid1,tmbibid1,tmbiid1,tmbiprd1;
    ResultSet rstmbitrnmenu,rstmbibkmenu,rstmbirdmenu;
    String readeridtmbi,bookidtmbi;
    int ReaderIdTrnMenu;
    
    //BOOK RETURN
    ResultSet rs_book_return_trnmenu;
    ResultSet rs_Book_return_rtnmenu;
    JFrame b_return_frame;
    JTextField b_actual_return_date_data;
    String r_id="",b_id="",b_issue_date="",b_planned_return_date="",u_login="";
    String sql_book_return_trnmenu;
    
    //LIBRARIAN MAINTENANCE
    JFrame LibMenuMM;
    ResultSet rslmmm;
    JPasswordField pwoldlmmm,pwnewlmmm,pwconfirmlmmm;
    String strpasslmmm;
    
    //REPORT MENU
    JFrame report_menu_choices,report_menu_book_search,report_menu_reader_search;
    JTextField book_name_tf,author_tf;
    JComboBox genre_tf;
    JTextField first_name_tf,last_name_tf,standard_tf;
    ResultSet rs_bs,rs_rs,rs_dr,rs_lr;
    
    //ADMIN MENU
    ResultSet rsnu;
    ResultSet rsmu;
    ResultSet rsru;
    ResultSet rscp;
    String amuuserentry="";
    JTextField linu,unnu;
    JTextField limu,unmu;
    JPasswordField pwnu;
    JPasswordField pwoldcp,pwnewcp,pwconfirmcp;
    JComboBox cnu;
    JComboBox cmu;
    JFrame AC,AdminMenuNU,AdminMenuMU,AdminMenuRU,AdminMenuCP; 
    String strpwcp;
    JTextField RMru1;
    
    public Trial()
    {
        initialize();
        connect();
        frame();
    }
    
    public void initialize()
    {
        for(int i=1;i<=100;i++)
        {
            ROLLNO[(i-1)]=String.valueOf(i);
        }
    }

    public void connect()//CONNECTION WITH THE DATABASE
    {
        try
        {
            String driver="sun.jdbc.odbc.JdbcOdbcDriver";
            Class.forName(driver);
            String db="jdbc:odbc:Final";
            con=DriverManager.getConnection(db);
            st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void frame()//LOGIN PAGE DESIGNING
    {
        loginframe.setSize(370,300);
        loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginframe.setVisible(true);
        JPanel p=new JPanel(new GridBagLayout());
        t.setText("");
        t1.setText("");
        user="";
        pass="";
        
        GridBagConstraints gbcLoginPage=new GridBagConstraints();
        
        gbcLoginPage.insets=new Insets(10,10,10,10);
        gbcLoginPage.gridx=0;
        gbcLoginPage.gridy=0;
        p.add(l,gbcLoginPage);
        
        gbcLoginPage.insets=new Insets(10,10,10,10);
        gbcLoginPage.gridx=1;
        gbcLoginPage.gridy=0;
        p.add(t,gbcLoginPage);
        
        gbcLoginPage.insets=new Insets(10,10,10,10);
        gbcLoginPage.gridx=0;
        gbcLoginPage.gridy=1;
        p.add(l1,gbcLoginPage);
        
        gbcLoginPage.insets=new Insets(10,10,10,10);
        gbcLoginPage.gridx=1;
        gbcLoginPage.gridy=1;
        p.add(t1,gbcLoginPage);
        
        gbcLoginPage.insets=new Insets(10,10,10,10);
        gbcLoginPage.gridx=1;
        gbcLoginPage.gridy=2;
        p.add(b,gbcLoginPage);
        
        loginframe.add(p);
        b.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    btnAction();
                }
            });
    }

    public void btnAction()//BUTTON ACTION FOR "LOGIN"
    {
        user=t.getText().trim();
        pass=t1.getText().trim();
        try
        {
            String sql="select * from USER_MST where U_LOGIN='"+user+"'and U_PASSWORD='"+pass+"'";
            rs=st.executeQuery(sql);
            String type="";
            if(rs.next())
            {
                int temp=0;
                type=rs.getString("U_CATEGORY");
                for(int trial=0;trial<=2;trial++)
                {
                    temp=trial;
                    if(type.equals(USER_TYPE[trial]))
                    {
                        trial=10;
                    }
                }
                if(temp==0)
                {
                    rs.close();
                    loginframe.setVisible(false);
                    JOptionPane.showMessageDialog(null,"You have opened through Admin Login");
                    AdminChoice();
                }
                else if(temp==1)
                {
                    rs.close();
                    loginframe.setVisible(false);
                    JOptionPane.showMessageDialog(null,"You have opened through Report User");
                    ReportMenu();
                }
                else
                {
                    rs.close();
                    loginframe.setVisible(false);
                    JOptionPane.showMessageDialog(null,"You have opened through Librarian Login");
                    LibMenu();
                }
            }
            else
            {
                int selectedOption=JOptionPane.showConfirmDialog(null,"It is a wrong LOGIN or PASSWORD. Do you want to re enter?","INVALID ENTRY",JOptionPane.YES_NO_OPTION);
                if(selectedOption==JOptionPane.NO_OPTION)
                    System.exit(0);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception");          
        }
    }

    public void LibMenu()//DESIGNING OF PAGE FOR LIBRARIAN'S CHOICES
    {
        LibMenuCHOICES=new JFrame("CHOICES");
        LibMenuCHOICES.setVisible(true);
        LibMenuCHOICES.setSize(700,500);
        LibMenuCHOICES.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel1=new JPanel(new GridBagLayout());
        GridBagConstraints gbcLibMenuChoices=new GridBagConstraints();
        JButton b1,b2,b3,b4,b5;
        b1=new JButton("    Master Update    ");
        b2=new JButton("Maintenance Menu");
        b3=new JButton(" Transaction Menu ");
        b4=new JButton("      Report Menu      ");
        b5=new JButton("          Sign out          ");
        b1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuCHOICES.setVisible(false);
                    LibMenumu();
                }          
            });
        b2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuCHOICES.setVisible(false);
                    JOptionPane.showMessageDialog(null,"YOU HAVE THE OPTION TO CHANGE ONLY YOUR OWN PASSWORD");
                    LibMenumm();
                }          
            });
        b3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuCHOICES.setVisible(false);
                    date();
                }          
            });
        b4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuCHOICES.setVisible(false);
                    LibMenuReportMenu();
                }          
            });
        b5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuCHOICES.setVisible(false);
                    main();
                }
            });
        gbcLibMenuChoices.insets=new Insets(10,10,10,10);
        gbcLibMenuChoices.gridx=0;
        gbcLibMenuChoices.gridy=1;
        panel1.add(b1,gbcLibMenuChoices);
        gbcLibMenuChoices.insets=new Insets(10,10,10,10);
        gbcLibMenuChoices.gridx=0;
        gbcLibMenuChoices.gridy=2;
        panel1.add(b2,gbcLibMenuChoices);
        gbcLibMenuChoices.insets=new Insets(10,10,10,10);
        gbcLibMenuChoices.gridx=0;
        gbcLibMenuChoices.gridy=3;
        panel1.add(b3,gbcLibMenuChoices);
        gbcLibMenuChoices.insets=new Insets(10,10,10,10);
        gbcLibMenuChoices.gridx=0;
        gbcLibMenuChoices.gridy=4;
        panel1.add(b4,gbcLibMenuChoices);
        gbcLibMenuChoices.insets=new Insets(10,10,10,10);
        gbcLibMenuChoices.gridx=0;
        gbcLibMenuChoices.gridy=5;
        panel1.add(b5,gbcLibMenuChoices);
        LibMenuCHOICES.add(panel1);
    }

    public void LibMenumu()//DESIGNING OF PAGE FOR MASTER UPDATE OPTIONS
    {
        LibMenuMASTERUPDATE=new JFrame("Master Update");
        LibMenuMASTERUPDATE.setVisible(true);
        LibMenuMASTERUPDATE.setSize(700,500);
        LibMenuMASTERUPDATE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panelmu=new JPanel(new GridBagLayout());
        JButton mu1=new JButton("Reader Master");
        JButton mu2=new JButton("  Book Master  ");
        JButton mu3=new JButton("         Back         ");
        JButton mu4=new JButton("      Sign out      ");
        GridBagConstraints gbcLibMenuMasterUpdate=new GridBagConstraints();
        mu1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent aemu)
                {
                    LibMenuMASTERUPDATE.setVisible(false);
                    LibMenuReaderMaster();
                }
            });
        mu2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent aemu)
                {
                    LibMenuMASTERUPDATE.setVisible(false);                        
                    LibMenuBookMaster();
                }
            });
        mu3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuMASTERUPDATE.setVisible(false);
                    LibMenu();
                }          
            });

        mu4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuMASTERUPDATE.setVisible(false);
                    main();
                }          
            });

        gbcLibMenuMasterUpdate.insets=new Insets(10,10,10,10);
        gbcLibMenuMasterUpdate.gridx=0;
        gbcLibMenuMasterUpdate.gridy=0;
        panelmu.add(mu1,gbcLibMenuMasterUpdate);
        gbcLibMenuMasterUpdate.insets=new Insets(10,10,10,10);
        gbcLibMenuMasterUpdate.gridx=0;
        gbcLibMenuMasterUpdate.gridy=1;
        panelmu.add(mu2,gbcLibMenuMasterUpdate);
        gbcLibMenuMasterUpdate.insets=new Insets(10,10,10,10);
        gbcLibMenuMasterUpdate.gridx=0;
        gbcLibMenuMasterUpdate.gridy=2;
        panelmu.add(mu3,gbcLibMenuMasterUpdate);
        gbcLibMenuMasterUpdate.insets=new Insets(10,10,10,10);
        gbcLibMenuMasterUpdate.gridx=0;
        gbcLibMenuMasterUpdate.gridy=3;
        panelmu.add(mu4,gbcLibMenuMasterUpdate);
        LibMenuMASTERUPDATE.add(panelmu);
    }

    public void LibMenuReaderMaster()//DESIGNING OF PAGE FOR READER MASTER OPTIONS
    {
        LibMenuRM=new JFrame("Reader Master");
        LibMenuRM.setVisible(true);
        LibMenuRM.setSize(700,500);
        LibMenuRM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelrm=new JPanel(new GridBagLayout());

        JButton rm1=new JButton(" New  Reader ");
        JButton rm2=new JButton("Modify Reader");
        JButton rm3=new JButton("Delete Reader");
        JButton rm4=new JButton("         Back        ");
        JButton rm5=new JButton("     Sign  out     ");

        GridBagConstraints gbcLibMenumurm=new GridBagConstraints();

        rm1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent aemu)
                {
                    LibMenuRM.setVisible(false);
                    LibMenumurmnr();
                }
            });
        gbcLibMenumurm.insets=new Insets(10,10,10,10);
        gbcLibMenumurm.gridx=0;
        gbcLibMenumurm.gridy=0;
        panelrm.add(rm1,gbcLibMenumurm);

        rm2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuRM.setVisible(false);
                    LibMenumurmmr();
                }          
            });
        gbcLibMenumurm.insets=new Insets(10,10,10,10);
        gbcLibMenumurm.gridx=0;
        gbcLibMenumurm.gridy=1;
        panelrm.add(rm2,gbcLibMenumurm);

        rm3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuRM.setVisible(false);
                    LibMenumurmdr();
                }          
            });
        gbcLibMenumurm.insets=new Insets(10,10,10,10);
        gbcLibMenumurm.gridx=0;
        gbcLibMenumurm.gridy=2;
        panelrm.add(rm3,gbcLibMenumurm);

        rm4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuRM.setVisible(false);
                    LibMenumu();
                }          
            });
        gbcLibMenumurm.insets=new Insets(10,10,10,10);
        gbcLibMenumurm.gridx=0;
        gbcLibMenumurm.gridy=3;
        panelrm.add(rm4,gbcLibMenumurm);

        rm5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuRM.setVisible(false);
                    main();
                }          
            });
        gbcLibMenumurm.insets=new Insets(10,10,10,10);
        gbcLibMenumurm.gridx=0;
        gbcLibMenumurm.gridy=4;
        panelrm.add(rm5,gbcLibMenumurm);

        LibMenuRM.add(panelrm);
    }
    
    public void LibMenumurmnr()//DESIGNING OF PAGE FOR ADDING A READER (TO TAKE THE INPUTS)
    {
        murmnr=new JFrame("New Reader");
        murmnr.setVisible(true);
        murmnr.setSize(700,500);
        murmnr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel murmnrp=new JPanel(new GridBagLayout());
        JLabel murmnrfn=new JLabel("First Name");
        JLabel murmnrln=new JLabel("Last Name");
        JLabel murmnrs=new JLabel("Standard");
        JLabel murmnrd=new JLabel("Division");
        JLabel murmnrrn=new JLabel("Roll No");
        JLabel murmnrid=new JLabel("ID");
        murmnrfn1=new JTextField(20);
        murmnrln1=new JTextField(20);
        murmnrs1=new JComboBox(STANDARD);
        murmnrd1=new JTextField(10);
        murmnrrn1=new JComboBox(ROLLNO);
        murmnrid1=new JTextField(10);
        JButton murmnradd=new JButton("Add");
        murmnradd.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    btnActionmurmnr();
                }
            });
        JButton murmnrback=new JButton("Back");
        murmnrback.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    murmnr.setVisible(false);
                    LibMenuReaderMaster();
                }          
            });
        JButton murmnrso=new JButton("Sign out");
        murmnrso.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    murmnr.setVisible(false);
                    System.exit(0);
                }          
            });
        GridBagConstraints murmnrgbc=new GridBagConstraints();
        murmnrfn1.setText("");
        murmnrln1.setText("");
        
        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=0;
        murmnrp.add(murmnrfn,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=0;
        murmnrp.add(murmnrfn1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=1;
        murmnrp.add(murmnrln,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=1;
        murmnrp.add(murmnrln1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=2;
        murmnrp.add(murmnrs,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=2;
        murmnrp.add(murmnrs1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=3;
        murmnrp.add(murmnrd,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=3;
        murmnrp.add(murmnrd1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=4;
        murmnrp.add(murmnrrn,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=4;
        murmnrp.add(murmnrrn1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=5;
        murmnrp.add(murmnrid,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=5;
        murmnrp.add(murmnrid1,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=0;
        murmnrgbc.gridy=6;
        murmnrp.add(murmnradd,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=1;
        murmnrgbc.gridy=6;
        murmnrp.add(murmnrback,murmnrgbc);

        murmnrgbc.insets=new Insets(10,10,10,10);
        murmnrgbc.gridx=2;
        murmnrgbc.gridy=6;
        murmnrp.add(murmnrso,murmnrgbc);

        murmnr.add(murmnrp);
        JOptionPane.showMessageDialog(null,"PLEASE ENTER THE GR NO OF THE READER FOR THE ID");
    }

    public void btnActionmurmnr()//BUTTON ACTION FOR "ADD" (ADDING IN THE DATABASE)
    {
        try
        {
            String sqlmurmnr="select * from READER_MST";
            rsmurmnr=st.executeQuery(sqlmurmnr);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while connecting");
        }
        int temporary=1;
        String IDmurmnr=murmnrid1.getText().trim();
        String fnamemurmnr=murmnrfn1.getText().trim();
        String lnamemurmnr=murmnrln1.getText().trim();
        String standardmurmnr=murmnrs1.getSelectedItem().toString();
        String strrollnomurmnr=murmnrrn1.getSelectedItem().toString();
        int rollnomurmnr=Integer.parseInt(strrollnomurmnr);
        String divisionmurmnr=murmnrd1.getText().trim();
        try
        {
            if(IDmurmnr.equals("") || fnamemurmnr.equals("") || lnamemurmnr.equals("") || divisionmurmnr.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                temporary++;
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while blank textfield check");
        }
        try
        {
            while(temporary==1 && rsmurmnr.next())
            {
                String standardtempmurmnr=rsmurmnr.getString("R_STANDARD"),divisiontempmurmnr=rsmurmnr.getString("R_DIVISION"),strrollnotempmurmnr=rsmurmnr.getString("R_ROLLNO"),idtempmurmnr=rsmurmnr.getString("R_ID");
                int rollnotempmurmnr=Integer.parseInt(strrollnotempmurmnr);
                if(standardmurmnr.equals(standardtempmurmnr) && rollnomurmnr==rollnotempmurmnr && divisionmurmnr.equals(divisiontempmurmnr))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another reader in the same standard and division with the same roll no");
                    temporary++;
                }
                if(idtempmurmnr.equals(IDmurmnr))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another reader with the same id");
                    temporary++;
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while checking 1");
        }
        try
        {
            while(temporary==1 && rsmurmnr.previous())
            {
                String standardtempmurmnr=rsmurmnr.getString("R_STANDARD"),divisiontempmurmnr=rsmurmnr.getString("R_DIVISION"),strrollnotempmurmnr=rsmurmnr.getString("R_ROLLNO"),idtempmurmnr=rsmurmnr.getString("R_ID");
                int rollnotempmurmnr=Integer.parseInt(strrollnotempmurmnr);
                if(standardmurmnr.equals(standardtempmurmnr) && rollnomurmnr==rollnotempmurmnr && divisionmurmnr.equals(divisiontempmurmnr))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another reader in the same standard and division with the same roll no");
                    temporary++;
                }
                if(idtempmurmnr.equals(IDmurmnr))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another reader with the same id");
                    temporary++;
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while checking 2");
        }
        try
        {
            if(temporary==1)
            {
                rsmurmnr.moveToInsertRow();
                rsmurmnr.updateString("R_FNAME",fnamemurmnr);
                rsmurmnr.updateString("R_LNAME",lnamemurmnr);
                rsmurmnr.updateString("R_STANDARD",standardmurmnr);
                rsmurmnr.updateString("R_DIVISION",divisionmurmnr);
                rsmurmnr.updateInt("R_ROLLNO",rollnomurmnr);
                rsmurmnr.updateString("R_ID",IDmurmnr);
                rsmurmnr.updateString("R_ACTIVE","Yes");
                rsmurmnr.updateString("RID_COUNT","0");
                rsmurmnr.insertRow();
                rsmurmnr.close();
                st.close();
                st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                JOptionPane.showMessageDialog(null,"Reader Added");
                murmnr.setVisible(false);
                LibMenuReaderMaster();
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while adding");
        }
    }
    
    public void LibMenumurmmr()//TAKING THE RAEDER ID WHICH IS TO BE MODIFIED
    {
        murmmrentryidtext=new JTextField(10);
        JLabel murmmrentryidlabel=new JLabel("Please enter the reader id");
        JPanel murmmrentryidpanel=new JPanel();
        murmmrentryidpanel.add(murmmrentryidlabel);
        murmmrentryidpanel.add(murmmrentryidtext);
        int selectedoptionIDModifyReader=JOptionPane.showConfirmDialog(null,murmmrentryidpanel,"ID",JOptionPane.OK_CANCEL_OPTION);
        if(selectedoptionIDModifyReader==JOptionPane.OK_OPTION)
        {
            btnActionIDmurmmr();
        }
        else
        {
            LibMenuReaderMaster();
        }
    }

    public void btnActionIDmurmmr()//DESIGNING OF PAGE FOR MODIFYING A READER (TO MODIFY THE DATA)
    {
        String ID=murmmrentryidtext.getText().trim();
        int temporaryLibMenuMURMMR=0;
        LibMenuMURMMRfn1=new JTextField(20);
        LibMenuMURMMRln1=new JTextField(20);
        LibMenuMURMMRs1=new JComboBox(STANDARD);
        LibMenuMURMMRd1=new JTextField(10);
        LibMenuMURMMRrn1=new JComboBox(ROLLNO);
        LibMenuMURMMRid1=new JTextField(10);
        try
        {
            String sqlLibMenumurmmr="select * from READER_MST where R_ID='"+ID+"' and R_ACTIVE='Yes'";
            rsLibMenumurmmr=st.executeQuery(sqlLibMenumurmmr);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Before Button");
        }
        try
        {
            if(rsLibMenumurmmr.next())
            {
                LibMenuMURMMRfn1.setText(rsLibMenumurmmr.getString("R_FNAME"));
                LibMenuMURMMRln1.setText(rsLibMenumurmmr.getString("R_LNAME"));
                LibMenuMURMMRd1.setText(rsLibMenumurmmr.getString("R_DIVISION"));
                LibMenuMURMMRid1.setText(rsLibMenumurmmr.getString("R_ID"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"ID DOES NOT EXIST");
                temporaryLibMenuMURMMR++;
                LibMenuReaderMaster();
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Again");
        }
        if(temporaryLibMenuMURMMR==0)
        {
            murmmr=new JFrame("Modify Reader");
            murmmr.setVisible(true);
            murmmr.setSize(700,500);
            murmmr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel murmmrp=new JPanel(new GridBagLayout());
            JLabel murmmrfn=new JLabel("First Name");
            JLabel murmmrln=new JLabel("Last Name");
            JLabel murmmrs=new JLabel("Standard");
            JLabel murmmrd=new JLabel("Division");
            JLabel murmmrrn=new JLabel("Roll No");
            JLabel murmmrid=new JLabel("ID");
            JButton murmmrmodify=new JButton("Modify");
            murmmrmodify.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionReaderModify();
                    }
                });
                JButton murmmrback=new JButton("Back");
            murmmrback.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        murmmr.setVisible(false);
                        LibMenuReaderMaster();
                    }          
                });
                JButton murmmrso=new JButton("Sign out");
                murmmrso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        murmmr.setVisible(false);
                        main();
                    }          
                });
            GridBagConstraints gbcmurmmr=new GridBagConstraints();
            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=0;
            murmmrp.add(murmmrfn,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=0;
            murmmrp.add(LibMenuMURMMRfn1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=1;
            murmmrp.add(murmmrln,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=1;
            murmmrp.add(LibMenuMURMMRln1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=2;
            murmmrp.add(murmmrs,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=2;
            murmmrp.add(LibMenuMURMMRs1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=3;
            murmmrp.add(murmmrd,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=3;
            murmmrp.add(LibMenuMURMMRd1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=4;
            murmmrp.add(murmmrrn,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=4;
            murmmrp.add(LibMenuMURMMRrn1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=5;
            murmmrp.add(murmmrid,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=5;
            murmmrp.add(LibMenuMURMMRid1,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=0;
            gbcmurmmr.gridy=6;
            murmmrp.add(murmmrmodify,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=1;
            gbcmurmmr.gridy=6;
            murmmrp.add(murmmrback,gbcmurmmr);

            gbcmurmmr.insets=new Insets(10,10,10,10);
            gbcmurmmr.gridx=2;
            gbcmurmmr.gridy=6;
            murmmrp.add(murmmrso,gbcmurmmr);

            murmmr.add(murmmrp);
            JOptionPane.showMessageDialog(null,"PLEASE NOTE THAT THE STANDARD OF THE READER IS BEING SHOWN NURSERY AND ROLL NO 1. CHANGE IT IF REQUIRED");
        }
    }

    public void btnActionReaderModify()//BUTTON ACTION FOR "MODIFY" (MODIFYING IN THE DATABASE)
    {
        String murmmrFN=LibMenuMURMMRfn1.getText().trim();
        String murmmrLN=LibMenuMURMMRln1.getText().trim();
        String murmmrS=LibMenuMURMMRs1.getSelectedItem().toString();
        String murmmrD=LibMenuMURMMRd1.getText().trim();
        String strmurmmrRN=LibMenuMURMMRrn1.getSelectedItem().toString();
        int murmmrRN=Integer.parseInt(strmurmmrRN);
        String murmmrID=LibMenuMURMMRid1.getText().trim();
        if(murmmrFN.equals("") || murmmrLN.equals("") || murmmrD.equals("") || murmmrID.equals(""))
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT ONE OR MORE TEXTFIELDS HAVE BEEN LEFT WITHOUT DATA");
        }
        else
        {
            try
            {
                rsLibMenumurmmr.updateString("R_FNAME",murmmrFN);
                rsLibMenumurmmr.updateString("R_LNAME",murmmrLN);
                rsLibMenumurmmr.updateString("R_STANDARD",murmmrS);
                rsLibMenumurmmr.updateString("R_DIVISION",murmmrD);
                rsLibMenumurmmr.updateInt("R_ROLLNO",murmmrRN);   
                rsLibMenumurmmr.updateString("R_ID",murmmrID);
                rsLibMenumurmmr.updateRow();
                JOptionPane.showMessageDialog(null,"Record Updated");
                murmmr.setVisible(false);
                LibMenuReaderMaster();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"SORRY BUT THE READER ID MATCHES THAT OF AN EXISTING READER");
            }
        }
    }
    
    public void LibMenumurmdr()//TAKING THE RAEDER ID WHICH IS TO BE DELETED
    {
        murmdrentryidtext=new JTextField(10);
        JLabel murmdrentryidlabel=new JLabel("Please enter the reader id");
        JPanel murmdrentryidpanel=new JPanel();
        murmdrentryidpanel.add(murmdrentryidlabel);
        murmdrentryidpanel.add(murmdrentryidtext);
        int selectedoptionIDDeleteReader=JOptionPane.showConfirmDialog(null,murmdrentryidpanel,"ID",JOptionPane.OK_CANCEL_OPTION);
        if(selectedoptionIDDeleteReader==JOptionPane.OK_OPTION)
        {
            btnActionIDmurmdr();
        }
        else
        {
            LibMenuReaderMaster();
        }
    }

    public void btnActionIDmurmdr()//DESIGNING OF PAGE FOR MODIFYING A READER (TO DELETE THE DATA)
    {
        String ID=murmdrentryidtext.getText().trim();
        int temporaryLibMenuMURMDR=0;
        LibMenuMURMDRfn1=new JLabel();
        LibMenuMURMDRln1=new JLabel();
        LibMenuMURMDRs1=new JLabel();
        LibMenuMURMDRd1=new JLabel();
        LibMenuMURMDRrn1=new JLabel();
        LibMenuMURMDRid1=new JLabel();
        String strLibMenuMURMDRidcount1;
        int temporary=0;
        try
        {
            String sqlLibMenumurmdr="select * from READER_MST where R_ID='"+ID+"' and R_ACTIVE='Yes'";
            rsLibMenumurmdr=st.executeQuery(sqlLibMenumurmdr);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Before Button");
        }
        try
        {
            if(rsLibMenumurmdr.next())
            {
                strLibMenuMURMDRidcount1=rsLibMenumurmdr.getString("RID_COUNT");
                if(strLibMenuMURMDRidcount1.equals("0"))
                {
                    
                }
                else
                {
                    temporary++;
                    JOptionPane.showMessageDialog(null,"SORRY BUT THE USER HAS A BOOK/S ISSUED. SO, HE/SHE CANNOT BE DELETED");
                    LibMenuReaderMaster();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(temporary==0)
        {
            try
            {
                if(rsLibMenumurmdr.first())
                {
                    LibMenuMURMDRfn1.setText(rsLibMenumurmdr.getString("R_FNAME"));
                    LibMenuMURMDRln1.setText(rsLibMenumurmdr.getString("R_LNAME"));
                    LibMenuMURMDRs1.setText(rsLibMenumurmdr.getString("R_STANDARD"));
                    LibMenuMURMDRd1.setText(rsLibMenumurmdr.getString("R_DIVISION"));
                    LibMenuMURMDRrn1.setText(rsLibMenumurmdr.getString("R_ROLLNO"));
                    LibMenuMURMDRid1.setText(rsLibMenumurmdr.getString("R_ID"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"ID DOES NOT EXIST");
                    temporaryLibMenuMURMDR++;
                    LibMenuReaderMaster();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception Again");
            }
            if(temporaryLibMenuMURMDR==0)
            {
                murmdr=new JFrame("Delete Reader");
                murmdr.setVisible(true);
                murmdr.setSize(700,500);
                murmdr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel murmdrp=new JPanel(new GridBagLayout());
                JLabel murmdrfn=new JLabel("First Name");
                JLabel murmdrln=new JLabel("Last Name");
                JLabel murmdrs=new JLabel("Standard");
                JLabel murmdrd=new JLabel("Division");
                JLabel murmdrrn=new JLabel("Roll No");
                JLabel murmdrid=new JLabel("ID");
                JLabel drremarklabel=new JLabel("Remark");
                drremark=new JTextField(20);
                JButton murmdrdelete=new JButton("Delete");
                murmdrdelete.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent e)
                        {
                            btnActionReaderDelete();
                        }
                    });
                    JButton murmdrback=new JButton("Back");
                murmdrback.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae)
                        {
                            murmdr.setVisible(false);
                            LibMenuReaderMaster();
                        }          
                    });
                    JButton murmdrso=new JButton("Sign out");
                    murmdrso.addActionListener(new ActionListener(){
                        public void actionPerformed(ActionEvent ae)
                        {
                            murmdr.setVisible(false);
                            main();
                        }          
                    });
                GridBagConstraints gbcmurmdr=new GridBagConstraints();
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=0;
                murmdrp.add(murmdrfn,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=0;
                murmdrp.add(LibMenuMURMDRfn1,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=1;
                murmdrp.add(murmdrln,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=1;
                murmdrp.add(LibMenuMURMDRln1,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=2;
                murmdrp.add(murmdrs,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=2;
                murmdrp.add(LibMenuMURMDRs1,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=3;
                murmdrp.add(murmdrd,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=3;
                murmdrp.add(LibMenuMURMDRd1,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=4;
                murmdrp.add(murmdrrn,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=4;
                murmdrp.add(LibMenuMURMDRrn1,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=5;
                murmdrp.add(murmdrid,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=5;
                murmdrp.add(LibMenuMURMDRid1,gbcmurmdr);
                
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=6;
                murmdrp.add(drremarklabel,gbcmurmdr);
                
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=6;
                murmdrp.add(drremark,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=0;
                gbcmurmdr.gridy=7;
                murmdrp.add(murmdrdelete,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=1;
                gbcmurmdr.gridy=7;
                murmdrp.add(murmdrback,gbcmurmdr);
    
                gbcmurmdr.insets=new Insets(10,10,10,10);
                gbcmurmdr.gridx=2;
                gbcmurmdr.gridy=7;
                murmdrp.add(murmdrso,gbcmurmdr);
    
                murmdr.add(murmdrp);
            }
        }
    }
    
    public void btnActionReaderDelete()//BUTTON ACTION FOR "DELETE" (DELETING IN THE DATABASE)
    {
        String strremarkdr=drremark.getText().trim();
        int temporarydr=0;
        if(strremarkdr.equals(""))
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE ENTER THE REASON WHY READER IS BEING DELETED");
            temporarydr++;
        }
        else
        {
            if(temporarydr==0)
            {
                try
                {
                    rsLibMenumurmdr.updateString("R_ACTIVE","No");
                    rsLibMenumurmdr.updateString("R_REMARK",strremarkdr);
                    rsLibMenumurmdr.updateRow();
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    murmdr.setVisible(false);
                    LibMenuReaderMaster();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while deleting");
                }
            }
        }
    }
    
    public void LibMenuBookMaster()//DESIGNING OF PAGE FOR BOOK MASTER OPTIONS
    {
        LibMenubm=new JFrame("Book Master");
        LibMenubm.setVisible(true);
        LibMenubm.setSize(700,500);
        LibMenubm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panelbm=new JPanel(new GridBagLayout());

        JButton bm1=new JButton(" New  Book ");
        JButton bm2=new JButton("Modify Book");
        JButton bm3=new JButton("Delete Book");
        JButton bm4=new JButton("       Back      ");
        JButton bm5=new JButton("   Sign  out   ");

        GridBagConstraints gbcLibMenumubm=new GridBagConstraints();

        bm1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenubm.setVisible(false);
                    LibMenumubmnb();
                }
            });
        gbcLibMenumubm.insets=new Insets(10,10,10,10);
        gbcLibMenumubm.gridx=0;
        gbcLibMenumubm.gridy=0;
        panelbm.add(bm1,gbcLibMenumubm);

        bm2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenubm.setVisible(false);
                    LibMenumubmmb();
                }          
            });
        gbcLibMenumubm.insets=new Insets(10,10,10,10);
        gbcLibMenumubm.gridx=0;
        gbcLibMenumubm.gridy=1;
        panelbm.add(bm2,gbcLibMenumubm);

        bm3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenubm.setVisible(false);
                    LibMenumubmdb();
                }          
            });
        gbcLibMenumubm.insets=new Insets(10,10,10,10);
        gbcLibMenumubm.gridx=0;
        gbcLibMenumubm.gridy=2;
        panelbm.add(bm3,gbcLibMenumubm);

        bm4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenubm.setVisible(false);
                    LibMenumu();
                }          
            });
        gbcLibMenumubm.insets=new Insets(10,10,10,10);
        gbcLibMenumubm.gridx=0;
        gbcLibMenumubm.gridy=3;
        panelbm.add(bm4,gbcLibMenumubm);

        bm5.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenubm.setVisible(false);
                    main();
                }          
            });
        gbcLibMenumubm.insets=new Insets(10,10,10,10);
        gbcLibMenumubm.gridx=0;
        gbcLibMenumubm.gridy=4;
        panelbm.add(bm5,gbcLibMenumubm);

        LibMenubm.add(panelbm);
    }
    
    public void LibMenumubmnb()//DESIGNING OF PAGE FOR ADDING A BOOK (TO TAKE THE INPUTS)
    {
        mubmnb=new JFrame("New Book");
        mubmnb.setVisible(true);
        mubmnb.setSize(700,500);
        mubmnb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel mubmnbp=new JPanel(new GridBagLayout());
        JLabel mubmnbbn=new JLabel("Book Name");
        JLabel mubmnbau=new JLabel("Author");
        JLabel mubmnbpr=new JLabel("Price");
        JLabel mubmnbcu=new JLabel("Currency");
        JLabel mubmnbgn=new JLabel("Genre");
        JLabel mubmnbid=new JLabel("ID");
        mubmnbbn1=new JTextField(20);
        mubmnbau1=new JTextField(20);
        mubmnbpr1=new JTextField(10);
        mubmnbcu1=new JComboBox(CURRENCY);
        mubmnbgn1=new JComboBox(GENRE);
        mubmnbid1=new JTextField(10);
        JButton mubmnbadd=new JButton("Add");
        mubmnbadd.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    btnActionmubmnb();
                }
            });
        JButton mubmnbback=new JButton("Back");
        mubmnbback.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    mubmnb.setVisible(false);
                    LibMenuBookMaster();
                }          
            });
        JButton mubmnbso=new JButton("Sign out");
        mubmnbso.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    mubmnb.setVisible(false);
                    main();
                }          
            });
        GridBagConstraints mubmnbgbc=new GridBagConstraints();
        mubmnbbn1.setText("");
        mubmnbau1.setText("");
        mubmnbpr1.setText("");
        mubmnbid1.setText("");
        
        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=0;
        mubmnbp.add(mubmnbbn,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=0;
        mubmnbp.add(mubmnbbn1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=1;
        mubmnbp.add(mubmnbau,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=1;
        mubmnbp.add(mubmnbau1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=2;
        mubmnbp.add(mubmnbpr,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=2;
        mubmnbp.add(mubmnbpr1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=3;
        mubmnbp.add(mubmnbcu,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=3;
        mubmnbp.add(mubmnbcu1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=4;
        mubmnbp.add(mubmnbgn,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=4;
        mubmnbp.add(mubmnbgn1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=5;
        mubmnbp.add(mubmnbid,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=5;
        mubmnbp.add(mubmnbid1,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=0;
        mubmnbgbc.gridy=6;
        mubmnbp.add(mubmnbadd,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=1;
        mubmnbgbc.gridy=6;
        mubmnbp.add(mubmnbback,mubmnbgbc);

        mubmnbgbc.insets=new Insets(10,10,10,10);
        mubmnbgbc.gridx=2;
        mubmnbgbc.gridy=6;
        mubmnbp.add(mubmnbso,mubmnbgbc);

        mubmnb.add(mubmnbp);
    }

    public void btnActionmubmnb()//BUTTON ACTION FOR "ADD"
    {
            try
            {
                String sqlmubmnb="select * from BOOK_MST";
                rsmubmnb=st.executeQuery(sqlmubmnb);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            temporary=1;
            idmubmnb=mubmnbid1.getText().trim();
            bnamemubmnb=mubmnbbn1.getText().trim();
            authormubmnb=mubmnbau1.getText().trim();
            String strpricemubmnb=mubmnbpr1.getText().trim();
            try
            {
                pricemubmnb=Integer.parseInt(strpricemubmnb);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE ENTER A NUMERIC VALUE FOR PRICE");
                temporary++;
            }
            currencymubmnb=mubmnbcu1.getSelectedItem().toString();
            genremubmnb=mubmnbgn1.getSelectedItem().toString();
            if(idmubmnb.equals("") || bnamemubmnb.equals("") || authormubmnb.equals("") || strpricemubmnb=="")
            {
                JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                temporary++;
            }
            if(temporary==1 && genremubmnb.equals("Combination of genres"))
            {
                temporary++;
                final JFrame genresmubmnb;
                genresmubmnb=new JFrame("PLEASE ENTER THE GENRES");
                mubmnb.setVisible(false);
                genresmubmnb.setVisible(true);
                genresmubmnb.setSize(500,300);
                genresmubmnb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel genrestriallabel=new JLabel("GENRES");
                genrestrial=new JTextField(20);
                JButton ok=new JButton("OK");
                
                ok.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        genremubmnb=genrestrial.getText().trim();
                        genresmubmnb.setVisible(false);
                        temporary=1;
                        AddTheBook();
                    }          
                });
                JButton back=new JButton ("BACK");
                back.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        temporary++;
                        genresmubmnb.setVisible(false);
                        mubmnb.setVisible(true);
                    }          
                });
                JButton so=new JButton ("SIGN OUT");
                so.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        temporary++;
                        genresmubmnb.setVisible(false);
                        main();
                    }          
                });
                
                JPanel genrespanel=new JPanel(new GridBagLayout());
                GridBagConstraints gbcgenres=new GridBagConstraints();
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=0;
                gbcgenres.gridy=0;
                genrespanel.add(genrestriallabel,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=1;
                gbcgenres.gridy=0;
                genrespanel.add(genrestrial,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=0;
                gbcgenres.gridy=1;
                genrespanel.add(ok,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=1;
                gbcgenres.gridy=1;
                genrespanel.add(back,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=2;
                gbcgenres.gridy=1;
                genrespanel.add(so,gbcgenres);
                
                genresmubmnb.add(genrespanel);
            }
            try
            {
                while(temporary==1 && rsmubmnb.next())
                {
                    String idtempmubmnb=rsmubmnb.getString("B_ID");
                    if(idtempmubmnb.equals(idmubmnb))
                    {
                        JOptionPane.showMessageDialog(null,"Sorry but there is another Book with the same id");
                        temporary++;
                    }
                }
                while(temporary==1 && rsmubmnb.previous())
                {
                    String idtempmubmnb=rsmubmnb.getString("B_ID");
                    if(idtempmubmnb.equals(idmubmnb))
                    {
                        JOptionPane.showMessageDialog(null,"Sorry but there is another Book with the same id");
                        temporary++;
                    }
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while checking");
            }
            if(temporary==1)
            {
                AddTheBook();
            }
    }
    public void AddTheBook()//ADDING IN THE DATABASE
    {
         try
         {
              rsmubmnb.moveToInsertRow();
              rsmubmnb.updateString("B_NAME",bnamemubmnb);
              rsmubmnb.updateString("B_AUTHOR",authormubmnb);
              rsmubmnb.updateInt("B_PRICE",pricemubmnb);
              rsmubmnb.updateString("B_CURRENCY",currencymubmnb);
              rsmubmnb.updateString("B_GENRE",genremubmnb);
              rsmubmnb.updateString("B_ID",idmubmnb);
              rsmubmnb.updateString("B_ACTIVE","Yes");
              rsmubmnb.updateString("B_AVAILABLE","Yes");
              rsmubmnb.insertRow();
              rsmubmnb.close();
              st.close();
              st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
              JOptionPane.showMessageDialog(null,"Book Added");
              mubmnb.setVisible(false);
              LibMenuBookMaster();
         }
         catch(Exception ex)
         {
             JOptionPane.showMessageDialog(null,"Exception while Added");
         }
    }
    
    public void LibMenumubmmb()//TAKING THE RAEDER ID WHICH IS TO BE MODIFIED
    {
        mubmmbentryidtext=new JTextField(10);
        JLabel mubmmbentryidlabel=new JLabel("Please enter the Book id");
        JPanel mubmmbentryidpanel=new JPanel();
        mubmmbentryidpanel.add(mubmmbentryidlabel);
        mubmmbentryidpanel.add(mubmmbentryidtext);
        int selectedoptionIDModifyBook=JOptionPane.showConfirmDialog(null,mubmmbentryidpanel,"ID",JOptionPane.OK_CANCEL_OPTION);
        if(selectedoptionIDModifyBook==JOptionPane.OK_OPTION)
        {
            btnActionIDmubmmb();
        }
        else
        {
            LibMenuBookMaster();
        }
    }

    public void btnActionIDmubmmb()//DESIGNING OF PAGE FOR MODIFYING A BOOK (TO MODIFY THE DATA)
    {
        String ID=mubmmbentryidtext.getText().trim();
        temporary=0;
        mubmmbbn1=new JTextField(20);
        mubmmbau1=new JTextField(20);
        mubmmbpr1=new JTextField(10);
        mubmmbcu1=new JComboBox(CURRENCY);
        mubmmbgn1=new JComboBox(GENRE);
        mubmmbid1=new JTextField(10);
        try
        {
            String sqlLibMenumubmmb="select * from BOOK_MST where B_ID='"+ID+"' and B_ACTIVE='Yes'";
            rsmubmmb=st.executeQuery(sqlLibMenumubmmb);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Before Button");
        }
        try
        {
            if(rsmubmmb.next())
            {
                mubmmbbn1.setText(rsmubmmb.getString("B_NAME"));
                mubmmbau1.setText(rsmubmmb.getString("B_AUTHOR"));
                mubmmbpr1.setText(rsmubmmb.getString("B_PRICE"));
                mubmmbid1.setText(rsmubmmb.getString("B_ID"));
            }
            else
            {
                JOptionPane.showMessageDialog(null,"ID DOES NOT EXIST");
                temporary++;
                LibMenuBookMaster();
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Again");
        }
        if(temporary==0)
        {
            mubmmb=new JFrame("Modify Book");
            mubmmb.setVisible(true);
            mubmmb.setSize(700,500);
            mubmmb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel mubmmbp=new JPanel(new GridBagLayout());
            JLabel mubmmbbn=new JLabel("Book Name");
            JLabel mubmmbau=new JLabel("Author");
            JLabel mubmmbpr=new JLabel("Price");
            JLabel mubmmbcu=new JLabel("Currency");
            JLabel mubmmbgn=new JLabel("Genre");
            JLabel mubmmbid=new JLabel("ID");
            JButton mubmmbmodify=new JButton("Modify");
            mubmmbmodify.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionBookModify();
                    }
                });
                JButton mubmmbback=new JButton("Back");
            mubmmbback.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        mubmmb.setVisible(false);
                        LibMenuBookMaster();
                    }          
                });
                JButton mubmmbso=new JButton("Sign out");
                mubmmbso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        mubmmb.setVisible(false);
                        main();
                    }          
                });
            GridBagConstraints gbcmubmmb=new GridBagConstraints();
            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=0;
            mubmmbp.add(mubmmbbn,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=0;
            mubmmbp.add(mubmmbbn1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=1;
            mubmmbp.add(mubmmbau,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=1;
            mubmmbp.add(mubmmbau1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=2;
            mubmmbp.add(mubmmbpr,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=2;
            mubmmbp.add(mubmmbpr1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=3;
            mubmmbp.add(mubmmbcu,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=3;
            mubmmbp.add(mubmmbcu1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=4;
            mubmmbp.add(mubmmbgn,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=4;
            mubmmbp.add(mubmmbgn1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=5;
            mubmmbp.add(mubmmbid,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=5;
            mubmmbp.add(mubmmbid1,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=0;
            gbcmubmmb.gridy=6;
            mubmmbp.add(mubmmbmodify,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=1;
            gbcmubmmb.gridy=6;
            mubmmbp.add(mubmmbback,gbcmubmmb);

            gbcmubmmb.insets=new Insets(10,10,10,10);
            gbcmubmmb.gridx=2;
            gbcmubmmb.gridy=6;
            mubmmbp.add(mubmmbso,gbcmubmmb);

            mubmmb.add(mubmmbp);
            JOptionPane.showMessageDialog(null,"PLEASE NOTE THAT THE CURRENCY BEING SHOWN IS INR AND GENRE AUTOBIOGRAPHY. PLEASE CHANGE IT AS PER REQUIREMENT");
        }
    }
    
    public void btnActionBookModify()//BUTTON ACTION FOR "MODIFY"
    {
            temporary=1;
            mubmmbBN=mubmmbbn1.getText().trim();
            mubmmbAU=mubmmbau1.getText().trim();
            String strmubmmbPR=mubmmbpr1.getText().trim();
            mubmmbCU=mubmmbcu1.getSelectedItem().toString();
            mubmmbGN=mubmmbgn1.getSelectedItem().toString();
            mubmmbID=mubmmbid1.getText().trim();
            if(mubmmbBN.equals("") || mubmmbAU.equals("") || strmubmmbPR.equals("") || mubmmbID.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                temporary++;
            }
            try
            {
                mubmmbPR=Integer.parseInt(strmubmmbPR);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"PLEASE ENTER A NUMERIC VALUE FOR PRICE");
                temporary++;
            }
            if(temporary==1 && mubmmbGN.equals("Combination of genres"))
            {
                temporary++;
                final JFrame genresmubmmb;
                genresmubmmb=new JFrame("PLEASE ENTER THE GENRES");
                mubmmb.setVisible(false);
                genresmubmmb.setVisible(true);
                genresmubmmb.setSize(500,300);
                genresmubmmb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JLabel genrestriallabel=new JLabel("GENRES");
                genrestrial=new JTextField(20);
                JButton ok=new JButton("OK");
                
                ok.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        mubmmbGN=genrestrial.getText().trim();
                        genresmubmmb.setVisible(false);
                        temporary=1;
                        ModifyTheBook();
                    }          
                });
                JButton back=new JButton ("BACK");
                back.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        temporary++;
                        genresmubmmb.setVisible(false);
                        mubmmb.setVisible(true);
                    }          
                });
                JButton so=new JButton ("SIGN OUT");
                so.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        temporary++;
                        genresmubmmb.setVisible(false);
                        main();
                    }          
                });
                
                JPanel genrespanel=new JPanel(new GridBagLayout());
                GridBagConstraints gbcgenres=new GridBagConstraints();
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=0;
                gbcgenres.gridy=0;
                genrespanel.add(genrestriallabel,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=1;
                gbcgenres.gridy=0;
                genrespanel.add(genrestrial,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=0;
                gbcgenres.gridy=1;
                genrespanel.add(ok,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=1;
                gbcgenres.gridy=1;
                genrespanel.add(back,gbcgenres);
                
                gbcgenres.insets=new Insets(10,10,10,10);
                gbcgenres.gridx=2;
                gbcgenres.gridy=1;
                genrespanel.add(so,gbcgenres);
                
                genresmubmmb.add(genrespanel);
            }
            
            if(temporary==1)
            {
                ModifyTheBook();
            }
    }
    
    public void ModifyTheBook()//MODIFYING IN THE DATABASE
    {
        try
        {
            rsmubmmb.updateString("B_NAME",mubmmbBN);
            rsmubmmb.updateString("B_AUTHOR",mubmmbAU);
            rsmubmmb.updateInt("B_PRICE",mubmmbPR);
            rsmubmmb.updateString("B_CURRENCY",mubmmbCU);
            rsmubmmb.updateString("B_GENRE",mubmmbGN);   
            rsmubmmb.updateString("B_ID",mubmmbID);
            rsmubmmb.updateRow();
            JOptionPane.showMessageDialog(null,"Record Updated");
            mubmmb.setVisible(false);
            LibMenuBookMaster();
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT THE ID REFERS TO THAT OF AN EXISTING BOOK");
        }
    }
    
    public void LibMenumubmdb()//TAKING THE RAEDER ID WHICH IS TO BE DELETED
    {
        mubmdbentryidtext=new JTextField(10);
        JLabel mubmdbentryidlabel=new JLabel("Please enter the Book id");
        JPanel mubmdbentryidpanel=new JPanel();
        mubmdbentryidpanel.add(mubmdbentryidlabel);
        mubmdbentryidpanel.add(mubmdbentryidtext);
        int selectedoptionIDDeleteBook=JOptionPane.showConfirmDialog(null,mubmdbentryidpanel,"ID",JOptionPane.OK_CANCEL_OPTION);
        if(selectedoptionIDDeleteBook==JOptionPane.OK_OPTION)
        {
            btnActionIDmubmdb();
        }
        else
        {
            LibMenuBookMaster();
        }
    }

    public void btnActionIDmubmdb()//DESIGNING OF PAGE FOR MODIFYING A BOOK
    {
        String ID=mubmdbentryidtext.getText().trim();
        temporary=0;
        mubmdbbn1=new JLabel();
        mubmdbau1=new JLabel();
        mubmdbpr1=new JLabel();
        mubmdbcu1=new JLabel();
        mubmdbgn1=new JLabel();
        mubmdbid1=new JLabel();
        try
        {
            String sqlmubmdb="select * from BOOK_MST where B_ID='"+ID+"' and B_ACTIVE='Yes'";
            rsmubmdb=st.executeQuery(sqlmubmdb);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception Before Button");
        }
        try
        {
            if(rsmubmdb.next())
            {
                String strBook_availabel=rsmubmdb.getString("B_AVAILABLE");
                if(strBook_availabel.equals("No"))
                {
                    temporary++;
                    JOptionPane.showMessageDialog(null,"SORRY BUT THE BOOK HAD BEEN ISSUED AND YET NOT RETURNED. SO, IT CANNOT BE DELETED");
                    LibMenuBookMaster();
                }
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        if(temporary==0)
        {
            try
            {
                if(rsmubmdb.first())
                {
                    mubmdbbn1.setText(rsmubmdb.getString("B_NAME"));
                    mubmdbau1.setText(rsmubmdb.getString("B_AUTHOR"));
                    mubmdbpr1.setText(rsmubmdb.getString("B_PRICE"));
                    mubmdbcu1.setText(rsmubmdb.getString("B_CURRENCY"));
                    mubmdbgn1.setText(rsmubmdb.getString("B_GENRE"));
                    mubmdbid1.setText(rsmubmdb.getString("B_ID"));
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"ID DOES NOT EXIST");
                    temporary++;
                    LibMenuBookMaster();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception Again");
            }
        }
        if(temporary==0)
        {
            mubmdb=new JFrame("Delete Book");
            mubmdb.setVisible(true);
            mubmdb.setSize(700,500);
            mubmdb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            JPanel mubmdbp=new JPanel(new GridBagLayout());
            JLabel mubmdbbn=new JLabel("B_NAME");
            JLabel mubmdbau=new JLabel("B_AUTHOR");
            JLabel mubmdbpr=new JLabel("B_PRICE");
            JLabel mubmdbcu=new JLabel("B_CURRENCY");
            JLabel mubmdbgn=new JLabel("B_GENRE");
            JLabel mubmdbid=new JLabel("B_ID");
            JLabel mubmdbremarklabel=new JLabel("Remark");
            dbremarktext=new JTextField(20);
            JButton mubmdbdelete=new JButton("Delete");
            mubmdbdelete.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionBookDelete();
                    }
                });
                JButton mubmdbback=new JButton("Back");
            mubmdbback.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        mubmdb.setVisible(false);
                        LibMenuBookMaster();
                    }          
                });
                JButton mubmdbso=new JButton("Sign out");
                mubmdbso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        mubmdb.setVisible(false);
                        main();
                    }          
                });
            GridBagConstraints gbcmubmdb=new GridBagConstraints();
            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=0;
            mubmdbp.add(mubmdbbn,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=0;
            mubmdbp.add(mubmdbbn1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=1;
            mubmdbp.add(mubmdbau,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=1;
            mubmdbp.add(mubmdbau1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=2;
            mubmdbp.add(mubmdbpr,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=2;
            mubmdbp.add(mubmdbpr1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=3;
            mubmdbp.add(mubmdbcu,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=3;
            mubmdbp.add(mubmdbcu1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=4;
            mubmdbp.add(mubmdbgn,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=4;
            mubmdbp.add(mubmdbgn1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=5;
            mubmdbp.add(mubmdbid,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=5;
            mubmdbp.add(mubmdbid1,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=6;
            mubmdbp.add(mubmdbremarklabel,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=6;
            mubmdbp.add(dbremarktext,gbcmubmdb);
            
            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=0;
            gbcmubmdb.gridy=7;
            mubmdbp.add(mubmdbdelete,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=1;
            gbcmubmdb.gridy=7;
            mubmdbp.add(mubmdbback,gbcmubmdb);

            gbcmubmdb.insets=new Insets(10,10,10,10);
            gbcmubmdb.gridx=2;
            gbcmubmdb.gridy=7;
            mubmdbp.add(mubmdbso,gbcmubmdb);

            mubmdb.add(mubmdbp);
        }
    }
    public void btnActionBookDelete()//TO DELETE THE DATA
    {
        int temporary=0;
        String remarkdeletebook=dbremarktext.getText().trim();
        if(remarkdeletebook.equals(""))
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT A REMARK IS REQUIRED AS TO WHY THE BOOK IS BEING DELETED");
            temporary++;
        }
        else
        {
            if(temporary==0)
            {
                try
                {
                    rsmubmdb.updateString("B_ACTIVE","No");
                    rsmubmdb.updateString("B_AVAILABLE","No");
                    rsmubmdb.updateString("B_REMARK",remarkdeletebook);
                    rsmubmdb.updateRow();
                    JOptionPane.showMessageDialog(null,"Record Deleted");
                    mubmdb.setVisible(false);
                    LibMenuBookMaster();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while deleting");
                }
            }
        }
    }

    public void date()//TO OBTAIN TODAY'S AND & DAYS LATER'S DATE 
    {
       Calendar dt1=Calendar.getInstance();
       Calendar dt2=Calendar.getInstance();
       dt1.setTime(new java.util.Date());
       dt1.add(Calendar.DATE,7);
       dt2.setTime(new java.util.Date());
       today=(sdf.format(dt2.getTime())).toString();
       duedate=(sdf.format(dt1.getTime())).toString();
       LibMenuTransactions();
    }
    
    public void LibMenuTransactions()//DESIGNING THE PAGE FOR TRANSACTION MENU CHOICES
    {
        LibMenuTM=new JFrame("Transactions");
        LibMenuTM.setVisible(true);
        LibMenuTM.setSize(700,500);
        LibMenuTM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel paneltm=new JPanel(new GridBagLayout());

        JButton tm1=new JButton     ("  Book  Issue  ");
        JButton tm2=new JButton     (" Book  Return ");
        JButton tm3=new JButton     ("         Back        ");
        JButton tm4=new JButton     ("     Sign  out     ");

        GridBagConstraints gbcLibMenutm=new GridBagConstraints();

        tm1.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent aemu)
                {
                    LibMenuTM.setVisible(false);
                    LibMenutmbi();
                }
            });
        gbcLibMenutm.insets=new Insets(10,10,10,10);
        gbcLibMenutm.gridx=0;
        gbcLibMenutm.gridy=0;
        paneltm.add(tm1,gbcLibMenutm);

        tm2.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuTM.setVisible(false);
                    LibMenutmbr();
                }          
            });
        gbcLibMenutm.insets=new Insets(10,10,10,10);
        gbcLibMenutm.gridx=0;
        gbcLibMenutm.gridy=1;
        paneltm.add(tm2,gbcLibMenutm);

        tm3.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuTM.setVisible(false);
                    LibMenu();
                }          
            });
        gbcLibMenutm.insets=new Insets(10,10,10,10);
        gbcLibMenutm.gridx=0;
        gbcLibMenutm.gridy=3;
        paneltm.add(tm3,gbcLibMenutm);

        tm4.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuTM.setVisible(false);
                    main();
                }          
            });
        gbcLibMenutm.insets=new Insets(10,10,10,10);
        gbcLibMenutm.gridx=0;
        gbcLibMenutm.gridy=4;
        paneltm.add(tm4,gbcLibMenutm);

        LibMenuTM.add(paneltm);
    }
    
    public void LibMenutmbi()//DESIGNING THE PAGE FOR BOOK ISSUE
    {
        JOptionPane.showMessageDialog(null,"Please enter the date in the DD-MM-YYYY format");
        tmbi=new JFrame("Book Issue");
        tmbi.setVisible(true);
        tmbi.setSize(700,500);
        tmbi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel tmbip=new JPanel(new GridBagLayout());
        JLabel tmbirid=new JLabel("Reader ID");
        JLabel tmbibid=new JLabel("Book ID");
        JLabel tmbiid=new JLabel("Issue Date");
        JLabel tmbiprd=new JLabel("Due Date");
        JLabel tmbiuid=new JLabel("ID");
        tmbirid1=new JTextField(20);
        tmbibid1=new JTextField(20);
        tmbiid1=new JTextField(20);
        tmbiprd1=new JTextField(20);
        JLabel tmbiuid1=new JLabel(user);
        JButton tmbiissue=new JButton("Issue");
        tmbiissue.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    btnActiontmbi();
                }
            });
        JButton tmbiback=new JButton("Back");
        tmbiback.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    tmbi.setVisible(false);
                    LibMenuTransactions();
                }          
            });
        JButton tmbiso=new JButton("Sign out");
        tmbiso.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    tmbi.setVisible(false);
                    main();
                }          
            });
        GridBagConstraints tmbigbc=new GridBagConstraints();
        tmbirid1.setText("");
        tmbibid1.setText("");
        tmbiid1.setText(today);
        tmbiprd1.setText(duedate);
        
        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=0;
        tmbip.add(tmbirid,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=0;
        tmbip.add(tmbirid1,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=1;
        tmbip.add(tmbibid,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=1;
        tmbip.add(tmbibid1,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=2;
        tmbip.add(tmbiid,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=2;
        tmbip.add(tmbiid1,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=3;
        tmbip.add(tmbiprd,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=3;
        tmbip.add(tmbiprd1,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=4;
        tmbip.add(tmbiuid,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=4;
        tmbip.add(tmbiuid1,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=0;
        tmbigbc.gridy=5;
        tmbip.add(tmbiissue,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=1;
        tmbigbc.gridy=5;
        tmbip.add(tmbiback,tmbigbc);

        tmbigbc.insets=new Insets(10,10,10,10);
        tmbigbc.gridx=2;
        tmbigbc.gridy=5;
        tmbip.add(tmbiso,tmbigbc);

        tmbi.add(tmbip);
    }

    public void btnActiontmbi()//BUTTON ACTION FOR "ISSUE"
    {
        int proceed=0;
        int temporary=1;
        readeridtmbi=tmbirid1.getText().trim();
        bookidtmbi=tmbibid1.getText().trim();
        String bookissuetmbi=tmbiid1.getText().trim();
        String bookplanneddatetmbi=tmbiprd1.getText().trim();
        try
        {
            java.util.Date dttrial1=sdf.parse(bookissuetmbi);
            java.util.Date dttrial2=sdf.parse(bookplanneddatetmbi);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Sorry,but please enter the date in the DD-MM-YYYY format");
            proceed++;
        }
        if(proceed==0)
        {
            String sqltmbitrnmenu="select * from BOOK_TRN";
            String sqltmbibkmenu="select * from BOOK_MST where B_ID='"+bookidtmbi+"'";
            String sqltmbirdmenu="select * from READER_MST where R_ID='"+readeridtmbi+"'";
            try
            {
                if(readeridtmbi.equals("") || bookidtmbi.equals("") || bookissuetmbi.equals("") || bookplanneddatetmbi.equals(""))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                    temporary++;
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while blank textfield check");
            }
            try
            {
                if(temporary==1)
                {
                    rstmbirdmenu=st.executeQuery(sqltmbirdmenu);
                    if(rstmbirdmenu.next())
                    {
                        String strReaderIdTrnMenu = rstmbirdmenu.getString("RID_COUNT");
                        ReaderIdTrnMenu=Integer.parseInt(strReaderIdTrnMenu);
                        String readeractive=rstmbirdmenu.getString("R_ACTIVE");
                        if(readeractive=="No")
                        {
                            JOptionPane.showMessageDialog(null,"Sorry but the Reader has been deleted");
                            temporary++;
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Sorry but the Reader does not exist");
                        temporary++;
                    }
                    rstmbirdmenu.close();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Exception while user availability check");
            }
            try
            {
                if(temporary==1)
                {
                    rstmbibkmenu=st.executeQuery(sqltmbibkmenu);
                    if(rstmbibkmenu.next())
                    {
                        String bookactive=rstmbibkmenu.getString("B_ACTIVE");
                        if(bookactive=="No")
                        {
                            JOptionPane.showMessageDialog(null,"Sorry but the Book has been deleted");
                            temporary++;
                        }
                        String bookavailable=rstmbibkmenu.getString("B_AVAILABLE");
                        if(bookavailable.equals("No"))
                        {
                            JOptionPane.showMessageDialog(null,"Sorry but the Book has been issued");
                            temporary++;
                        }
                        rstmbibkmenu.close();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null,"Sorry but the Book does not exist");
                        temporary++;
                    }
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Exception while book active and available check");
            }
             try
             {
                 if(temporary==1)
                 {
                     int selected_option_trn_menu=0;
                     if(ReaderIdTrnMenu!=0)
                     {
                         selected_option_trn_menu=JOptionPane.showConfirmDialog(null,"THIS USER ALREADY HAS "+ReaderIdTrnMenu+" BOOKS ISSUED EXCLUDING THE ONE TAKEN NOW. DO YOU STILL WANTTO PROCEED?","PLEASE CONFIRM",JOptionPane.YES_NO_OPTION);
                     }
                     if(selected_option_trn_menu==JOptionPane.YES_OPTION)
                     {
                         rstmbitrnmenu=st.executeQuery(sqltmbitrnmenu);
                         rstmbitrnmenu.moveToInsertRow();
                         rstmbitrnmenu.updateString("R_ID",readeridtmbi);
                         rstmbitrnmenu.updateString("B_ID",bookidtmbi);
                         rstmbitrnmenu.updateString("B_ISSUE_DATE",bookissuetmbi);
                         rstmbitrnmenu.updateString("B_PLANNED_RETURN_DATE",bookplanneddatetmbi);
                         rstmbitrnmenu.updateString("U_LOGIN",user);
                         rstmbitrnmenu.insertRow();
                         rstmbitrnmenu.close();
                         st.close();
                         st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                         JOptionPane.showMessageDialog(null,"Book Issued");
                     }
                     else
                     {
                         temporary++;
                     }
                 }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null,"Exception while issuing");
            }
            if(temporary==1)
            {
                tmbi.setVisible(false);
                RequiredChanges();
            }
        }
    }
    
    public void RequiredChanges()//CHANGES TO BE MADE IN THE READER AND BOOK MASTER TABLE
    {
        try
        {
            String sqltmbirdmenuchanges="select * from READER_MST where R_ID='"+readeridtmbi+"'";
            ResultSet rstmbirdmenuchanges=st.executeQuery(sqltmbirdmenuchanges);
            ReaderIdTrnMenu++;
            String strIncReaderIdTrnMenu=String.valueOf(ReaderIdTrnMenu);
            if(rstmbirdmenuchanges.next())
            {
                rstmbirdmenuchanges.updateString("RID_COUNT",strIncReaderIdTrnMenu);
                rstmbirdmenuchanges.updateRow();
                rstmbirdmenuchanges.close();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        try
        {
            String sqltmbibkmenuchanges="select * from BOOK_MST where B_ID='"+bookidtmbi+"'";
            ResultSet rstmbibkmenuchanges=st.executeQuery(sqltmbibkmenuchanges);
            if(rstmbibkmenuchanges.next())
            {
                rstmbibkmenuchanges.updateString("B_AVAILABLE","No");
                rstmbibkmenuchanges.updateRow();
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        LibMenuTransactions();
    }
    
    public void LibMenutmbr()//DESIGNING THE PAGE FOR BOOK RETURN
    {
        JTextField bi_input=new JTextField(10);
        JPanel biinputpanel=new JPanel();
        biinputpanel.add(bi_input);
        int selected_option_book_return=JOptionPane.showConfirmDialog(null,biinputpanel,"PLEASE ENTER THE BOOK ID",JOptionPane.OK_CANCEL_OPTION);
        int temp_b_return;
        if(selected_option_book_return==JOptionPane.OK_OPTION)
        {
            temp_b_return=0;
        }
        else
        {
            LibMenuTransactions();
            temp_b_return=1;
        }
        if(temp_b_return==0)
        {
            String B_ID_INPUT=bi_input.getText().trim();
            if(B_ID_INPUT.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Please enter something");
                LibMenutmbr();
            }
            try
            {
                sql_book_return_trnmenu="select * from BOOK_TRN where B_ID='"+B_ID_INPUT+"'";
                rs_book_return_trnmenu=st.executeQuery(sql_book_return_trnmenu);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while connection");
                ex.printStackTrace();
            }
            try
            {
                if(rs_book_return_trnmenu.next())
                {
                    r_id=rs_book_return_trnmenu.getString("R_ID");
                    b_id=rs_book_return_trnmenu.getString("B_ID");
                    b_issue_date=rs_book_return_trnmenu.getString("B_ISSUE_DATE");
                    b_planned_return_date=rs_book_return_trnmenu.getString("B_PLANNED_RETURN_DATE");
                    u_login=rs_book_return_trnmenu.getString("U_LOGIN");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Sorry but the book was not with any reader");
                    temp_b_return++;
                    LibMenutmbr();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while extracting");
                ex.printStackTrace();
            }
            if(temp_b_return==0)
            {
                Calendar dt3=Calendar.getInstance();
                today=(sdf.format(dt3.getTime())).toString();
                b_return_frame=new JFrame("Book Return");
                b_return_frame.setSize(700,500);
                b_return_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                b_return_frame.setVisible(true);
                JLabel r_id_label=new JLabel("Reader Id");
                JLabel r_id_data=new JLabel(r_id);
                JLabel b_id_label=new JLabel("Book Id");
                JLabel b_id_data=new JLabel(b_id);
                JLabel b_issue_date_label=new JLabel("Book Issue Date");
                JLabel b_issue_date_data=new JLabel(b_issue_date);
                JLabel b_planned_return_date_label=new JLabel("Book Due Date");
                JLabel b_planned_return_date_data=new JLabel(b_planned_return_date);
                JLabel u_login_label=new JLabel("Issue User");
                JLabel u_login_data=new JLabel(u_login);
                JLabel b_actual_return_date_label=new JLabel("Book Return Date");
                b_actual_return_date_data=new JTextField(10);
                b_actual_return_date_data.setText(today);
                JButton return_b_rtn=new JButton("Return");
                return_b_rtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        btnAction_Book_Return();
                    }
                });
                JButton back_b_rtn=new JButton("Back");
                back_b_rtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        b_return_frame.setVisible(false);
                        LibMenuTransactions();
                    }
                });
                JButton so_b_rtn=new JButton("Sign Out");
                so_b_rtn.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        b_return_frame.setVisible(false);
                        main();
                    }
                });
                GridBagConstraints gbc_book_return=new GridBagConstraints();
                JPanel Book_return_panel=new JPanel(new GridBagLayout());
            
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=0;
                Book_return_panel.add(r_id_label,gbc_book_return);
            
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=0;
                Book_return_panel.add(r_id_data,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=1;
                Book_return_panel.add(b_id_label,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=1;
                Book_return_panel.add(b_id_data,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=2;
                Book_return_panel.add(b_issue_date_label,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=2;
                Book_return_panel.add(b_issue_date_data,gbc_book_return);
            
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=3;
                Book_return_panel.add(b_planned_return_date_label,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=3;
                Book_return_panel.add(b_planned_return_date_data,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=4;
                Book_return_panel.add(u_login_label,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=4;
                Book_return_panel.add(u_login_data,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=5;
                Book_return_panel.add(b_actual_return_date_label,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=5;
                Book_return_panel.add(b_actual_return_date_data,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=0;
                gbc_book_return.gridy=6;
                Book_return_panel.add(return_b_rtn,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=1;
                gbc_book_return.gridy=6;
                Book_return_panel.add(back_b_rtn,gbc_book_return);
                
                gbc_book_return.insets=new Insets(10,10,10,10);
                gbc_book_return.gridx=2;
                gbc_book_return.gridy=6;
                Book_return_panel.add(so_b_rtn,gbc_book_return);
                
                b_return_frame.add(Book_return_panel);
            }
        }
    }
    
    public void btnAction_Book_Return()//BUTTON ACTION FOR "RETURN"
    {
        String actualreturndate=b_actual_return_date_data.getText().trim();
        int proceed=0;
        try
        {
            java.util.Date dttrial3=sdf.parse(actualreturndate);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Sorry,but please enter the date in the DD-MM-YYYY format");
            b_return_frame.setVisible(true);
            proceed++;
        }
        if(proceed==0)
        {
            try
            {
                String sql_book_return_rtnmenu="select * from B_RTN";
                rs_Book_return_rtnmenu=st.executeQuery(sql_book_return_rtnmenu);
                rs_Book_return_rtnmenu.moveToInsertRow();
                rs_Book_return_rtnmenu.updateString("R_ID",r_id);
                rs_Book_return_rtnmenu.updateString("B_ID",b_id);
                rs_Book_return_rtnmenu.updateString("B_ISSUE_DATE",b_issue_date);
                rs_Book_return_rtnmenu.updateString("B_PLANNED_RETURN_DATE",b_planned_return_date);
                rs_Book_return_rtnmenu.updateString("U_LOGIN",u_login);
                rs_Book_return_rtnmenu.updateString("B_ACTUAL_RETURN_DATE",actualreturndate);
                rs_Book_return_rtnmenu.updateString("U_LOGIN_RECEIVER",user);
                rs_Book_return_rtnmenu.insertRow();
                rs_Book_return_rtnmenu.close();
                st.close();
                st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
                JOptionPane.showMessageDialog(null,"Book Returned");
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while adding");
                ex.printStackTrace();
            }
            
            try
            {
                rs_book_return_trnmenu=st.executeQuery(sql_book_return_trnmenu);
                if(rs_book_return_trnmenu.next())
                {
                    rs_book_return_trnmenu.deleteRow();
                }
                rs_book_return_trnmenu.close();
                st.close();
                st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while deleting");
                ex.printStackTrace();
            }
            ResultSet rs1,rs2;
            String sql1="select * from BOOK_MST where B_ID='"+b_id+"'";
            String sql2="select * from READER_MST where R_ID='"+r_id+"'";
            try
            {
                rs2=st.executeQuery(sql2);
                if(rs2.next())
                {
                    int num=Integer.parseInt(rs2.getString("RID_COUNT"));
                    num--;
                    String str_num=String.valueOf(num);
                    rs2.updateString("RID_COUNT",str_num);
                    rs2.updateRow();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            try
            {
                rs1=st.executeQuery(sql1);
                if(rs1.next())
                {
                    rs1.updateString("B_AVAILABLE","Yes");
                    rs1.updateRow();
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
    }
    
    public void LibMenumm()//DESIGNING THE PAGE FOR MAINTENANCE MENU
    {
                try
                {
                    String sqllmmm="select * from USER_MST where U_LOGIN='"+user+"'";
                    rslmmm=st.executeQuery(sqllmmm);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while connecting");
                    LibMenumm();
                }
                LibMenuMM=new JFrame("Change Password For Self");
                LibMenuMM.setVisible(true);
                LibMenuMM.setSize(700,500);
                LibMenuMM.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel LibMenuMMp=new JPanel(new GridBagLayout());
                JLabel LIlmmm=new JLabel("Login ID");
                JLabel UNlmmm=new JLabel("User Name");
                JLabel PWoldlmmm=new JLabel(" Old Password");
                JLabel PWnewlmmm=new JLabel(" New Password");
                JLabel PWconfirmlmmm=new JLabel(" Confirm Password");
                JLabel Clmmm=new JLabel("Category");
               
                pwoldlmmm=new JPasswordField(20);
                pwnewlmmm=new JPasswordField(20);
                pwconfirmlmmm=new JPasswordField(20);
                JButton lmmmchangepassword=new JButton("Change");
                lmmmchangepassword.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionLMMMCHANGEPASSWORD();
                    }
                });
                JButton lmmmb=new JButton("Back");
                lmmmb.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        LibMenuMM.setVisible(false);
                        LibMenu();
                    }          
                });
                JButton lmmmso=new JButton("Sign out");
                lmmmso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        LibMenuMM.setVisible(false);
                        main();
                    }          
                });
                GridBagConstraints gbclmmm=new GridBagConstraints();
                pwoldlmmm.setText("");
                pwnewlmmm.setText("");
                pwconfirmlmmm.setText("");
                String strlilmmm="",strunlmmm="",strcalmmm="";
                try
                {
                    if(rslmmm.next())
                    {
                        strlilmmm=rslmmm.getString("U_LOGIN");
                        strunlmmm=rslmmm.getString("U_NAME");
                        strcalmmm=rslmmm.getString("U_CATEGORY");
                        strpasslmmm=rslmmm.getString("U_PASSWORD");
                    }
                    else
                    {
                        LibMenuMM.setVisible(false);
                        JOptionPane.showMessageDialog(null,"SORRY BUT THE USER ID DOES NOT EXIST");
                        LibMenu();
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while alloting the text");
                }
                JLabel lilmmm=new JLabel(strlilmmm);
                JLabel unlmmm=new JLabel(strunlmmm);
                JLabel calmmm=new JLabel(strcalmmm);
                 
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=0;
                LibMenuMMp.add(LIlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=0;
                LibMenuMMp.add(lilmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=1;
                LibMenuMMp.add(UNlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=1;
                LibMenuMMp.add(unlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=2;
                LibMenuMMp.add(PWoldlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=2;
                LibMenuMMp.add(pwoldlmmm,gbclmmm);
                
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=3;
                LibMenuMMp.add(PWnewlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=3;
                LibMenuMMp.add(pwnewlmmm,gbclmmm);
                
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=4;
                LibMenuMMp.add(PWconfirmlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=4;
                LibMenuMMp.add(pwconfirmlmmm,gbclmmm);

                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=5;
                LibMenuMMp.add(Clmmm,gbclmmm);
        
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=5;
                LibMenuMMp.add(calmmm,gbclmmm);
        
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=0;
                gbclmmm.gridy=6;
                LibMenuMMp.add(lmmmchangepassword,gbclmmm);
        
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=1;
                gbclmmm.gridy=6;
                LibMenuMMp.add(lmmmb,gbclmmm);
        
                gbclmmm.insets=new Insets(10,10,10,10);
                gbclmmm.gridx=2;
                gbclmmm.gridy=6;
                LibMenuMMp.add(lmmmso,gbclmmm);
        
                LibMenuMM.add(LibMenuMMp);
        
    }

    public void btnActionLMMMCHANGEPASSWORD()//BUTTON ACTION FOR "CHANGE"
    {
        String newchangepasslibrarianmenu=pwnewlmmm.getText().trim();
        String confirmchangepasslibrarianmenu=pwconfirmlmmm.getText().trim();
        String oldchangepasslibrarianmenu=pwoldlmmm.getText().trim();
        if(oldchangepasslibrarianmenu.equals(strpasslmmm))
        {
            if(newchangepasslibrarianmenu.equals(confirmchangepasslibrarianmenu))
            {
                try
                {
                    rslmmm.updateString("U_PASSWORD",newchangepasslibrarianmenu);
                    rslmmm.updateRow();
                    JOptionPane.showMessageDialog(null,"Password Changed");
                    LibMenuMM.setVisible(false);
                    LibMenu();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while changing");
                    ex.printStackTrace();
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"SORRY BUT YOUR NEW PASSWORD AND CONFIRMED PASSWORD ARE NOT THE SAME");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT OLD NEW PASSWORD AND YOUR ACTUAL PASSWORD ARE NOT THE SAME");
        }
    }
    
    public void LibMenuReportMenu()//DESIGNING THE PAGE FOR CHOICES FOR REPORT MENU FOR LIBRARIAN
    {
        report_menu_choices=new JFrame("Report Menu");
        report_menu_choices.setVisible(true);
        report_menu_choices.setSize(700,500);
        report_menu_choices.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_choices_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_menu_choices=new GridBagConstraints();
        JButton book_search,reader_search,due_report,lending_report,back,sign_out;
        book_search=new JButton        ("  Book Search   ");
        reader_search=new JButton      ("Reader Search ");
        due_report=new JButton         ("    Due Report    ");
        lending_report=new JButton     ("Lending Report");
        back=new JButton               ("         Back         ");
        sign_out=new JButton           ("      Sign  out      ");
        book_search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    LibMenuReportMenuBookSearch();
                }          
            });
        reader_search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    LibMenuReportMenuReaderSearch();
                }          
            });
        due_report.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuReportMenuDueReport();
                }          
            });
        lending_report.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    LibMenuReportMenuLendingReport();
                }          
            });
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    LibMenu();
                }          
            });   
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    main();
                }
            });
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=0;
        report_menu_choices_panel.add(book_search,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=1;
        report_menu_choices_panel.add(reader_search,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=2;
        report_menu_choices_panel.add(due_report,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=3;
        report_menu_choices_panel.add(lending_report,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=4;
        report_menu_choices_panel.add(back,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=5;
        report_menu_choices_panel.add(sign_out,gbc_report_menu_choices);
        report_menu_choices.add(report_menu_choices_panel);
    }
    
    public void LibMenuReportMenuBookSearch()//DESIGNING THE PAGE FOR BOOK SEARCH
    {
        report_menu_book_search=new JFrame("Book Search");
        report_menu_book_search.setVisible(true);
        report_menu_book_search.setSize(700,500);
        report_menu_book_search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_book_search_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_book_search=new GridBagConstraints();
        JButton search,back,sign_out;
        
        search=new JButton("Search");
        back=new JButton("Back");
        sign_out=new JButton("Sign out");
        
        JLabel book_name=new JLabel("Book Name");
        JLabel author=new JLabel("Author");
        JLabel genre=new JLabel("Genre");
        String GENRE_RM[]={"    ","Autobiography","Fiction","Fantasy","Suspense","Romance","Non-fiction","Mythology","Philosophy","Pshycology","Reference Books","Dictionaries and Thesaurus","Others","Combination of genres"};
        
        book_name_tf=new JTextField(20);
        author_tf=new JTextField(10);
        genre_tf=new JComboBox(GENRE_RM);
        
        search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuBookSearchOutput();
                }          
            });
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_book_search.setVisible(false);
                    LibMenuReportMenu();
                }          
            });
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_book_search.setVisible(false);
                    main();
                }
            });
            
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(book_name,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(author,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(genre,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(book_name_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(author_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(genre_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(search,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(back,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(sign_out,gbc_report_book_search);
        report_menu_book_search.add(report_menu_book_search_panel);
    }
        
    public void LibMenuReportMenuBookSearchOutput()//PRINTING THE REPORT FOR BOOK SEARCH
    {
        String bn=book_name_tf.getText().trim();
        String au=author_tf.getText().trim();
        String gn=genre_tf.getSelectedItem().toString().trim();
        int temp=0;
        String sql="";
        if(gn.equals("Combination of genres"))
        {
            JTextField genre_actual=new JTextField(20);
            JPanel genre_actual_panel=new JPanel();
            genre_actual_panel.add(genre_actual);
            int so_genre_actual=JOptionPane.showConfirmDialog(null,genre_actual_panel,"Enter the genres",JOptionPane.OK_CANCEL_OPTION);
            if(so_genre_actual==JOptionPane.OK_OPTION)
            {
                gn=genre_actual.getText().trim();
            }
            else
            {
                temp++;
            }
        }
        if(temp==0)
        {
            if(bn.equals("") && au.equals("") && gn.equals(""))
            {
                sql="select * from BOOK_MST where B_ACTIVE='Yes'";
            }
            else if(gn.equals(""))
            {
                if(au.equals(""))
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_ACTIVE='Yes'";
                }
                else if(bn.equals(""))
                {
                    sql="select * from BOOK_MST where B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
            }
            else if(au.equals(""))
            {
                if(gn.equals(""))
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_ACTIVE='Yes'";
                }
                else if(bn.equals(""))
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
            }
            else if(bn.equals(""))
            {
                if(au.equals(""))
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
                else if(gn.equals(""))
                {
                    sql="select * from BOOK_MST where B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
            }
            else
            {
                sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_AUTHOR='"+au+"' and B_NAME='"+bn+"' and B_ACTIVE='Yes'";
            }
        }
        if(temp==0)
        {
            try
            {
                rs_bs=st.executeQuery(sql);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(temp==0)
        {
            System.out.println("ID \t NAME                                                  AUTHOR                                                PRICE                    CURRENCY       GENRE                                         AVAILABLE       ACTIVE");
        }
            if(temp==0)
        {
            try
            {
                if(rs_bs.next())
                {
                    rs_bs.previous();
                    while(rs_bs.next())
                    {
                        String rs_id=rs_bs.getString("B_ID");
                        String rs_bn=rs_bs.getString("B_NAME");
                        String rs_au=rs_bs.getString("B_AUTHOR");
                        String rs_pr=rs_bs.getString("B_PRICE");
                        String rs_cu=rs_bs.getString("B_CURRENCY");
                        String rs_gn=rs_bs.getString("B_GENRE");
                        String rs_av=rs_bs.getString("B_AVAILABLE");
                        String rs_ac=rs_bs.getString("B_ACTIVE");
                        int len_rs_bn=rs_bn.length();
                        int len_rs_au=rs_au.length();
                        int len_rs_pr=rs_pr.length();
                        int len_rs_gn=rs_gn.length();
                        System.out.print(rs_id+"\t"+rs_bn);
                        for(int a=1;a<=(53-len_rs_bn);a++)
                        {
                            System.out.print(" ");
                        } 
                        System.out.print(rs_au);
                        for(int a=1;a<=(56-len_rs_au);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_pr);
                        for(int a=1;a<=(25-len_rs_pr);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_cu+"            "+rs_gn);
                        for(int a=1;a<=(51-len_rs_gn);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_av+"         "+rs_ac);
                        System.out.println();
                    }
                    rs_bs.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE CHECK THE ENTRIES AS THE DO NOT MATCH TO ANY RECORD IN THE DATABASE");
                    temp++;
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
    
    public void LibMenuReportMenuReaderSearch()//DESIGNING THE PAGE FOR READER SEARCH
    {
        report_menu_reader_search=new JFrame("Reader Search");
        report_menu_reader_search.setVisible(true);
        report_menu_reader_search.setSize(700,500);
        report_menu_reader_search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_reader_search_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_reader_search=new GridBagConstraints();
        JButton search,back,sign_out;
        
        search=new JButton("Search");
        back=new JButton("Back");
        sign_out=new JButton("Sign out");
        
        JLabel first_name=new JLabel("First Name");
        JLabel last_name=new JLabel("Last Name");
        JLabel standard=new JLabel("Standard");

        
        first_name_tf=new JTextField(20);
        last_name_tf=new JTextField(20);
        standard_tf=new JTextField(10);
        
        search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuReaderSearchOutput();
                }          
            });
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_reader_search.setVisible(false);
                    LibMenuReportMenu();
                }          
            });
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_reader_search.setVisible(false);
                    main();
                }
            });
            
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(first_name,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(last_name,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(standard,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(first_name_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(last_name_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(standard_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(search,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(back,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(sign_out,gbc_report_reader_search);
        report_menu_reader_search.add(report_menu_reader_search_panel);
    }
        
    public void LibMenuReportMenuReaderSearchOutput()//PRINTING THE READER SEARCH REPORT
    {
            String fn=first_name_tf.getText().trim();
            String ln=last_name_tf.getText().trim();
            String sta=standard_tf.getText().trim();
            String sql;
            if(fn.equals("") && ln.equals("") && sta.equals(""))
            {
                sql="select * from READER_MST where R_ACTIVE='Yes'";
            }
            else if(fn.equals(""))
            {
                if(ln.equals(""))
                {
                    sql="select * from READER_MST where R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
                else if(sta.equals(""))
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
            }
            else if(ln.equals(""))
            {
                if(fn.equals(""))
                {
                    sql="select * from READER_MST where R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
                else if(sta.equals(""))
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
            }
            else if(sta.equals(""))
            {
                if(fn.equals(""))
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
                else if(ln.equals(""))
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
            }
            else
            {
                sql="select * from READER_MST where R_LNAME='"+ln+"' and R_STANDARD='"+sta+"' and R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
            }
            int temp=0;
            try
            {
                rs_rs=st.executeQuery(sql);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println("ID \t FIRST NAME                                                  LAST NAME                                                  STANDARD                    DIVISION       ROLL NO                    BOOKS WITH HIM/HER       ACTIVE");
            try
            {
                if(rs_rs.next())
                {
                    rs_rs.previous();
                    while(rs_rs.next())
                    {
                        String rs_id=rs_rs.getString("R_ID");
                        String rs_fn=rs_rs.getString("R_FNAME");
                        String rs_ln=rs_rs.getString("R_LNAME");
                        String rs_sta=rs_rs.getString("R_STANDARD");
                        String rs_di=rs_rs.getString("R_DIVISION");
                        String rs_rn=rs_rs.getString("R_ROLLNO");
                        String rs_ridc=rs_rs.getString("RID_COUNT");
                        String rs_ac=rs_rs.getString("R_ACTIVE");
                        int len_rs_fn=rs_fn.length();
                        int len_rs_ln=rs_ln.length();
                        System.out.print(rs_id+"\t"+rs_fn);
                        for(int a=1;a<=(60-len_rs_fn);a++)
                        {
                            System.out.print(" ");
                        } 
                        System.out.print(rs_ln);
                        for(int a=1;a<=(59-len_rs_ln);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_sta+"                            "+rs_di+"               "+rs_rn+"                               "+rs_ridc+"                  "+rs_ac);
                        System.out.println();
                    }
                    rs_rs.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE CHECK THE ENTRIES AS THE DO NOT MATCH TO ANY RECORD IN THE DATABASE");
                    temp++;
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
    }
    
    public void LibMenuReportMenuDueReport()//PRINTING THE DUE REPORT
    {
        try
        {
            String sql="select * from BOOK_TRN";
            rs_dr=st.executeQuery(sql);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("READER ID    BOOK ID    ISSUE DATE        DUE DATE        USER LOGIN(ISSUER)");
        try
        {
                while(rs_dr.next())
                {
                    String rs_rid=rs_dr.getString("R_ID");
                    String rs_bid=rs_dr.getString("B_ID");
                    String rs_id=rs_dr.getString("B_ISSUE_DATE");
                    String rs_dd=rs_dr.getString("B_PLANNED_RETURN_DATE");
                    String rs_ul=rs_dr.getString("U_LOGIN");
                    System.out.print(rs_rid+"            "+rs_bid+"          "+rs_id+"        "+rs_dd+"          "+rs_ul);
                    System.out.println();
                }
                rs_dr.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    
    public void LibMenuReportMenuLendingReport()//PRINTING THE LENDING REPORT
    {
        try
        {
            String sql="select * from B_RTN";
            rs_lr=st.executeQuery(sql);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("READER ID    BOOK ID    ISSUE DATE        DUE DATE        RETURN DATE        USER LOGIN(ISSUER)        USER LOGIN(RECEIVER)");
        try
        {
                while(rs_lr.next())
                {
                    String rs_rid=rs_lr.getString("R_ID");
                    String rs_bid=rs_lr.getString("B_ID");
                    String rs_id=rs_lr.getString("B_ISSUE_DATE");
                    String rs_dd=rs_lr.getString("B_PLANNED_RETURN_DATE");
                    String rs_rd=rs_lr.getString("B_ACTUAL_RETURN_DATE");
                    String rs_ul=rs_lr.getString("U_LOGIN");
                    String rs_ulr=rs_lr.getString("U_LOGIN_RECEIVER");
                    System.out.print(rs_rid+"            "+rs_bid+"          "+rs_id+"        "+rs_dd+"        "+rs_rd+"           "+rs_ul+"              "+rs_ulr);
                    System.out.println();
                }
                rs_lr.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    //SAME AS THAT IN CASE OF LIBRARIAN'S REPORTS
    public void ReportMenu()
    {
        report_menu_choices=new JFrame("CHOICES");
        report_menu_choices.setVisible(true);
        report_menu_choices.setSize(700,500);
        report_menu_choices.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_choices_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_menu_choices=new GridBagConstraints();
        JButton book_search,reader_search,due_report,lending_report,sign_out;
        book_search=new JButton        ("  Book Search   ");
        reader_search=new JButton      ("Reader Search ");
        due_report=new JButton         ("    Due Report    ");
        lending_report=new JButton     ("Lending Report");
        sign_out=new JButton           ("      Sign  out      ");
        book_search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    ReportMenuBookSearch();
                }          
            });
        reader_search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    ReportMenuReaderSearch();
                }          
            });
        due_report.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuDueReport();
                }          
            });
        lending_report.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuLendingReport();
                }          
            });
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_choices.setVisible(false);
                    main();
                }
            });
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=0;
        report_menu_choices_panel.add(book_search,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=1;
        report_menu_choices_panel.add(reader_search,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=2;
        report_menu_choices_panel.add(due_report,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=3;
        report_menu_choices_panel.add(lending_report,gbc_report_menu_choices);
        gbc_report_menu_choices.insets=new Insets(10,10,10,10);
        gbc_report_menu_choices.gridx=0;
        gbc_report_menu_choices.gridy=4;
        report_menu_choices_panel.add(sign_out,gbc_report_menu_choices);
        report_menu_choices.add(report_menu_choices_panel);
    }
    
    public void ReportMenuBookSearch()
    {
        report_menu_book_search=new JFrame("Book Search");
        report_menu_book_search.setVisible(true);
        report_menu_book_search.setSize(700,500);
        report_menu_book_search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_book_search_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_book_search=new GridBagConstraints();
        JButton search,back,sign_out;
        
        search=new JButton("Search");
        back=new JButton("Back");
        sign_out=new JButton("Sign out");
        
        JLabel book_name=new JLabel("Book Name");
        JLabel author=new JLabel("Author");
        JLabel genre=new JLabel("Genre");
        String GENRE_RM[]={"    ","Autobiography","Fiction","Fantasy","Suspense","Romance","Non-fiction","Mythology","Philosophy","Pshycology","Reference Books","Dictionaries and Thesaurus","Others","Combination of genres"};
        
        book_name_tf=new JTextField(20);
        author_tf=new JTextField(10);
        genre_tf=new JComboBox(GENRE_RM);
        
        search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuBookSearchOutput();
                }          
            });
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_book_search.setVisible(false);
                    ReportMenu();
                }          
            });
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_book_search.setVisible(false);
                    main();
                }
            });
            
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(book_name,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(author,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=0;
        report_menu_book_search_panel.add(genre,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(book_name_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(author_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=1;
        report_menu_book_search_panel.add(genre_tf,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=0;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(search,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=1;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(back,gbc_report_book_search);
        gbc_report_book_search.insets=new Insets(10,10,10,10);
        gbc_report_book_search.gridx=2;
        gbc_report_book_search.gridy=2;
        report_menu_book_search_panel.add(sign_out,gbc_report_book_search);
        report_menu_book_search.add(report_menu_book_search_panel);
        }
        
        public void ReportMenuBookSearchOutput()
        {
        String bn=book_name_tf.getText().trim();
        String au=author_tf.getText().trim();
        String gn=genre_tf.getSelectedItem().toString().trim();
        int temp=0;
        String sql="";
        if(gn.equals("Combination of genres"))
        {
            JTextField genre_actual=new JTextField(20);
            JPanel genre_actual_panel=new JPanel();
            genre_actual_panel.add(genre_actual);
            int so_genre_actual=JOptionPane.showConfirmDialog(null,genre_actual_panel,"Enter the genres",JOptionPane.OK_CANCEL_OPTION);
            if(so_genre_actual==JOptionPane.OK_OPTION)
            {
                gn=genre_actual.getText().trim();
            }
            else
            {
                temp++;
            }
        }
        if(temp==0)
        {
            if(bn.equals("") && au.equals("") && gn.equals(""))
            {
                sql="select * from BOOK_MST where B_ACTIVE='Yes'";
            }
            else if(gn.equals(""))
            {
                if(au.equals(""))
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_ACTIVE='Yes'";
                }
                else if(bn.equals(""))
                {
                    sql="select * from BOOK_MST where B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
            }
            else if(au.equals(""))
            {
                if(gn.equals(""))
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_ACTIVE='Yes'";
                }
                else if(bn.equals(""))
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_NAME='"+bn+"' and B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
            }
            else if(bn.equals(""))
            {
                if(au.equals(""))
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_ACTIVE='Yes'";
                }
                else if(gn.equals(""))
                {
                    sql="select * from BOOK_MST where B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_AUTHOR='"+au+"' and B_ACTIVE='Yes'";
                }
            }
            else
            {
                sql="select * from BOOK_MST where B_GENRE='"+gn+"' and B_AUTHOR='"+au+"' and B_NAME='"+bn+"' and B_ACTIVE='Yes'";
            }
        }
        if(temp==0)
        {
            try
            {
                rs_bs=st.executeQuery(sql);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
        }
        if(temp==0)
        {
            System.out.println("ID \t NAME                                                  AUTHOR                                                PRICE                    CURRENCY       GENRE                                         AVAILABLE       ACTIVE");
        }
            if(temp==0)
        {
            try
            {
                if(rs_bs.next())
                {
                    rs_bs.previous();
                    while(rs_bs.next())
                    {
                        String rs_id=rs_bs.getString("B_ID");
                        String rs_bn=rs_bs.getString("B_NAME");
                        String rs_au=rs_bs.getString("B_AUTHOR");
                        String rs_pr=rs_bs.getString("B_PRICE");
                        String rs_cu=rs_bs.getString("B_CURRENCY");
                        String rs_gn=rs_bs.getString("B_GENRE");
                        String rs_av=rs_bs.getString("B_AVAILABLE");
                        String rs_ac=rs_bs.getString("B_ACTIVE");
                        int len_rs_bn=rs_bn.length();
                        int len_rs_au=rs_au.length();
                        int len_rs_pr=rs_pr.length();
                        int len_rs_gn=rs_gn.length();
                        System.out.print(rs_id+"\t"+rs_bn);
                        for(int a=1;a<=(53-len_rs_bn);a++)
                        {
                            System.out.print(" ");
                        } 
                        System.out.print(rs_au);
                        for(int a=1;a<=(56-len_rs_au);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_pr);
                        for(int a=1;a<=(25-len_rs_pr);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_cu+"            "+rs_gn);
                        for(int a=1;a<=(51-len_rs_gn);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_av+"         "+rs_ac);
                        System.out.println();
                    }
                    rs_bs.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE CHECK THE ENTRIES AS THE DO NOT MATCH TO ANY RECORD IN THE DATABASE");
                    temp++;
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }
    
    public void ReportMenuReaderSearch()
    {
        report_menu_reader_search=new JFrame("Reader Search");
        report_menu_reader_search.setVisible(true);
        report_menu_reader_search.setSize(700,500);
        report_menu_reader_search.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel report_menu_reader_search_panel=new JPanel(new GridBagLayout());
        GridBagConstraints gbc_report_reader_search=new GridBagConstraints();
        JButton search,back,sign_out;
        
        search=new JButton("Search");
        back=new JButton("Back");
        sign_out=new JButton("Sign out");
        
        JLabel first_name=new JLabel("First Name");
        JLabel last_name=new JLabel("Last Name");
        JLabel standard=new JLabel("Standard");

        
        first_name_tf=new JTextField(20);
        last_name_tf=new JTextField(20);
        standard_tf=new JTextField(10);
        
        search.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    ReportMenuReaderSearchOutput();
                }          
            });
        back.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_reader_search.setVisible(false);
                    ReportMenu();
                }          
            });
        sign_out.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    report_menu_reader_search.setVisible(false);
                    main();
                }
            });
            
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(first_name,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(last_name,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=0;
        report_menu_reader_search_panel.add(standard,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(first_name_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(last_name_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=1;
        report_menu_reader_search_panel.add(standard_tf,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=0;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(search,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=1;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(back,gbc_report_reader_search);
        gbc_report_reader_search.insets=new Insets(10,10,10,10);
        gbc_report_reader_search.gridx=2;
        gbc_report_reader_search.gridy=2;
        report_menu_reader_search_panel.add(sign_out,gbc_report_reader_search);
        report_menu_reader_search.add(report_menu_reader_search_panel);
    }
        
    public void ReportMenuReaderSearchOutput()
    {
            String fn=first_name_tf.getText().trim();
            String ln=last_name_tf.getText().trim();
            String sta=standard_tf.getText().trim();
            String sql;
            if(fn.equals("") && ln.equals("") && sta.equals(""))
            {
                sql="select * from READER_MST where R_ACTIVE='Yes'";
            }
            else if(fn.equals(""))
            {
                if(ln.equals(""))
                {
                    sql="select * from READER_MST where R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
                else if(sta.equals(""))
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
            }
            else if(ln.equals(""))
            {
                if(fn.equals(""))
                {
                    sql="select * from READER_MST where R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
                else if(sta.equals(""))
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_STANDARD='"+sta+"' and R_ACTIVE='Yes'";
                }
            }
            else if(sta.equals(""))
            {
                if(fn.equals(""))
                {
                    sql="select * from READER_MST where R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
                else if(ln.equals(""))
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
                }
                else
                {
                    sql="select * from READER_MST where R_FNAME='"+fn+"' and R_LNAME='"+ln+"' and R_ACTIVE='Yes'";
                }
            }
            else
            {
                sql="select * from READER_MST where R_LNAME='"+ln+"' and R_STANDARD='"+sta+"' and R_FNAME='"+fn+"' and R_ACTIVE='Yes'";
            }
            int temp=0;
            try
            {
                rs_rs=st.executeQuery(sql);
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
            System.out.println("ID \t FIRST NAME                                                  LAST NAME                                                  STANDARD                    DIVISION       ROLL NO                    BOOKS WITH HIM/HER       ACTIVE");
            try
            {
                if(rs_rs.next())
                {
                    rs_rs.previous();
                    while(rs_rs.next())
                    {
                        String rs_id=rs_rs.getString("R_ID");
                        String rs_fn=rs_rs.getString("R_FNAME");
                        String rs_ln=rs_rs.getString("R_LNAME");
                        String rs_sta=rs_rs.getString("R_STANDARD");
                        String rs_di=rs_rs.getString("R_DIVISION");
                        String rs_rn=rs_rs.getString("R_ROLLNO");
                        String rs_ridc=rs_rs.getString("RID_COUNT");
                        String rs_ac=rs_rs.getString("R_ACTIVE");
                        int len_rs_fn=rs_fn.length();
                        int len_rs_ln=rs_ln.length();
                        System.out.print(rs_id+"\t"+rs_fn);
                        for(int a=1;a<=(60-len_rs_fn);a++)
                        {
                            System.out.print(" ");
                        } 
                        System.out.print(rs_ln);
                        for(int a=1;a<=(59-len_rs_ln);a++)
                        {
                            System.out.print(" ");
                        }
                        System.out.print(rs_sta+"                            "+rs_di+"               "+rs_rn+"                               "+rs_ridc+"                  "+rs_ac);
                        System.out.println();
                    }
                    rs_rs.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"SORRY BUT PLEASE CHECK THE ENTRIES AS THE DO NOT MATCH TO ANY RECORD IN THE DATABASE");
                    temp++;
                }
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
    }
    
    public void ReportMenuDueReport()
    {
        try
        {
            String sql="select * from BOOK_TRN";
            rs_dr=st.executeQuery(sql);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("READER ID    BOOK ID    ISSUE DATE        DUE DATE        USER LOGIN(ISSUER)");
        try
        {
                while(rs_dr.next())
                {
                    String rs_rid=rs_dr.getString("R_ID");
                    String rs_bid=rs_dr.getString("B_ID");
                    String rs_id=rs_dr.getString("B_ISSUE_DATE");
                    String rs_dd=rs_dr.getString("B_PLANNED_RETURN_DATE");
                    String rs_ul=rs_dr.getString("U_LOGIN");
                    System.out.print(rs_rid+"            "+rs_bid+"          "+rs_id+"        "+rs_dd+"          "+rs_ul);
                    System.out.println();
                }
                rs_dr.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    
    public void ReportMenuLendingReport()
    {
        try
        {
            String sql="select * from B_RTN";
            rs_lr=st.executeQuery(sql);
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println("READER ID    BOOK ID    ISSUE DATE        DUE DATE        RETURN DATE        USER LOGIN(ISSUER)        USER LOGIN(RECEIVER)");
        try
        {
                while(rs_lr.next())
                {
                    String rs_rid=rs_lr.getString("R_ID");
                    String rs_bid=rs_lr.getString("B_ID");
                    String rs_id=rs_lr.getString("B_ISSUE_DATE");
                    String rs_dd=rs_lr.getString("B_PLANNED_RETURN_DATE");
                    String rs_rd=rs_lr.getString("B_ACTUAL_RETURN_DATE");
                    String rs_ul=rs_lr.getString("U_LOGIN");
                    String rs_ulr=rs_lr.getString("U_LOGIN_RECEIVER");
                    System.out.print(rs_rid+"            "+rs_bid+"          "+rs_id+"        "+rs_dd+"        "+rs_rd+"           "+rs_ul+"              "+rs_ulr);
                    System.out.println();
                }
                rs_lr.close();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
    }
    
    public void AdminChoice()//DESIGNING THE PAGE FOR ADMIN MENU CHOICES
    {
        final JFrame AC=new JFrame("CHOICES");
        AC.setSize(500,400);
        AC.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AC.setVisible(true);
        JPanel ACP=new JPanel(new GridBagLayout());
        GridBagConstraints gbc=new GridBagConstraints();
        
        JButton NU=new JButton("         New User        ");
        JButton MU=new JButton("      Modify  User     ");
        JButton RU=new JButton("    Remove  User    ");
        JButton CP=new JButton("Change Password");
        JButton SO=new JButton("          Sign Out          ");
        
        NU.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AC.setVisible(false);
                    AdminMenuNewUser();
                }          
            });
        MU.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AC.setVisible(false);
                    AdminMenuModifyUser();
                }          
            });
        RU.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AC.setVisible(false);
                    AdminMenuRemoveUser();
                }          
            });
        CP.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AC.setVisible(false);
                    AdminMenuChangePassword();
                }          
            });
        
        SO.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AC.setVisible(false);
                    main();
                }
            });
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=0;
        ACP.add(NU,gbc);
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=1;
        ACP.add(MU,gbc);
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=2;
        ACP.add(RU,gbc);
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=3;
        ACP.add(CP,gbc);
        
        gbc.insets=new Insets(10,10,10,10);
        gbc.gridx=0;
        gbc.gridy=4;
        ACP.add(SO,gbc);
        
        AC.add(ACP);
    }
    
    public void AdminMenuNewUser()//DESIGNING THE PAGE FOR NEW USER
    {
        AdminMenuNU=new JFrame("New User");
        AdminMenuNU.setVisible(true);
        AdminMenuNU.setSize(700,500);
        AdminMenuNU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel AdminMenuNUp=new JPanel(new GridBagLayout());
        JLabel LInu=new JLabel("Login ID");
        JLabel UNnu=new JLabel("User Name");
        JLabel PWnu=new JLabel("Password");
        JLabel Cnu=new JLabel("Category");
        String categorynu[]={"ADMIN","GENERAL USER","LIBRARIAN"};
        cnu=new JComboBox(categorynu);
        linu=new JTextField(20);
        unnu=new JTextField(20);
        pwnu=new JPasswordField(20);
        JButton amnuadd=new JButton("Add");
        amnuadd.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e)
                {
                    btnActionAMNUADD();
                }
            });
        JButton amnub=new JButton("Back");
        amnub.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AdminMenuNU.setVisible(false);
                    AdminChoice();
                }          
            });
        JButton amnuso=new JButton("Sign out");
        amnuso.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ae)
                {
                    AdminMenuNU.setVisible(false);
                    main();
                }          
            });
        GridBagConstraints gbc1=new GridBagConstraints();
        linu.setText("");
        unnu.setText("");
        pwnu.setText("");
        
        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=0;
        gbc1.gridy=0;
        AdminMenuNUp.add(LInu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=1;
        gbc1.gridy=0;
        AdminMenuNUp.add(linu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=0;
        gbc1.gridy=1;
        AdminMenuNUp.add(UNnu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=1;
        gbc1.gridy=1;
        AdminMenuNUp.add(unnu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=0;
        gbc1.gridy=2;
        AdminMenuNUp.add(PWnu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=1;
        gbc1.gridy=2;
        AdminMenuNUp.add(pwnu,gbc1);

        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=0;
        gbc1.gridy=3;
        AdminMenuNUp.add(Cnu,gbc1);
        
        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=1;
        gbc1.gridy=3;
        AdminMenuNUp.add(cnu,gbc1);
        
        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=0;
        gbc1.gridy=4;
        AdminMenuNUp.add(amnuadd,gbc1);
        
        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=1;
        gbc1.gridy=4;
        AdminMenuNUp.add(amnub,gbc1);
        
        gbc1.insets=new Insets(10,10,10,10);
        gbc1.gridx=2;
        gbc1.gridy=4;
        AdminMenuNUp.add(amnuso,gbc1);
        
        AdminMenuNU.add(AdminMenuNUp);
    }

    public void btnActionAMNUADD()//BUTTON ACTION FOR "ADD" (ADDING IN THE DATABASE)
    {
        try
        {
            String sqlnu="select * from USER_MST";
            rsnu=st.executeQuery(sqlnu);
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while connecting");
        }
        int temporary=1;
        String LoginIdnu=linu.getText().trim();
        String UserNamenu=unnu.getText().trim();
        String PassWordnu=pwnu.getText().trim();
        String Categorynu=cnu.getSelectedItem().toString();
        try
        {
            if(LoginIdnu.equals("") || UserNamenu.equals("") || PassWordnu.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                temporary++;
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while blank textfield check");
        }
        try
        {
            while(temporary==1 && rsnu.next())
            {
                String usertempnu=rsnu.getString("U_LOGIN");
                if(usertempnu.equals(LoginIdnu))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another user with the same Login Id");
                    temporary++;
                }
            }
            while(temporary==1 && rsnu.previous())
            {
                String usertempnu=rsnu.getString("U_LOGIN");
                if(usertempnu.equals(LoginIdnu))
                {
                    JOptionPane.showMessageDialog(null,"Sorry but there is another user with the same Login Id");
                    temporary++;
                }
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while user id");
        }
        try
        {
            if(temporary==1)
            {
                rsnu.moveToInsertRow();
                rsnu.updateString("U_NAME",UserNamenu);
                rsnu.updateString("U_LOGIN",LoginIdnu);
                rsnu.updateString("U_PASSWORD",PassWordnu);
                rsnu.updateString("U_CATEGORY",Categorynu);
                JOptionPane.showMessageDialog(null,"User Added");
                AdminMenuNU.setVisible(false);
                AdminChoice();
                rsnu.updateString("U_ACTIVE","Yes");
                rsnu.insertRow();
                rsnu.close();
                st.close();
                st=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while adding");
        }
    }
    
    public void AdminMenuModifyUser()//TAKING THE LOGIN ID OF THE USER TO BE MODIFIED
    {
        JLabel amlabeluserentrymodify=new JLabel("LOGIN ID:");
        JTextField amuserentrymodify=new JTextField(20);
        JPanel amentrymodify=new JPanel();
        amentrymodify.add(amlabeluserentrymodify);
        amentrymodify.add(amuserentrymodify);
        int muSelectedOption=JOptionPane.showConfirmDialog(null,amentrymodify,"LOGIN",JOptionPane.OK_CANCEL_OPTION);
        if(muSelectedOption==JOptionPane.OK_OPTION)
        {
            amuuserentry=amuserentrymodify.getText().trim();
            btnActionAdminMenuModifyUser();
        }
        else
        {
            AdminChoice();
        }
    }
    
    public void btnActionAdminMenuModifyUser()//DESIGNING THE PAGE FOR MODIFY USER
    {
            int tempmu=0;
            try
            {
                String sqlmu="select * from USER_MST where U_LOGIN='"+amuuserentry+"' and U_ACTIVE='Yes'";
                rsmu=st.executeQuery(sqlmu);
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while connecting");
                AdminMenuModifyUser();
            }
            limu=new JTextField(20);
            unmu=new JTextField(20);
            String PWmu1="";
            try
            {
                if(rsmu.next())
                {
                    limu.setText(rsmu.getString("U_LOGIN"));
                    unmu.setText(rsmu.getString("U_NAME"));
                    PWmu1=rsmu.getString("U_PASSWORD");
                    JOptionPane.showMessageDialog(null,"PLEASE NOTE THAT THE CATEGORY OF THE THE USER BEING SHOWN IS ADMIN, CHANGE IT AS PER REQUIREMENT");
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"SORRY BUT THE USER ID DOES NOT EXIST");
                    tempmu++;
                    AdminChoice();
                }
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while alloting the text");
            }
            if(tempmu==0)
            {
                AdminMenuMU=new JFrame("Modify User");
                AdminMenuMU.setVisible(true);
                AdminMenuMU.setSize(700,500);
                AdminMenuMU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel AdminMenuMUp=new JPanel(new GridBagLayout());
                JLabel LImu=new JLabel("Login ID");
                JLabel UNmu=new JLabel("User Name");
                JLabel PWmu=new JLabel("Password");
                JLabel Cmu=new JLabel("Category");
                String categorymu[]={"ADMIN","GENERAL USER","LIBRARIAN"};
                cmu=new JComboBox(categorymu);
                JButton ammumodify=new JButton("Modify");
                ammumodify.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionAMMUMODIFY();
                    }
                });
                JButton ammub=new JButton("Back");
                ammub.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuMU.setVisible(false);
                        AdminChoice();
                    }          
                });
                JButton ammuso=new JButton("Sign out");
                ammuso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuMU.setVisible(false);
                        main();
                    }          
                });
                GridBagConstraints gbc1=new GridBagConstraints();
                
                int PWmu1len=PWmu1.length();
                char chtempammu='*';
                String PWmu2="";
                for(int temp_in_ammu=0;temp_in_ammu<PWmu1len;temp_in_ammu++)
                {
                    PWmu2=PWmu2+chtempammu;
                }
                JLabel pwmu=new JLabel(PWmu2);
                
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=0;
                AdminMenuMUp.add(LImu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=0;
                AdminMenuMUp.add(limu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=1;
                AdminMenuMUp.add(UNmu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=1;
                AdminMenuMUp.add(unmu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=2;
                AdminMenuMUp.add(PWmu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=2;
                AdminMenuMUp.add(pwmu,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=3;
                AdminMenuMUp.add(Cmu,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=3;
                AdminMenuMUp.add(cmu,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=4;
                AdminMenuMUp.add(ammumodify,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=4;
                AdminMenuMUp.add(ammub,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=2;
                gbc1.gridy=4;
                AdminMenuMUp.add(ammuso,gbc1);
        
                AdminMenuMU.add(AdminMenuMUp);
            }
    }

    public void btnActionAMMUMODIFY()//BUTTON ACTION FOR "MODIFY" (MODIFYING IN THE DATABASE)
    {
        int temporary=1;
        String LoginIdmu=limu.getText().trim();
        String UserNamemu=unmu.getText().trim();
        String Categorymu=cmu.getSelectedItem().toString();
        try
        {
            if(LoginIdmu.equals("") || UserNamemu.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Sorry but one or more Textfields have been left without data");
                temporary++;
            }
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,"Exception while blank textfield check");
        }
        if(temporary==1)
        {
            try
            {
                rsmu.updateString("U_NAME",UserNamemu);
                rsmu.updateString("U_LOGIN",LoginIdmu);
                rsmu.updateString("U_CATEGORY",Categorymu);
                rsmu.updateRow();
                JOptionPane.showMessageDialog(null,"User Modified");
                AdminMenuMU.setVisible(false);
                AdminChoice();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"SORRY BUT THE LOGIN IS THAT OF AN EXISTING USER");
            }
        }
    }
    
    public void AdminMenuRemoveUser()//DESIGNING THE PAGE FOR REMOVE USER
    {
        int tempru=0;
        String aruuserentry="";
        JLabel amlabeluserentryremove=new JLabel("LOGIN ID:");
        JTextField amuserentryremove=new JTextField(20);
        JPanel amentryremove=new JPanel();
        amentryremove.add(amlabeluserentryremove);
        amentryremove.add(amuserentryremove);
        int ruSelectedOption=JOptionPane.showConfirmDialog(null,amentryremove,"LOGIN",JOptionPane.OK_CANCEL_OPTION);
        if(ruSelectedOption==JOptionPane.CANCEL_OPTION)
        {
            AdminChoice();
            tempru++;
        }
        else if(ruSelectedOption==JOptionPane.OK_OPTION)
        {
            aruuserentry=amuserentryremove.getText().trim();
        }
        else
        {
            AdminChoice();
            tempru++;
        }
        if(tempru==0)
        {
                try
                {
                    String sqlru="select * from USER_MST where U_LOGIN='"+aruuserentry+"' and U_ACTIVE='Yes'";
                    rsru=st.executeQuery(sqlru);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while connecting");
                    AdminMenuRemoveUser();
                }
                AdminMenuRU=new JFrame("Remove User");
                AdminMenuRU.setVisible(true);
                AdminMenuRU.setSize(700,500);
                AdminMenuRU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel AdminMenuRUp=new JPanel(new GridBagLayout());
                JLabel LIru=new JLabel("Login ID");
                JLabel UNru=new JLabel("User Name");
                JLabel PWru=new JLabel("Password");
                JLabel Cru=new JLabel("Category");
                JLabel RMru=new JLabel("Remark");
                RMru1=new JTextField(20);
                String strliru="",strunru="",strcaru="";
                String PWru1="";
                JButton amruremove=new JButton("Remove");
                amruremove.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionAMRUREMOVE();
                    }
                });
                JButton amrub=new JButton("Back");
                amrub.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuRU.setVisible(false);
                        AdminChoice();
                    }          
                });
                JButton amruso=new JButton("Sign out");
                amruso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuRU.setVisible(false);
                        main();
                    }          
                });
                GridBagConstraints gbc1=new GridBagConstraints();
                try
                {
                    if(rsru.next())
                    {
                        strliru=rsru.getString("U_LOGIN");
                        strunru=rsru.getString("U_NAME");
                        PWru1=rsru.getString("U_PASSWORD");
                        strcaru=rsru.getString("U_CATEGORY");
                    }
                    else
                    {
                        AdminMenuRU.setVisible(false);
                        JOptionPane.showMessageDialog(null,"SORRY BUT THE USER ID DOES NOT EXIST");
                        AdminChoice();
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while alloting the text");
                }
                int PWru1len=PWru1.length();
                char chtempamru='*';
                String PWru2="";
                for(int temp_in_amru=0;temp_in_amru<PWru1len;temp_in_amru++)
                {
                    PWru2=PWru2+chtempamru;
                }
                JLabel pwru=new JLabel(PWru2);
                JLabel liru=new JLabel(strliru);
                JLabel unru=new JLabel(strunru);
                JLabel caru=new JLabel(strcaru);
                
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=0;
                AdminMenuRUp.add(LIru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=0;
                AdminMenuRUp.add(liru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=1;
                AdminMenuRUp.add(UNru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=1;
                AdminMenuRUp.add(unru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=2;
                AdminMenuRUp.add(PWru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=2;
                AdminMenuRUp.add(pwru,gbc1);

                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=3;
                AdminMenuRUp.add(Cru,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=3;
                AdminMenuRUp.add(caru,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=4;
                AdminMenuRUp.add(RMru,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=4;
                AdminMenuRUp.add(RMru1,gbc1);
                
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=0;
                gbc1.gridy=5;
                AdminMenuRUp.add(amruremove,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=1;
                gbc1.gridy=5;
                AdminMenuRUp.add(amrub,gbc1);
        
                gbc1.insets=new Insets(10,10,10,10);
                gbc1.gridx=2;
                gbc1.gridy=5;
                AdminMenuRUp.add(amruso,gbc1);
        
                AdminMenuRU.add(AdminMenuRUp);

        }
    }

    public void btnActionAMRUREMOVE()//BUTTON ACTION FOR "REMOVE" (REMOVING IN THE DATABASE)
    {
        String remark=RMru1.getText().trim();
        int temporary=0;
        if(remark.equals(""))
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT A REMARK IS REQUIRED AS TO WHY THE USER IS BEING DELETED");
            temporary++;
        }
        if(temporary==0)
        {
            try
            {
                rsru.updateString("U_ACTIVE","No");
                rsru.updateString("U_REMARK",remark);
                rsru.updateRow();
                JOptionPane.showMessageDialog(null,"User Removed");
                AdminMenuRU.setVisible(false);
                AdminChoice();
            }
            catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Exception while removing");
            }
        }
    }
    
    public void AdminMenuChangePassword()//DESIGNING THE PAGE FOR CHANGE PASSWORD
    {
            int tempcp=0;
            String acpuserentry="";
            JLabel amlabeluserentrychangepassword=new JLabel("LOGIN ID:");
            JTextField amuserentrychangepassword=new JTextField(20);
            JPanel amentrychangepassword=new JPanel();
            amentrychangepassword.add(amlabeluserentrychangepassword);
            amentrychangepassword.add(amuserentrychangepassword);
            int cpSelectedOption=JOptionPane.showConfirmDialog(null,amentrychangepassword,"LOGIN",JOptionPane.OK_CANCEL_OPTION);
            if(cpSelectedOption==JOptionPane.CANCEL_OPTION)
            {
                AdminChoice();
                tempcp++;
            }
            else if(cpSelectedOption==JOptionPane.OK_OPTION)
            {
                acpuserentry=amuserentrychangepassword.getText().trim();
            }
            else
            {
                AdminChoice();
                tempcp++;
            }
            if(tempcp==0)
            {
                try
                {
                    String sqlcp="select * from USER_MST where U_LOGIN='"+acpuserentry+"' and U_ACTIVE='Yes'";
                    rscp=st.executeQuery(sqlcp);
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while connecting");
                    AdminMenuChangePassword();
                }
                AdminMenuCP=new JFrame("Change Password");
                AdminMenuCP.setVisible(true);
                AdminMenuCP.setSize(700,500);
                AdminMenuCP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                JPanel AdminMenuCPp=new JPanel(new GridBagLayout());
                JLabel LIcp=new JLabel("Login ID");
                JLabel UNcp=new JLabel("User Name");
                JLabel PWoldcp=new JLabel("Old Password");
                JLabel PWnewcp=new JLabel("New Password");
                JLabel PWconfirmcp=new JLabel("Confirm Password");
                JLabel Ccp=new JLabel("Category");
                pwoldcp=new JPasswordField(20);
                pwnewcp=new JPasswordField(20);
                pwconfirmcp=new JPasswordField(20);
                JButton amcpchangepassword=new JButton("Change");
                amcpchangepassword.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e)
                    {
                        btnActionAMCPCHANGEPASSWORD();
                    }
                });
                JButton amcpb=new JButton("Back");
                amcpb.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuCP.setVisible(false);
                        AdminChoice();
                    }          
                });
                JButton amcpso=new JButton("Sign out");
                amcpso.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent ae)
                    {
                        AdminMenuCP.setVisible(false);
                        main();
                    }          
                });
                GridBagConstraints gbcamcp=new GridBagConstraints();
                String strlicp="",struncp="",strcacp="";
                try
                {
                    if(rscp.next())
                    {
                        strlicp=rscp.getString("U_LOGIN");
                        struncp=rscp.getString("U_NAME");
                        strpwcp=rscp.getString("U_PASSWORD");
                        strcacp=rscp.getString("U_CATEGORY");
                    }
                    else
                    {
                        AdminMenuCP.setVisible(false);
                        JOptionPane.showMessageDialog(null,"SORRY BUT THE USER ID DOES NOT EXIST");
                        AdminChoice();
                    }
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while alloting the text");
                }
                JLabel licp=new JLabel(strlicp);
                JLabel uncp=new JLabel(struncp);
                JLabel cacp=new JLabel(strcacp);
                 
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=0;
                AdminMenuCPp.add(LIcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=0;
                AdminMenuCPp.add(licp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=1;
                AdminMenuCPp.add(UNcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=1;
                AdminMenuCPp.add(uncp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=2;
                AdminMenuCPp.add(PWoldcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=2;
                AdminMenuCPp.add(pwoldcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=3;
                AdminMenuCPp.add(PWnewcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=3;
                AdminMenuCPp.add(pwnewcp,gbcamcp);
                
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=4;
                AdminMenuCPp.add(PWconfirmcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=4;
                AdminMenuCPp.add(pwconfirmcp,gbcamcp);

                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=5;
                AdminMenuCPp.add(Ccp,gbcamcp);
        
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=5;
                AdminMenuCPp.add(cacp,gbcamcp);
        
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=0;
                gbcamcp.gridy=6;
                AdminMenuCPp.add(amcpchangepassword,gbcamcp);
        
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=1;
                gbcamcp.gridy=6;
                AdminMenuCPp.add(amcpb,gbcamcp);
        
                gbcamcp.insets=new Insets(10,10,10,10);
                gbcamcp.gridx=2;
                gbcamcp.gridy=6;
                AdminMenuCPp.add(amcpso,gbcamcp);
        
                AdminMenuCP.add(AdminMenuCPp);

            }
    }

    public void btnActionAMCPCHANGEPASSWORD()//CHANGING THE PASSWORD IN THE DATABASE
    {
        String oldchangepassadminmenu=pwoldcp.getText().trim();
        String newchangepassadminmenu=pwnewcp.getText().trim();
        String confirmchangepassadminmenu=pwconfirmcp.getText().trim();
        if(oldchangepassadminmenu.equals(strpwcp))
        {
            if(newchangepassadminmenu.equals(confirmchangepassadminmenu))
            {
                try
                {
                    rscp.updateString("U_PASSWORD",newchangepassadminmenu);
                    rscp.updateRow();
                    JOptionPane.showMessageDialog(null,"Password Changed");
                    AdminMenuCP.setVisible(false);
                    AdminChoice();
                }
                catch(Exception ex)
                {
                    JOptionPane.showMessageDialog(null,"Exception while changing");
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"SORRY BUT YOUR NEW PASSWORD AND CONFIRM PASSWORD ARE NOT THE SAME");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,"SORRY BUT YOUR OLD PASSWORD AND ACTUAL PASSWORD ARE NOT THE SAME");
        }
    }
    
    public void main()
    {
        new Trial();
    }
}