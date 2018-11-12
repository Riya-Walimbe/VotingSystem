import java.sql.*;
import java.util.Scanner;
/**
 * 
 * @author Gauri Chaudhari
 *
 */
public class FinalCandidateRegisterSuccessful extends CandidateRegister implements Candidate {

	static String firstNamefirstName,gender,nationality,aadhar,mobile_no,qualification,motto;
	static int age,count;
	//connection done to interact with database.
	Connection con = ConnectionProvider.getConnectionwithDB();
	Statement st;
	PreparedStatement pst,pst1,pst2;
	
	public FinalCandidateRegisterSuccessful()
	{
		try
		{
			st = con.createStatement(); 
			pst = con.prepareStatement("insert into Bank values(?,?,?,?,?)");
			pst1 = con.prepareStatement("insert into Candidate(firstName,lastName) values(?,?)");
			pst2 = con.prepareStatement("insert into voting values(?,?,?,?,?,?,?,?,?,'C',?,0)");
			 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	public FinalCandidateRegisterSuccessful(String firstName,String lastName,int age,String gender,String nationality,String aadhar,String mobile_no,String qualification,String motto,String password) {
		this.firstName = firstName;
		System.out.println(firstName);
		this.lastName = lastName;
		this.age = age;
		this.gender = gender;
		this.nationality = nationality;
		this.aadhar = aadhar;
		this.mobile_no = mobile_no;
		this.qualification = qualification;
		this.motto = motto;
		this.password = password;
		FinalCandidateRegisterSuccessful f = new FinalCandidateRegisterSuccessful();
			}
	
	

	public FinalCandidateRegisterSuccessful(int age)
	{
		Checkage(age);
	}
	public void InsertVoting()
	{
		try
		{
		pst2.setString(1,adhaar_no);
		pst2.setString(2, firstName);
		pst2.setString(3, lastName);
		pst2.setInt(4, age);
		pst2.setString(5, gender);
		pst2.setString(6,mobile_no);
		pst2.setString(7, nationality);
		pst2.setString(8,motto);
		pst2.setString(9, qualification);
		pst2.setString(10,this.password);
		pst2.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

	
	public void Checkage(int age) {
		System.out.println(age);
		if(age >= 25 && age <=80)
		{
			System.out.println("User is eligible to stand for elections on basis of age!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		else
		{
			try
			{
				throw new InvalidCandidateAge("Sorry! You need to be 25 or older and below 80 to stand for elections.");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}


		public void CheckNationality(String nationality) {
		if(nationality.equalsIgnoreCase("Indian"))
		{
			System.out.println("User is eligible to stand for elections on basis of nationality!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CheckCandidateCount();
		}
		else
		{
			try
			{
				throw new InvalidNationality("Sorry! You need to be an Indian to stand for elections.");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

	}

	@Override
	public void CheckCandidateCount() {
		//Maximum 3 Candidates can stand for elections on first come first serve basis
		String sql4 = "select count(*) from Candidate";
		
		try {
			count = st.executeUpdate(sql4);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 

		if(count <= 3)
		{
			System.out.println("There is position available for the applying candidate!");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			CandidateDeposit();
		}
		else
		{
			try
			{
				throw new InvalidCandidateCount("Sorry! No entries will be furthur taken.");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
		}

	}
	
	void getBankDetails(String acc_no,double balance)
	{
		try
		{
		pst.setString(1,adhaar_no);
		pst.setString(2,firstName);
		pst.setString(3,lastName);
		pst.setString(4,acc_no);
		pst.setDouble(5,balance);
		pst.executeUpdate();
		System.out.println("Records Inserted in the bank");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

	public void insertData()
	{
		try
		{
			
			pst1.setString(1,firstName);
			pst1.setString(2,lastName);
			pst1.executeUpdate();
			count++;
			Thread.sleep(2000);
			InsertVoting();
			System.out.println("Congratulations "+firstName+" "+lastName+"! You are now standing for the elections!!!");
			HomePage.startData();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	
	@Override
	public void CandidateDeposit() {
		Scanner in = new Scanner(System.in);
		int choice;
		
		System.out.println("We will require your bank details to proceed");
		System.out.println("Please Enter your Account Number(11 digit): ");
		String acc_no = in.next();
		System.out.println("Enter your balance: ");
		double balance = in.nextDouble();
		getBankDetails(acc_no, balance);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("You will have to deposit Rs. 25,000 as a Security Deposit!!");
		String sql = "select balance from Bank where acc_no='"+acc_no+"'";
		ResultSet rs;
		try {
			rs = st.executeQuery(sql);
			rs.next();
			System.out.println("You have Rs."+rs.getDouble(1)+" in your Bank Account.");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		if(balance <= deposit)
		{
			try
			{
				throw new InvalidDepositAction("You do not have enough deposit money in your Bank Account. Sorry You cannot stand for elections");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			try {
				HomePage.startData();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			int i=5;
			do
			{
			System.out.println("Do you want to continue to pay the deposit? Rs. 25,000 will be deducted from your account.");
			System.out.println("Press 1 ==> Proceed with Payment\nPress 2 ==> Terminate the process");
			choice=in.nextInt();
			switch(choice)
			{
			case 1: double amount = balance - deposit;
					try
					{
					String sql1 = "update Bank set balance='"+amount+"' where acc_no='"+acc_no+"'";
					st.executeUpdate(sql1);
					String sql2 = "update Bank set balance=balance+25000 where acc_no=12345678909";
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
			System.out.println("Transaction is completed.You have Rs."+amount+" left in your bank.");
			insertData();
			System.exit(0);
			break;
			case 2: System.out.println("You decided to not pay. Sorry, You cannot stand for elections");	
				try {
					HomePage.startData();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
			default:System.out.println("Please enter either 1 or 2");
					i--;
					System.out.println("You only have "+i+" chances");
					if(i==0)
					{
						break;
					}
			}
			
		}while(i>0);
		}
		
	}	
		
		

		
	}


