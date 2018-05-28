package schools;

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
		COLLINARE };
	
	/**
	 * Getter method for the community name
	 * 
	 * @return name of the community
	 */
	public String getName() {
		return null;
	}
	
	/**
	 * Getter method for the community type
	 * 
	 * @return type of the community
	 */
	public Type getType(){
		return null;
	}

	/**
	 * Retrieves the municipalities the belong to this community
	 * 
	 * @return collection of municipalities
	 */
	public Collection<Municipality> getMunicipalities() {
		return null;
	}
}
