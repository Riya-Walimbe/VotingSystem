import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * @author Riya Walimbe
 *
 */
public class HomePage {

	static Scanner sc=new Scanner(System.in);
	
	public static void startData() throws SQLException, ClassNotFoundException
	{	
		int ch;
		System.out.println();
		System.out.println();
		System.out.println("******WELCOME TO VOTING SYSTEM******");
		System.out.println("******MENU*****\n1.REGISTER\n2.LOGIN\n3.OFFICER LOGIN\n4.EXIT");
		System.out.println("Enter the choice for operation");
		ch=sc.nextInt();
		
		do
		{
		switch(ch)
			{
			case 1:HomePage.Register();
			  break;
			case 2:HomePage.Login();
			  break;
			case 3:AdminList.passwordCheck();
			break;
			case 4:System.exit(0);
			  break;
			    
	    default:System.out.println("Please enter the correct choice!!");
	    
			}
		break;
		}while(ch!=3);
		
	}
	
	public static void Register() throws SQLException, ClassNotFoundException
	{
		int ch;
		System.out.println("\n\n******MENU*****\n1.CANDIDATE REGISTER\n2.VOTER REGISTER\n3.EXIT\n\n");
		System.out.println("Enter the choice for operation");
		ch=sc.nextInt();
		
		do
		{
			switch(ch)
			{
			case 1:CandidateRegister.getInfo();
			break;
			
			case 2:VoterRegister.getInfo();
			break;
	
			case 3:HomePage.startData();
			break;
			
			default:System.out.println("Please enter the correct choice!!");
	    
			}
		
		
		}while(ch!=3);
	
	}
	
	public static void Login() throws SQLException, ClassNotFoundException
	{
		int ch;
		System.out.println("******MENU*****\n1.LOGIN TO VOTE\n2.EXIT");
		System.out.println("Enter the choice for operation");
		ch=sc.nextInt();
		
		do
		{
			switch(ch)
			{
			
			case 1:new VoterLogin().getLoginData();
			break;
	
			case 2:HomePage.startData();
			break;
			
			default:System.out.println("Please enter the correct choice!!");
	    
			}
		
		
		}while(ch!=2);
	
	}


}
