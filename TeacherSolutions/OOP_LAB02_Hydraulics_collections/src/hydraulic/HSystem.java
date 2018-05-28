package hydraulic;

import java.util.ArrayList;
import java.util.List;

/**
 * Main class that act as a container of the elements for
 * the simulation of an hydraulics system 
 */
public class HSystem {
	
	private static final double DONT_CARE = 0.0;
	private List<Element> elements;

	
	/**
	 * costruttore
	 */
	public HSystem() {
		elements = new ArrayList<>();
	}
	
	
	/**
	 * Adds a new element to the system
	 * @param elem
	 */
	public void addElement(Element elem){
		elements.add(elem);
	}
	
	/**
	 * returns the element added so far to the system
	 * @return an array of elements whose length is equal to the number of added elements
	 */
	public Element[] getElements() {
		return elements.toArray(new Element[elements.size()]);  /* cosi' perche' semplicemente elements.toArray
																 * avrebbe ritornato un vettore di Object
																 * questa variante di toArray riceve l'array e lo 
																 * riempe
																 */
	}
	
	/**
	 * Prints the layout of the system starting at each Source
	 */
	public String layout() {
		String s = "";
		for (Element e : elements) {  // liste implementano Iterable -> no problem per foreach
			if (e != null && e instanceof Source) {
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
//		for (Element e : elements) {
//			if (e != null && e instanceof Source) {  // root
//				e.simulate(0.0);  // overridden
//			}
//		}
		// piu' bello con le ArrayList
		elements.forEach(e -> {
			if (e != null && e instanceof Source) {  // root
				e.simulate(DONT_CARE);  // overridden
			}
		});
	}

}
