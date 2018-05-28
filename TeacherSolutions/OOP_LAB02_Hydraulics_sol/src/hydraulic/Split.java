package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {

	
	
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name,2);
	}
    
	/**
	 * returns the downstream elements
	 * @return array containing the two downstream elements
	 */
//    public Element[] getOutputs(){
//    		//TODO: complete
//        return null;
//    }
//
//	public void connect(Element elem, int noutput){
//		//TODO: complete
//	}

	@Override
	void simulate(double inFlow) {
		System.out.println("Source " + getName() + ": flow=" + inFlow);
		double outFlow = inFlow/2;
		System.out.println(" : out flow=" + outFlow);
		for(Element e : getOutputs()){
			e.simulate(outFlow);
		}	
	}

	@Override
	StringBuffer layout(int indent) {
		StringBuffer res = new StringBuffer();
		res.append("[").append(getName()).append("] Split +->");
		int l = res.length()+indent-3;
		res.append( getOutputs()[0].layout(res.length() + indent) );
		res.append("\n");
		for(int i=0; i<l; ++i) res.append(" ");
		res.append("|");
		res.append("\n");
		for(int i=0; i<l; ++i) res.append(" ");
		res.append("+->");
		res.append( getOutputs()[1].layout(res.length() + indent) );
		return res;
	}

}
