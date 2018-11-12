/** 
 * 
 * @author Gauri Chaudhari
 *
 */
public interface Candidate {
	public static final double deposit = 25000; 
	//The variable deposit is declared as final as we don't want to change the value of deposit variable anywhere. This is a fixed value.
	//Below are the methods that are defined in FinalCandidateRegisterSuccessful class.
	void Checkage(int age);
	void CheckNationality(String nationality);
	void CheckCandidateCount();
	void CandidateDeposit();
}
