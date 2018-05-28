package hydraulic;

/**
 * Represents a source of water, i.e. the initial element for the simulation.
 *
 * Lo status of the source is defined through the method
 * {@link #setFlow(double) setFlow()}.
 */
public class Source extends Element {

	private double flow;
	
	public Source(String name) {
		super(name);
	}

	public void setFlow(double flow){
		this.flow = flow;
	}

	@Override
	void simulate(double inFlow) {
		System.out.println("Source " + getName() + ": flow=" + flow);
		
		getOutput().simulate(flow);
		
	}

	@Override
	StringBuffer layout(int indent) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("] Source ->");
		res.append( getOutput().layout(res.length()) );
		return res;
	}
	
}
