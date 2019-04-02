package CMONEYPackage;

//import org.ejml.simple.SimpleMatrix;

public class feedForward {
	
	
	public void feedForward(int[] inputs) {
		//input layer
		for(int i=0; i<this.sizes[0]; i++) {
			this.activations[0][i] = inputs[i];
		}
		
		//hidden layer 1
		for(int i=0; i<this.sizes[1]; i++) {
			this.activations[1][i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.activations[1][j] += weights[0][j][i] * this.activations[0][j];
			}
			this.activations[1][i] = sigmoid(this.activations[1][i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.activations[2][i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.activations[2][j] += this.weights[1][j][i] * this.activations[1][j];
			}
			this.activations[2][i] = sigmoid(this.activations[2][i]);
		}
		
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			activations[3][i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.activations[3][i] += this.activations[2][j] * this.weights[2][j][i];
			}
			this.activations[3][i] = sigmoid(this.activations[3[i]]);
		}
	}
	
	
	/*public SimpleMatrix feedforward(SimpleMatrix a) {
		
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
	}*/
	
}
