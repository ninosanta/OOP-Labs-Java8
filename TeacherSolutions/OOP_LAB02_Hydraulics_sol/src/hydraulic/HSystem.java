package hydraulic;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 * 
 */
public class HSystem {
	private static final int MAX_SIZE = 100;
	private Element[] elements = new Element[MAX_SIZE];
	private int next = 0;
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elements[next++] = elem;
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to 
	 * 							the number of added elements
	 */
	public Element[] getElements(){
		Element[] result = new Element[next];
		//Element[] result=null;
		for(int i =0; i<result.length; ++i){
			result[i] = elements[i];
		}
		return result;
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout(){
		StringBuffer res = new StringBuffer();
		for(Element e : elements){
			if( e != null && e instanceof Source){
				res.append(e.layout(0));
			}
		}
		return res.toString();
	}
	
	/**
	 * starts the simulation of the system
	 */
	public void simulate(){
		for(Element e : elements){
			if( e != null && e instanceof Source){
				e.simulate(-1);
			}
		}
	}

	
	
	
}
