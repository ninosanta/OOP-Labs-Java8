package hydraulic;

/**
 * Represents a tap that can interrupt the flow.
 * 
 * The status of the tap is defined by the method
 * {@link #setOpen(boolean) setOpen()}.
 */

public class Tap extends Element {

	private boolean open;
	
	public Tap(String name) {
		super(name);
	}
	
	public void setOpen(boolean open){
		this.open = open;
	}
	
	@Override
	void simulate(double inFlow) {
		System.out.println("Tap " + getName() + ": in flow=" + inFlow);
		double outFlow = (open?inFlow:0);
		System.out.println(" out flow=" + outFlow);
		getOutput().simulate(outFlow);
		
	}

	@Override
	StringBuffer layout(int indent) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("] Tap ->");
		res.append( getOutput().layout(res.length() + indent) );
		return res;
	}

}
