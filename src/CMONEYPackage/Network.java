package CMONEYPackage;

import java.lang.Math;

public class Network {
	
	private final int numLayers;
	private final int[] sizes;
	
	
    public Network(int[] sizes){
    	this.numLayers = sizes.length;
    	this.sizes = sizes;
    	
    }
    
    private double sigmoid(int z) {
    	return 1.0/(1.0 + Math.exp(-z));
    }

}