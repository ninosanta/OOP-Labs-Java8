package schools;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

/**
 * Represents a municipality.
 * A municipality may belong to a {@link Community}
 *
 */
public class Municipality {
	private String nome;
	private String provincia;
	private Collection<Branch> sedi= new LinkedList<>();
	private Community comunita;
	
	public Municipality(String nome, String provincia, Community comunita) {
		super();
		this.nome = nome;
		this.provincia = provincia;
		this.comunita = comunita;
	}
	
	/**
	 * Getter method for the municipality's name
	 * 
	 * @return name of the municipality
	 */
	public String getName() {
		return nome;
	}
	
	/**
	 * Getter method for province where the municipality lies
	 * 
	 * @return province of the municipality
	 */
	public String getProvince() {
		return provincia;
	}

	/**
	 * Retrieves the community the municipality belongs to as an {@link Optional}
	 * If the municipality the optional will be empty.
	 * 
	 * @return optional community
	 */
	public Optional<Community> getCommunity() {
		return Optional.ofNullable(comunita);
	}	
	
	/**
	 * Retrieves all the school branches located in the municipality 
	 * 
	 * @return collection of branches
	 */
	public Collection<Branch> getBranches() {
		return sedi;
	}
	
	void addBranch(Branch s) {
		sedi.add(s);
	}
	
}
