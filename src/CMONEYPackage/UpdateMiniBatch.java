package CMONEYPackage;

public class UpdateMiniBatch {
	
	public void updateMiniBatch(int[][] miniBatch, int learnRate) {
		
		double[] nb = new double[this.biases.length];
		double[] nw = new double[this.weights.length];
		
		int i = 0;
		for (double A : this.biases) {
			nb[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		i = 0;
		for (double A : this.weights) {
			nw[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		SimpleMatrix[][] dn = backprop(miniBatch);
		
		i = 0;
		for (SimpleMatrix A : nb) {
			nb[i] = A.plus(dn[0][i]);
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : nw) {
			nw[i] = A.plus(dn[1][i]);
			i++;
		}
		
		double rate = learnRate / miniBatch.length;
		
		i = 0;
		for (SimpleMatrix A : nb) {
			this.biases[i] = A.scale(rate).mult(this.biases[i]);
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : nw) {
			this.weights[i] = A.scale(rate).mult(this.weights[i]);
			i++;
		}
	}
	
	public double[] genOutputs(int num) {
		double[] outputs = new double[10];
		outputs[num] = 1;
		return outputs;
	}
	
	public void backProp(double[] errors) {
		double[] deltaOut = new double[this.sizes[3]];
		
		for (int i = 0; i < this.sizes[3]; i++) {
			deltaOut[i] = dSigmoid(this.activations[3][i]) * errors[i];
		}
		
		double[] deltaH2 = new double[this.sizes[2]];
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0; j < this.sizes[3]; j++) {
				deltaH2[i] = dSigmoid(this.activations[2][i]) * deltaOut[j] * this.weights[2][i][j];
			}
			
		}
		
		double[] deltaH1 = new double[this.sizes[1]];
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0; j < this.sizes[2]; j++) {
				deltaH1[i] = dSigmoid(this.activations[1][i]) * deltaH2[j] * this.weights[1][i][j];
			}
		}
		
		
		for (int i = 0; i < this.sizes[0]; i++) {
			for (int j = 0;) j < this.sizes[1]; j++){
				this.weights[0][i][j] += learnRate * deltaH1[j] * this.activation[0][i];
			}
		}
		
		for (int i = 0; i < this.sizes[1]; i++) {
			for (int j = 0;) j < this.sizes[2]; j++){
				this.weights[1]][i][j] += learnRate * deltaH2[j] * this.activation[1][i];
			}
		}
		
		for (int i = 0; i < this.sizes[2]; i++) {
			for (int j = 0;) j < this.sizes[3]; j++){
				this.weights[2][i][j] += learnRate * deltaOut[j] * this.activation[2][i];
			}
		}
		
	}
//
}
//
//
//		