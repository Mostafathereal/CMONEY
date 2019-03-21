package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;
//import org.ejml.equation.*;

public class feedForward {
	
	public void feedforward(SimpleMatrix a) {
		
		for(int i=0; i<NeuralNet.weights.length; i++) {
			a = dot(a,NeuralNet.weights[i]) + NeuralNet.biases[i];
		}
	}
	
}
