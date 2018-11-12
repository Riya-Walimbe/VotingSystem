import java.sql.SQLException;
import java.util.Scanner;
/**
 * 
 * @author Riya Walimbe
 *
 */
public class CandidateRegister extends Register implements Reg
{
	
	

	static Scanner sc=new Scanner(System.in);
	
	public static void getInfo()
	{
		CandidateRegister r=new CandidateRegister();
		String firstName = r.getfName();
		String lastName = r.getlName();
		int age = r.getAge();
		String gender = r.getGender();
		String nationality = r.getNationality();
		String aadhar = r.getAddharCard();
		String mobile_no = r.getMobNo();
		String qualification = r.getQualification();
		String motto = r.getMotto();
		String password = r.getPassword();
	
		r = new FinalCandidateRegisterSuccessful(firstName,lastName,age,gender,nationality,aadhar,mobile_no,qualification,motto,password);
		
		
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
	
	public String getPassword()
	{
		System.out.println("Enter password");
		password=sc.next();
		
		return password;
	}
	
	public void unp()
	{
		System.out.println("Your username is "+firstName);
		System.out.println("Your password is "+password);
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
		System.out.println("Enter age");
		age=sc.nextInt();
		
		if(!(age>=25 && age<=100))
		{
		//System.out.println("You are not eligible!! Try after some years!!!!");
			
		new FinalCandidateRegisterSuccessful(age);
		try {
			HomePage.startData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		else
		{
			getGender();
		}
		return age;
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
		
			
			getQualification();
			return mobile_no;
	}

	public String getQualification()
	{
		String qualification;
		boolean c=false;
		System.out.println("Enter Qualification");
		qualification=sc.next();
		c=check(qualification);
		
		if(c==true)
		{
			System.out.println("Enter valid lastName");
			getQualification();
		}
		else
		{
			getMotto();
		}
		return qualification;
	}


	public String getMotto()
	{
		String motto;
		boolean c=false;
		System.out.println("Enter motto");
		motto=sc.next();
		c=check(motto);
		
		if(c==true)
		{
			System.out.println("Enter valid motto");
			getMotto();
		}
		else
		{
			getPassword();
			unp();
		
			new FinalCandidateRegisterSuccessful().CheckNationality(nationality);
		}
		return motto;
	}

	
}
