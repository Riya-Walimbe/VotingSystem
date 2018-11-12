import java.sql.SQLException;

/**
 * 
 * @author Gauri Chaudhari
 *
 */
public class InvalidDepositAction extends Exception {

	public InvalidDepositAction(String msg)
	{
		System.out.println(msg);
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
}
