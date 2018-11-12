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
 * @author Kiran Karpe
 *
 */
public class VoterOption {
	 static Connection con=ConnectionProvider.getConnectionwithDB();

	public static String voterProfile(String name) throws SQLException {
		
		String sql = "select * from voting where firstname='"+name+"'";

		Statement st = con.createStatement();
		ResultSet rs=st.executeQuery(sql);
		ResultSetMetaData rsmd=rs.getMetaData();	
		
		while(rs.next()) {
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print("\t"+rs.getString(i));
			}
		}
		VoterLogin.showMenu(name);
		return name;
	}
	
	public static String candidateInfo(String name) throws SQLException {
		
		String sql1 = "select firstName,lastName,qualification,motto from voting where field='C'";
		Statement st=con.createStatement();
		ResultSet rs=st.executeQuery(sql1);
		ResultSetMetaData rsmd=rs.getMetaData();
		
		while(rs.next()) {
			for(int i=1;i<=rsmd.getColumnCount();i++) {
				System.out.print("\t"+rs.getString(i));
			}
			System.out.println();
		}
		VoterLogin.showMenu(name);
		return name;
	}
	
	}

