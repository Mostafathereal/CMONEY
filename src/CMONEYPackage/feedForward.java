package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;

public class feedForward {
	
	public SimpleMatrix feedforward(SimpleMatrix a) {
		
		for(int i=0; i<NeuralNet.weights.length; i++) {
			SimpleMatrix dot = dot(a,NeuralNet.weights[i]);
			SimpleMatrix added =  dot.plus(NeuralNet.biases[i]);
			for(int j=0; j<a.numRows(); j++) {
				for(int k=0; k<a.numCols(); k++) {
					double b = added.get(j,k);
					a.set(j, k, Sigmoid.sigmoidFunc(b));
				}
			}
		}
		return a;
	}
	
	public SimpleMatrix dot(SimpleMatrix A, SimpleMatrix B) {
		SimpleMatrix result = A.elementMult(B); 
		return result;
	}
	
}
