package schools;

/**
 * Represents a branch of a {@link School}.
 * For each school there exist one or more branches.
 *
 */
public class Branch {
	private int codice;
	private String indirizzo;
	private int CAP;
	private Municipality comune;
	private School scuola;
	
	Branch(int codice, String indirizzo, int cap,
				  Municipality comune, School scuola) {
		super();
		this.codice = codice;
		this.indirizzo = indirizzo;
		CAP = cap;
		this.comune = comune;
		this.scuola = scuola;
	}
	
	/**
	 * Getter method for the code
	 * @return code of the branch
	 */
	public int getCode() {
		return codice;
	}

	/**
	 * Getter method for the address
	 * @return address of the branch
	 */
	public String getAddress() {
		return indirizzo;
	}
	
	/**
	 * Getter method for the CAP (zip code)
	 * @return zip code of the branch
	 */
	public int getCAP() {
		return CAP;
	}

	/**
	 * Retrieve the municipality where the branch is located
	 * @return municipality of the branch
	 */
	public Municipality getMunicipality(){
		return comune;
	}

	/**
	 * Retrieve the school this branch belongs to.
	 * @return school the branch belongs to
	 */
	public School getSchool(){
		return scuola;
	}

}
