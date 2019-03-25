package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;
//import org.ejml.equation.*;

public class feedForward {
	
	public void feedforward(SimpleMatrix a) {
		
		for(int i=0; i<NeuralNet.weights.length; i++) {
			SimpleMatrix dot = dot(a,NeuralNet.weights[i]);
			SimpleMatrix added =  dot.plus(NeuralNet.biases[i]);
			a = Sigmoid.sigmoidFunc(added);
			return a;
			
		}
	}
	
	public SimpleMatrix dot(SimpleMatrix A, SimpleMatrix B) {
		SimpleMatrix result = A.elementMult(B); 
		return result;
	}
	
}
