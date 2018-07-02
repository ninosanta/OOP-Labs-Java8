package schools;

import java.util.ArrayList;
import java.util.Collection;

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
		
	/**
	 * Enumeration of valid {@link Community} types.
	 *
	 */
	public enum Type {
		/**
		 * Mountain community
		 */
		MONTANA, 
		/**
		 * Hill community
		 */
		COLLINARE 
	};
	
	private String name;
	private Type community;
	private Collection<Municipality> municipalities = new ArrayList<>();
	
	
	public Community(String name, Type type) {
		this.name = name;
		this.community = type;
	}
	
	/**
	 * Getter method for the community name
	 * 
	 * @return name of the community
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Getter method for the community type
	 * 
	 * @return type of the community
	 */
	public Type getType(){
		return community;
	}

	/**
	 * Retrieves the municipalities the belong to this community
	 * 
	 * @return collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return municipalities;
	}
	
	
	public void addMunicipality(Municipality m) {
		municipalities.add(m);
	}
	
	
	@Override
	public String toString() {
		return "Community [name=" + name + "]";
	}
	
}
