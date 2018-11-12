import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * @author Riya Walimbe
 *
 */
public class VoterRegister extends Register implements Reg
{
	static Scanner sc=new Scanner(System.in);
	
	
	public static void getInfo()
	{
		VoterRegister r=new VoterRegister();
		r.getfName();
		r.getlName();
		r.getAge();
		r.getGender();
		r.getNationality();
		r.getAddharCard();
		r.getMobNo();
		r.getPassword();
		r.unp();
		
	}
	
	public void unp()
	{
		System.out.println("Your username is "+firstName);
		System.out.println("Your password is "+password);
	}
	
	public static boolean check(String str)
	{
		char fn[]=str.toCharArray();
		for(int i=0;i<fn.length;i++)
		{
			int j=fn[i];
			if(!((j>=65 && j<=90)||(j>=97 && j<=122)))
			{
				return true;
			}
		
		}
			return false;
		
	}
	
		public String getfName() 
	{
		boolean c=false;
		System.out.println("Enter firstName");
		firstName=sc.next();
		c=check(firstName);
			
		if(c==true)
		{
			System.out.println("Enter valid firstName");
			getfName();
		}
		else
		{
			getlName();
		}
		return firstName;
	}

	@Override
	public String getlName() {
		boolean c=false;
		System.out.println("Enter lastName");
		lastName=sc.next();
		c=check(lastName);
			
		if(c==true)
		{
			System.out.println("Enter valid lastName");
			getlName();
		}
		else
		{
			getAge();
		}
		return lastName;
	}


	public int getAge() {
		try
		{
		System.out.println("Enter age");
		age=sc.nextInt();
		
		if(!(age>=18 && age<=100))
		{
		System.out.println("You are not eligible!! Try after some years!!!!");
		HomePage.startData();
		}
		else
		{
			getGender();
		}
		}catch(Exception e)
		{
			
		}
		return age;
	}

	public void getPassword()
	{
		System.out.println("Enter password");
		password=sc.next();
		
		insertVoterData();
	}
	
	public String getGender() 
	{
		boolean c=false;
		System.out.println("Enter gender");
		gender=sc.next();
		c=check(gender);
		
		if(c==true)
		{
			System.out.println("Enter valid gender");
			getGender();
		}
		else
		{
			getNationality();
		}

		return gender;
		
	}

	@Override
	public String getNationality() 
	{
		boolean c=false;
		System.out.println("Enter nationality");
		nationality=sc.next();
		c=check(nationality);
		
		if(c==true)
		{
			System.out.println("Enter valid nationality");
			getNationality();
		}
		else
		{
			getAddharCard();
		}
		return nationality;
	}

	@Override
	public String getAddharCard() {
		System.out.println("Enter Adhhar_Card number");
		adhaar_no=sc.next();
		if(adhaar_no.length()==12)
		{
			char fn[]=adhaar_no.toCharArray();
			for(int i=0;i<fn.length;i++)
			{
				int j=fn[i];
				if(!(j>=48 && j<=57))
				{
					System.out.println("Enter valid Adhaar-Card number");
					getAddharCard();
				}
			
			}
		}
		else
		{
			System.out.println("Length of Adhaar card should be of 12 digits and must be an Integer!!");
			getAddharCard();
		}
		
			
			getMobNo();
			return adhaar_no;
	}
   		
		
		
		
	

	@Override
	public String getMobNo() 
	{
		int c=0;
		System.out.println("Enter mobile no");
		mobile_no=sc.next();
		
		if(mobile_no.length()==10)
		{
			char fn[]=mobile_no.toCharArray();
			for(int i=0;i<fn.length;i++)
			{
				int j=fn[i];
				if(!(j>=48 && j<=57))
				{
					System.out.println("Enter valid Mobile number");
					getMobNo();
				}
			
			}
		}
		else
		{
			System.out.println("Length of Mobile no. should be of 10 digits and must be an Integer!!");
			getMobNo();
		}
		
		getPassword();
		return mobile_no;
	}


	public void insertVoterData()
	{
		Connection con=ConnectionProvider.getConnectionwithDB();
		try {
			PreparedStatement pst=con.prepareStatement("insert into voting values(?,?,?,?,?,?,?,?,?,?,?,?)");
			pst.setString(1, adhaar_no);
			pst.setString(2, firstName);
			pst.setString(3, lastName);
			pst.setInt(4, age);
			pst.setString(5, gender);
			pst.setString(6, mobile_no);
			pst.setString(7, nationality);
			pst.setString(8, " ");
			pst.setString(9, " ");
			pst.setString(10, "V");
			pst.setString(11, password);
			pst.setInt(12, 0);
			pst.executeUpdate();
			System.out.println("Your information is recorded successfully!!");
			System.out.println();
			System.out.println();
			System.out.println("Your username is "+firstName);
			System.out.println("Your password is "+password);
			HomePage.startData();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
}
