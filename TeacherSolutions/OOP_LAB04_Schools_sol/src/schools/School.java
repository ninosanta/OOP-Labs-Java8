package schools;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents the schools.
 * Each school has one or more {@link Branch branches}
 *
 */
public class School {
	private String denominazione;
	private String codice;
	private int grado;
	private String descrizione;
	private Collection<Branch> sedi= new LinkedList<>();

	School(String denominazione, String codice, int grado,
			String descrizione) {
		this.denominazione = denominazione;
		this.codice = codice;
		this.grado = grado;
		this.descrizione = descrizione;
	}

	/**
	 * Getter method for the school name
	 * @return name of the school
	 */
	public String getName() {
		return denominazione;
	}

	/**
	 * Getter method for the school code
	 * @return code of the school
	 */
	public String getCode() {
		return codice;
	}

	/**
	 * Getter method for the grade
	 * @return grade
	 */
	public int getGrade() {
		return grado;
	}

	/**
	 * Getter method for the description
	 * @return description
	 */
	public String getDescription() {
		return descrizione;
	}

	/**
	 * Retrieves the branches of the school.
	 * 
	 * @return collection of the branches
	 */
	public Collection<Branch> getBranches() {
		return sedi;
	}

	void addBranch(Branch s) {
		sedi.add(s);
	}

}
