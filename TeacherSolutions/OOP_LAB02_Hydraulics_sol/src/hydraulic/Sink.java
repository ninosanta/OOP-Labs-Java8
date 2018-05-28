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
	void simulate(double inFlow) {
		System.out.println("Sink " + getName() + ": flow=" + inFlow);		
	}

	@Override
	StringBuffer layout(int indent) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("] Sink |");
		return res;
	}

}
