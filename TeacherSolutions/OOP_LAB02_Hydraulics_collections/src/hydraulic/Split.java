package hydraulic;

/**
 * Represents a split element, a.k.a. T element
 * 
 * During the simulation each downstream element will
 * receive a stream that is half the input stream of the split.
 */

public class Split extends Element {
	
	private static final int MAX_OUTPUTS = 2;
	private Element[] outputs = new Element[MAX_OUTPUTS];
		
	/**
	 * Constructor
	 * @param name
	 */
	public Split(String name) {
		super(name);
		//TODO: complete
	}
    
    public void connect(Element elem, int noutput){
    	outputs[noutput] = elem;
	}
    
    /**
	 * returns the downstream elements
	 * @return array containing the two downstream element
	 */
    public Element[] getOutputs(){
    	return outputs;
    }
    

	@Override
	void simulate(double inFlow) {
		System.out.println("Split "+getName()+":\n\tInput flow = "+inFlow
				+" cubic meters -> Total outpupt flow = "
				+inFlow+" cubic meters");
		getOutputs()[0].simulate(inFlow/2);
		getOutputs()[1].simulate(inFlow/2);
	}

	@Override
	public String layout(String s) {
		s += ("["+getName()+"]"+"Split +-> ");
		int len = s.length()-4;
		s = outputs[0].layout(s);
		s += "\n";
		for (int i = 0; i < len; i++)
			s += " ";
		s += ("|\n");
		for (int i = 0; i < len; i++)
			s += " ";
		s += ("+-> ");
		s = outputs[1].layout(s);
		return s;
	}

}
