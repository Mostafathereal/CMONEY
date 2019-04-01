import org.ejml.simple.SimpleMatrix;

package CMONEYPackage;

import org.ejml.simple.SimpleMatrix;

public class UpdateMiniBatch {
	
	public void updateMiniBatch(int[][] miniBatch, int learnRate) {
		
		SimpleMatrix[] nb = new SimpleMatrix[this.biases.length];
		SimpleMatrix[] nw = new SimpleMatrix[this.weights.length];
		
		int i = 0;
		for (SimpleMatrix A : this.biases) {
			nb[i] = new SimpleMatrix(A.numRows(), A.numCols());
			i++;
		}
		
		i = 0;
		for (SimpleMatrix A : this.weights) {
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
//
}
//
//
//		