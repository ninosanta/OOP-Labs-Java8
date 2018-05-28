package hydraulic;

/**
 * Represents the sink, i.e. the terminal element of a system
 *
 */
public class Sink extends Element {
	
	/**
	 * Constructor
	 * @param name
	 */
	public Sink(String name) {
		super(name);
		//TODO: complete
	}
	
	@Override
	public void connect(Element elem){
		System.err.println("Operation not allowed on Sink elements");
	}

	@Override
	void simulate(double inFlow) {
		System.out.println("Sink "+getName()+":\n\tInput flow = "
				+inFlow+" cubic meters");
	}

	@Override
	public String layout(String s) {
		s += ("["+getName()+"]"+"Sink |");
		return s;
	}
	
}
