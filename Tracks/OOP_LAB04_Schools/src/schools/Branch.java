package schools;

/**
 * Represents a branch of a {@link School}.
 * For each school there exist one or more branches.
 *
 */
public class Branch {
	
	/**
	 * Getter method for the code
	 * @return code of the branch
	 */
	public int getCode() {
		return -1;
	}
	
	/**
	 * Getter method for the address
	 * @return address of the branch
	 */
	public String getAddress() {
		return null;
	}
	
	/**
	 * Getter method for the CAP (zip code)
	 * @return zip code of the branch
	 */
	public int getCAP() {
		return -1;
	}

	/**
	 * Retrieve the municipality where the branch is located
	 * @return municipality of the branch
	 */
	public Municipality getMunicipality(){
		return null;
	}
	
	/**
	 * Retrieve the school this branch belongs to.
	 * @return school the branch belongs to
	 */
	public School getSchool(){
		return null;
	}

}
