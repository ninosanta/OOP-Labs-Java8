package hydraulic;

import java.util.Arrays;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 */
public class HSystem {
	
	private static final double DONT_CARE = 0.0;
	private static final int MAX_ELEMENTS = 100;
	private Element[] elements = new Element[MAX_ELEMENTS]; 
	private int nEl = 0;
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elements[nEl++] = elem;
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to the number of added elements
	 */
	public Element[] getElements() {
//		return elements;  // NON SICURO
		
//		Element[] elementsTrue = new Element[nEl];
//		for (int i = 0; i < nEl; i++) {
//			elementsTrue[i] = elements[i];
//		}
//		return elementsTrue;
		/* OPPURE */
		return Arrays.copyOf(elements, nEl);
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout() {
		String s = "";
		for (int i = 0; i < nEl; i++) {
			Element e = elements[i];
			if (e instanceof Source) {
				s += e.layout(s);
				s += "\n";
			}
		}
		return s;
	}
	
	/**
	 * starts the simulation of the system
	 * -> sfrutta il composite pattern
	 */
	public void simulate() {
		for (int i = 0; i < nEl; i++) {
			Element e = elements[i];
			if (e instanceof Source) {  // root
				e.simulate(DONT_CARE);  // overridden
			}
		}
	}

}
