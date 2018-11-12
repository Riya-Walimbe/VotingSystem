import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
/**
 * 
 * @author Riya Walimbe
 *
 */
public class AdminList {

	 static Connection con=ConnectionProvider.getConnectionwithDB();
	 static Scanner sc=new Scanner(System.in);
	 
	 public static void passwordCheck()
	 {
	
		 System.out.println("Enter username");
		 String un=sc.next();
		 System.out.println("Enter the password");
		 String pwd=sc.next();
		 
		 if(un.equals("admin") && pwd.equals("1234"))
		 {
			 showMenuAdmin();
		 }
		 else
		 {
			 System.out.println("User-Name or Password is incorrect!!");
			 passwordCheck();
		 }
		 
	 }
public static void voterShow() throws SQLException {
		
		String sql = "select firstName,flag from voting";

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		ResultSetMetaData rsmd=rs.getMetaData();	
		System.out.println("\t\t"+"Name"+"      "+"Voting Status(0->No Vote and 1->Voted)");
		while(rs.next()) {
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print("\t\t"+rs.getString(i));
				
			}
			System.out.println();
		}
	showMenuAdmin();
	}

public static void candidateVotes()
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		String sql1="select * from candidate";
		ResultSet rs=stmt.executeQuery(sql1);
		ResultSetMetaData rsmd1=rs.getMetaData();
		System.out.println("\t"+"Name"+"\t\t"+"Votes");
		while(rs.next())
		{
			 for(int i=1;i<=rsmd1.getColumnCount();i++)
				{
			                
			       System.out.print("\t"+rs.getString(i));
				}
				System.out.println();
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	showMenuAdmin();
}

public static void winner()
{
	Statement stmt;
	try {
		stmt = con.createStatement();
		String sql1="select * from candidate where votes=(select max(votes) from candidate)";
		ResultSet rs=stmt.executeQuery(sql1);
		if(rs.next())
		{
			System.out.println("********Winner********");
			System.out.println(rs.getString("firstName")+" with "+rs.getString("votes")+" votes!!!!");
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	showMenuAdmin();
}

public static void showMenuAdmin()
{
	System.out.println("\n*****OFFICER MENU****\n1.Show Voting Details\n2.Show Candidate Votes\n3.Winner\n4.Exit");
	System.out.println("\nEnter choice for operation");
	int ch=sc.nextInt();
	do
	{
	switch(ch)
	{
	case 1:try {
			voterShow();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		break;
	case 2:candidateVotes();
		break;
	case 3:winner();
		break;
	case 4:try {
			HomePage.startData();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		break;
		
	default:System.out.println("Enter the proper choice");
	}
	}while(ch==4);

}

}
