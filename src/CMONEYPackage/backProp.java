package CMONEYPackage;

public class backProp {
	
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private double[][] w0 = new double[16][784];
	private double[][] w1 = new double[16][16];
	private double[][] w2 = new double[10][16];
	
	private double[] b0 = new double[16];
	private double[] b1 = new double[16];
	private double[] b2 = new double[10];
	
	private double[][] biases;
	private double learnRate;
	
	private double[] act0;
	private double[] act1;
	private double[] act2;
	private double[] act3;
	
	private double[] z1;
	private double[] z2;
	private double[] z3;
	
	/**
	 * Goes through the neural network backwards layer by layer calculating the error of each activation, 
	 * then adjusts the weights and biases based on the cost function derivative to train the neural network.
	 * @param outputs The array of values of the desired activations in the output layer.
	 */
	public void backProp(double[] outputs) {
		
		double[] delOutC = new double[this.act3.length];
		double[] outErr = new double[this.act3.length];
		
		//create cost derivative vector
		for (int i = 0; i < delOutC.length; i ++) {
			delOutC[i] = this.act3[i] - outputs[i];
		}
		
		//calculate error of output layer
		for (int i = 0; i < outErr.length; i ++) {
			outErr[i] = delOutC[i] * Sigmoid.sigmoidDerivative(this.z3[i]);
		}
		
		double[] h2Err = new double[this.act2.length];
		double[][] wt2 = transpose(this.w2);
		double[] intH2 = mult(wt2, outErr);
		
		//calculate error vector of second hidden layer
		for (int i = 0; i < h2Err.length; i ++) {
			h2Err[i] = intH2[i] * Sigmoid.sigmoidDerivative(this.z2[i]);
		}
		
		double[] h1Err = new double[this.act1.length];
		double[][] wt1 = transpose(this.w1);
		double[] intH1 = mult(wt1, h2Err);
		
		//calculate error vector of first hidden layer
		for (int i = 0; i < h1Err.length; i ++) {
			h1Err[i] = intH1[i] * Sigmoid.sigmoidDerivative(this.z1[i]);
		}
		
		
		//update biases in all layers
		for (int i = 0; i < this.b0.length; i++) {
			this.b0[i] += - h1Err[i];
		}
		
		for (int i = 0; i < this.b1.length; i++) {
			this.b1[i] += - h2Err[i];
		}
		
		for (int i = 0; i < this.b2.length; i++) {
			this.b2[i] += - outErr[i];
		}
		
		//update weights
		for (int i = 0; i < this.w0.length; i++) {
			for (int j = 0; j < this.w0[0].length; j++) {
				this.w0[i][j] += - (this.act0[j] * h1Err[i]);
			}
		}
		
		for (int i = 0; i < this.w1.length; i++) {
			for (int j = 0; j < this.w1[0].length; j++) {
				this.w1[i][j] += - (this.act1[j] * h2Err[i]);
			}
		}
		
		for (int i = 0; i < this.w2.length; i++) {
			for (int j = 0; j < this.w2[0].length; j++) {
				this.w2[i][j] += - (this.act2[j] * outErr[i]);
			}
		}
		
	}
	
	/**
	 * Transposes an input matrix.
	 * @param in The matrix to be transposed.
	 * @return Returns the transposed matrix.
	 */
	private double[][] transpose(double[][] in) {
		double[][] out = new double[in.length][in[0].length];
		
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[0].length; j++) {
				out[i][j] = in[j][i];
			}
		}
		
		return out;
	}
	
	/**
	 * Computes the dot product of 2 vectors.
	 * @param a The first vector.
	 * @param b The second vector.
	 * @return Returns the result of the dot product of the 2 input vectors.
	 */
	private double dot(double[] a, double[] b) {
		double result = 0;
		
		for (int i = 0; i < a.length; i++) {
			result += a[i] * b[i];
		}
		
		return result;
	}
	
	/**
	 * Computes matrix multiplication of a matrix and a vector
	 * @param a A 2D matrix
	 * @param b An input vector
	 * @return Returns the vector which is the result of the matrix multiplication of a and b.
	 */
	private double[] mult(double[][] a, double[] b){
		double[] result = new double[a.length];
		
		for (int i = 0; i < result.length; i++) {
			result[i] = dot(a[i], b);
		}
		
		return result;
	}
	
	private static Comparable[] aux;
	
	/**
	 * Checks to see if one Comparable is less than another.
	 * @param v The first Comparable.
	 * @param w The second Comparable.
	 * @return Returns true if Comparable v is less than Comparable w.
	 */
	private static boolean less(Comparable v, Comparable w) {
		return v.compareTo(w) < 0;
	}
	

	private static void merge(Comparable[]a, int lo, int mid, int hi) {
		int i = lo;
		int j = mid + 1;
		
		for (int k = lo; k <= hi; k++) {
			aux[k] = a[k];
		}
		for (int k = lo; k <= hi; k++) {
			if (i > mid) a[k] = aux[j++];
			else if (j > hi)  a[k] = aux[i++];
			else if (less(aux[j], aux[i])) a[k] = aux[j++];
			else a[k] = aux[i++];
		}
		
		
	}
	
	/**
	 * top-down merge sort using Comparable
	 * @param x - the input array containing products that need to be sorted.
	 * @param n - the size of the input array
	 */
	public static void sortMergeTD ( Comparable[] x, int n ) {
		aux = new Comparable[n];
		sortMergeTD(x, 0, n-1);
	}
	
	private static void sortMergeTD( Comparable[] a, int lo, int hi) {
		if (hi <= lo) return;
		int mid = lo + (hi - lo)/2;
		sortMergeTD(a, lo, mid);
		sortMergeTD(a, mid+1, hi);
		merge(a, lo, mid, hi);
	}
	
	private void feedForward(int[] inputs) {
		//input layer
		for(int i=0; i<this.sizes[0]; i++) {
			this.act0[i] = inputs[i];
		}
		
		//hidden layer 1
		for(int i=0; i<this.sizes[1]; i++) {
			this.act1[i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.act1[i] += w0[j][i] * this.act0[j];
			}
			this.z1[i] = this.act1[i];
			this.act1[i] = sigmoid(this.act1[i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.act2[i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.act2[j] += this.w1[j][i] * this.act1[j];
			}
			this.z2[i] = this.act2[i];
			this.act2[i] = sigmoid(this.act2[i]);
		}
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			act3[i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.act3[i] += this.act2[j] * this.w2[j][i];
			}
			this.z3[i] = this.act3[i];
			this.act3[i] = sigmoid(this.act3[i]);
		}
	}

}
