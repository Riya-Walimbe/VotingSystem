
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * 
 * @author Riya Walimbe
 * @author Shilpa Khairnar
 *
 */
public class Voting {
static Connection con=ConnectionProvider.getConnectionwithDB();
static ResultSet rs;



static Scanner sc=new Scanner(System.in);
public static void voting(String name) throws SQLException {
        //get aadhar no from user
	
        String check_aadhar = null;  
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your Aadhar Number :");
        String aadhar=sc.next();
        String query="select * from voting where adhaar_no='"+aadhar+"' and  firstname='"+name+"'";
        //String query="Select * from voting where adhaar_no='"+aadhar+"';";
        Statement stmt=con.createStatement();
		ResultSet rs =stmt.executeQuery(query);
        if(rs.next())
        {
           check_aadhar=rs.getString("adhaar_no");
        }
       
        //Print candidate names!
        	if (aadhar.equalsIgnoreCase(check_aadhar)) {

            int f = 0;        //change status to voted!
		String sql7="select * from voting where firstname='"+name+"'";
		ResultSet rs2=stmt.executeQuery(sql7);
		if(rs2.next())
		{
			f=rs2.getInt("flag");
		}
		String c;
		if(f==0)
		{
		do {
			String sql2 = "select firstName,lastName from Candidate ";
	        ResultSet rs1=stmt.executeQuery(sql2);
	        ResultSetMetaData rsmd1=rs1.getMetaData();
		
	        String[] candidate=new String[5];
	        int count=0;
	        int j=1;
	        while(rs1.next()) 
	        {	
	        	System.out.print(j);
	            for(int i=1;i<=rsmd1.getColumnCount();i++)
		{
	                candidate[count]=rs1.getString(1);
	                System.out.print("\t"+rs1.getString(i));
		}
		System.out.println();
	            count++;
	            j++;
	        }


			System.out.println("enter your choice :");
			int ch=sc.nextInt();
			int cnt=1;
			int cnt1=1;
			int cnt2=1;
			
			switch (ch) {
			case 1:
				String sql16="select * from candidate where firstName='"+candidate[0]+"'";
				ResultSet rs114=stmt.executeQuery(sql16);
				if(rs114.next())
				{
				cnt=rs114.getInt("votes");
				cnt=cnt+1;
				}
				String sql3 = "Update Candidate set votes='"+cnt+"' where firstName='"+candidate[0]+"'";
				stmt.executeUpdate(sql3);
				
				break;
			case 2:
				String sql14="select * from candidate where firstName='"+candidate[1]+"'";
				ResultSet rs111=stmt.executeQuery(sql14);
				if(rs111.next())
				{
				cnt1=rs111.getInt("votes");
				cnt1=cnt1+1;
				}
				String sql4 = "Update Candidate set votes='"+cnt1+"' where firstName='"+candidate[1]+"'";
				
				stmt.executeUpdate(sql4);
				break;
			case 3:
				String sql15="select * from candidate where firstName='"+candidate[2]+"'";
				ResultSet rs112=stmt.executeQuery(sql15);
				if(rs112.next())
				{
				cnt2=rs112.getInt("votes");
				cnt2=cnt2+1;
				}
				String sql5 = "Update Candidate set votes='"+cnt2+"' where firstName='"+candidate[2]+"'";
				stmt.executeUpdate(sql5);
				break;
			default:
				System.out.println("Inappropriate Choice..");
			}

			if (ch>=1 && ch<=3) {
				String sql6 = "Update voting set flag=1 where adhaar_no='"+check_aadhar+"'";
				stmt.executeUpdate(sql6);
				System.out.println("Thanks for Voting..");
				
				try {
					HomePage.startData();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else {
				System.out.println("For vote properly enter(v)");
			}
			c=sc.next();
		}while(!c.equalsIgnoreCase("e"));
		
		}
		else
		{
			System.out.println("You have already been voted!!");
			try {
				HomePage.startData();
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			}
		}
	} else {
		System.out.println("Aadhar Number does not match with userName!! Please try again!!");
		VoterLogin.showMenu(name);
	}
	}
}
