package schools;

/**
 * Represents a branch of a {@link School}.
 * For each school there exist one or more branches.
 *
 */
public class Branch {
	
	private int code;
	private String address;
	private int cap;
	private Municipality municipality;
	private School school;
	
	public Branch(int regionalCode, Municipality municipality, 
			String address, int zipCode, School school) {

		code = regionalCode;
		this.address = address;
		cap = zipCode;
		this.municipality = municipality;
		this.school = school;
	}
	
	/**
	 * Getter method for the code
	 * @return code of the branch
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Getter method for the address
	 * @return address of the branch
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * Getter method for the CAP (zip code)
	 * @return zip code of the branch
	 */
	public int getCAP() {
		return cap;
	}

	/**
	 * Retrieve the municipality where the branch is located
	 * @return municipality of the branch
	 */
	public Municipality getMunicipality(){
		return municipality;
	}

	/**
	 * Retrieve the school this branch belongs to.
	 * @return school the branch belongs to
	 */
	public School getSchool(){
		return school;
	}

}
