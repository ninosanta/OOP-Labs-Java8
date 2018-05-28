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
		//TODO: complete
	}
	
	public void setOpen(boolean open){
		this.open = open;
	}
	
	
	@Override
	void simulate(double inFlow) {
		double outFlow = (open ? inFlow : 0);
		System.out.println("Tap "+getName()+":\n\tInput flow = "+inFlow
				+" cubic meters -> Outpupt flow = "
				+outFlow+" cubic meters");
		getOutput().simulate(outFlow);
	}

	@Override
	public String layout(String s) {
		s += ("["+getName()+"]"+"Tap -> ");
		s = getOutput().layout(s);
		return s;
	}

	
	
}
