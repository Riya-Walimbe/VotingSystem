//import java.lang.Math;
//import java.sql.*;
///**
// * 
// * @author Gauri Chaudhari
// *
// */
//public class CheckVoters {
//	static int totalVotes;
//	static int minimumVotes;
//	 int CandidateFirstTotal;
//	 int CandidateSecondTotal;
//	 int CandidateThirdTotal;
//	 Connection con;
//	 Statement st;
//	 String[] CandidateFirstName = new String[3];
//	 String[] CandidateSecondName = new String[3];
//	 int[] CandidateVotes = new int[3];
//	 
//	 public CheckVoters() throws SQLException
//	 {
//		 con = ConnectionProvider.getConnectionWithDB();
//		 st = con.createStatement();
//		 
//	 }
//	 public static void message() throws InterruptedException
//		{
//			System.out.println("You have legally lost your security deposit!!");
//			Thread.sleep(2000);
//			System.out.println("Transferring money to admin's account!!!");
//		}
//	 
//	 public void getCandidateVotes() throws SQLException, InterruptedException
//	 {
//		 String sql1 = "select * from Candidate order by votes desc";
//		 ResultSet rs = st.executeQuery(sql1);
//		 String sql2 = "select sum(votes) from Candidate";
//		 ResultSet votes = st.executeQuery(sql2);
//		 while(votes.next())
//		 {
//			 totalVotes=votes.getInt(1);
//		 }
//		 ResultSetMetaData rsmd = rs.getMetaData();
//		 while(rs.next())
//		 {
//			 for(int i=0;i<3;i++)
//			 {
//			 CandidateFirstName[i]=rs.getString(1);
//			 CandidateSecondName[i]=rs.getString(2);
//			 CandidateVotes[i]=rs.getInt(3);
//			 }
//		 }
//		 
//		 checkVotes(totalVotes,CandidateVotes);
//	 }
//	 public void redeemMoney(int CandidateVote) throws SQLException
//	 {
//		 String sql = "select firstName,lastName from Candidate where votes="+CandidateVote;
//		 ResultSet rs = st.executeQuery(sql);
//		 while(rs.next())
//		 {
//			 System.out.println(rs.getString("firstName")+"\t"+rs.getString("lastName"));
//		 }
//	 }
//	public void checkVotes(int total,int CandidateVotes[]) throws InterruptedException, SQLException
//	{
//		minimumVotes= Math.round(total/6);
//		System.out.println("The minimum required votes should be 1/6th of total votes");
//		System.out.println("Minimum votes should be "+minimumVotes);
//		Thread.sleep(1000);
//	
//	
//		
//		CheckVoters c = new CheckVoters();
//		if((Math.min(CandidateVotes[2],minimumVotes)) == minimumVotes)
//		{
//			message();
//			c.redeemMoney(CandidateVotes[2]);
//		}
//		
//		if((Math.min(CandidateVotes[3],minimumVotes)) == minimumVotes)
//		{
//			message();
//			c.redeemMoney(CandidateVotes[3]);
//		}
//		
//		
//				
//		
//		
//	}
//
//	public static void main(String[] args) throws SQLException, InterruptedException {
//		CheckVoters c = new CheckVoters();
//		c.getCandidateVotes();
//		//checkVotes(total,CandidateFirstVotes,CandidateSecondVotes,CandidateThirdVotes);
//		
//		
//	}
//}
