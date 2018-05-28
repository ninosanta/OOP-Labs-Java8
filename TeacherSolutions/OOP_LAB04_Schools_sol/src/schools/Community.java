package schools;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Represents a community.
 * A community may consist of several {@link Municipality municipalities} belonging to the community.
 * There are two {@link Community.Type types} of community:
 * <ul>
 * <li>{@code Community.Type.MONTANA}: mountain community
 * <li>{@code Community.Type.COLLINARE}: hill community
 * </ul>
 */
public class Community {
	private String nome;
	private Type tipo;
	private Collection<Municipality> comuni = new LinkedList<>();
	
	/**
	 * Enumeration of valid {@link Community} types.
	 *
	 */
	public enum Type { MONTANA, COLLINARE };
	
	Community(String nome, Type tipo) {
		super();
		this.nome = nome;
		this.tipo = tipo;
	}

	/**
	 * Getter method for the community name
	 * 
	 * @return name of the community
	 */
	public String getName() {
		return nome;
	}
	
	
	/**
	 * Getter method for the community type
	 * 
	 * @return type of the community
	 */
	public Type getType(){
		return tipo;
	}

	/**
	 * Retrieves the municipalities the belong to this community
	 * 
	 * @return collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return comuni;
	}
	
	
	void add(Municipality e) {
		comuni.add(e);
	}
	
}
