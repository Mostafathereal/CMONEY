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
		
//		for(i = 0; i < 3; i++) {
//			System.out.println("Testing");
//			nw[i].print();
//			nb[i].print();
//		}
		
		
		
	}
//
}
//
//
//		