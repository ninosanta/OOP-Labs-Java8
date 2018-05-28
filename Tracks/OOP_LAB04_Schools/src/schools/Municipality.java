package schools;

import java.util.Collection;
import java.util.Optional;

/**
 * Represents a municipality.
 * A municipality may belong to a {@link Community}
 *
 */

public class Municipality {

	/**
	 * Getter method for the municipality's name
	 * 
	 * @return name of the municipality
	 */
	public String getName() {
		return null;	
	}
	
	/**
	 * Getter method for province where the municipality lies
	 * 
	 * @return province of the municipality
	 */
	public String getProvince() {
		return null;
	}

	/**
	 * Retrieves the community the municipality belongs to as an {@link Optional}
	 * If the municipality the optional will be empty.
	 * 
	 * @return optional community
	 */
	public Optional<Community> getCommunity() {
		return null;
	}	
	
	/**
	 * Retrieves all the school branches located in the municipality 
	 * 
	 * @return collection of branches
	 */
	public Collection<Branch> getBranches() {
		return null;
	}

}
