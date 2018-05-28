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
		//TODO: complete
	}
	
	/**
	 * Funzione che definisce la portata di una sorgente
	 * @param flow represents the flow rate in cubic meters
	 */
	public void setFlow(double flow) {
		this.flow = flow;
	}
	
		
	@Override
	void simulate(double inFlow) {
		System.out.println("Source "+getName()+":\n\tOutpupt flow "
				+ "= "+flow+" cubic meters");
		getOutput().simulate(flow);
	}

	@Override
	public String layout(String s) {
		s += ("["+getName()+"]"+"Source -> ");
		s = getOutput().layout(s);
		return s;
	}
	
}
