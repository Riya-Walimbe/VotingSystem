import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class VoterLogin
{
	 static Connection con=ConnectionProvider.getConnectionwithDB();
		
	public static void showMenu(String name) throws SQLException
	{
		
		Scanner sc=new Scanner(System.in);
		
		System.out.println("\n1.User Profile\n2.Candidate Information\n3.Vote\n4.Exit");
		System.out.println("Enter option: ");
		int ch = sc.nextInt();

		switch (ch) {
				
		case 1:System.out.print("\t"+"Adhaar No."+"   "+"firstName"+"   "+"lastName"+"   "+"age"+"   "+"gender"+"   "+"mobile_no"+"   "+"nationality"+"   "+"Voter/Candidate"+"   "+"password"+"   "+"Voting status"+"\n");
			VoterOption.voterProfile(name);
			break;

		case 2:System.out.print("\t"+"firstname"+" "+"lastname"+" "+"qualification"+" "+"motto"+"\n");
			   VoterOption.candidateInfo(name);
			break;

		case 3:Voting.voting(name);
			   break;
		
			
		case 4: try {
				HomePage.startData();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;

		default:
			System.out.println("Invalid choice");
			break;
		}
	
	}
	

	

	
	public static void getLoginData() throws ClassNotFoundException, SQLException,NullPointerException
	{
	
	try
    {
    
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the username");
		String un=sc.next();
		System.out.println("Enter password");
		String pw=sc.next();
		
		
		
    ResultSet rs;
    String pp;
    PreparedStatement ps=con.prepareStatement("select * from voting where firstname=? and password=?");
	ps.setString(1,un);
	ps.setString(2,pw);
    rs=ps.executeQuery();//records with condition are fetched
    
       if(rs.next())//if record is fetched in the result set
       {
    	  pp=rs.getString("firstname");
           System.out.println("Login successfull!!");
        	showMenu(pp);
       }

       else
       {
          System.out.println("Login failed!!");
          HomePage.Login();
       }

    }catch (Exception e)
	{
    	e.printStackTrace();
	}

	}
}
