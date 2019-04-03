package CMONEYPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringTokenizer;


public class NewNetwork {
	
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private double[][] w0 = new double[16][784];
	private double[][] w1 = new double[16][16];
	private double[][] w2 = new double[10][16];
	private double[][] dw0 = new double[16][784];
	private double[][] dw1 = new double[16][16];
	private double[][] dw2 = new double[10][16];
	
	Random rand = new Random(System.currentTimeMillis());
	
	private double[] b0 = new double[16];
	private double[] b1 = new double[16];
	private double[] b2 = new double[10];
	private double[] db0 = new double[16];
	private double[] db1 = new double[16];
	private double[] db2 = new double[10];
	
	private double[] z1 = new double[16];
	private double[] z2 = new double[16];
	private double[] z3 = new double[10];
	
	private double learnRate;
	private int batchSize;
	
	public double[] act0;
	private double[] act1;
	private double[] act2;
	public double[] act3;
	
	
	public  NewNetwork() {
		this.learnRate = 0.1;
		this.batchSize = 10;
		
		act0 = new double[784];
		act1 = new double[16];
		act2 = new double[16];
		act3 = new double[10];
		
	}
	
	public int evaluate(int inputs[]) {
		feedForward(inputs);
		double maxVal = act3[0];
		int maxInd = 0;
		for(int i = 1; i < 10; i++){
			if (act3[i] > maxVal){
				maxVal = act3[i];
				maxInd = i;
			}
		}
		
		for (int i = 0; i < 10; i ++) {
			System.out.println(this.act3[i]);
		}
		
		return maxInd;
	}
	
	
	
	public void setRandWeights() {
		for(int i = 0; i < this.w0.length; i++) {
			for(int j = 0; j < this.w0[i].length; j++) {
				this.w0[i][j] = randRange(-1, 1);
			}
		}
		
		for(int i = 0; i < this.w1.length; i++) {
			for(int j = 0; j < this.w1[i].length; j++) {
				this.w1[i][j] = randRange(-1, 1);
			}
		}
		
		for(int i = 0; i < this.w2.length; i++) {
			for(int j = 0; j < this.w2[i].length; j++) {
				this.w2[i][j] = randRange(-1, 1);
			}
		}
	}
	
	private double randRange(double a, double b) {
		return a + (b - a) * Math.random();
	}
	
	public void setRandBiases() {
		
		for(int j = 0; j < b0.length; j++) {
			b0[j] = randRange(-1, 1);
		}
		
		for(int j = 0; j < b1.length; j++) {
			b1[j] = randRange(-1, 1);
		}
		
		for(int j = 0; j < b2.length; j++) {
			b2[j] = randRange(-1, 1);
		}
		

	}
	
	public void trainNet(int in[][], int out[][], int epochs) {
		for (int j = 0; j < 50; j++) {
			for(int i = 0; i < epochs; i++) {
				feedForward(in[i]);
				backProp(out[i]);
				if(i % this.batchSize == 0) {
					update();
				}
	//				System.out.println(counter);
	//				counter++;
					
	
			}
		}
		
	}
	
	
	public int[] genOutputs(int num) {
		int[] outputs = new int[10];
		outputs[num] = 1;
		return outputs;
	}

	
	/**
	 * Goes through the neural network backwards layer by layer calculating the error of each activation, 
	 * then adjusts the weights and biases based on the cost function derivative to train the neural network.
	 * @param outputs The array of values of the desired activations in the output layer.
	 */
	public void backProp(int[] outputs) {
		
//		System.out.println();
//		System.out.println("Output Activations Before");
		for (int i = 0; i < 10; i ++) {
//			System.out.println(this.act3[i]);
		}
		
		double[] delOutC = new double[this.act3.length];
		double[] outErr = new double[this.act3.length];
		
		//create cost derivative vector
		for (int i = 0; i < delOutC.length; i ++) {
			delOutC[i] = this.act3[i] - outputs[i];
			//System.out.println(delOutC[i]);
		}
		
		//calculate error of output layer
		for (int i = 0; i < outErr.length; i ++) {
			outErr[i] = delOutC[i] * dSigmoid(this.z3[i])/2;
//			System.out.println();
//			System.out.println("Error on " + i);
//			System.out.println(outErr[i]);
		}
		
		
		double[] h2Err = new double[this.act2.length];
		double[][] wt2 = transpose(this.w2);
		double[] intH2 = mult(wt2, outErr);
		
		//calculate error vector of second hidden layer
		for (int i = 0; i < h2Err.length; i ++) {
			h2Err[i] = intH2[i] * dSigmoid(this.z2[i])/2;
		}
		
		double[] h1Err = new double[this.act1.length];
		double[][] wt1 = transpose(this.w1);
		double[] intH1 = mult(wt1, h2Err);
		
		//calculate error vector of first hidden layer
		for (int i = 0; i < h1Err.length; i ++) {
			h1Err[i] = intH1[i] * dSigmoid(this.z1[i])/2;
		}
		
		
		//update biases in all layers
		for (int i = 0; i < this.b0.length; i++) {
			this.db0[i] += h1Err[i];
		}
		
		for (int i = 0; i < this.b1.length; i++) {
			this.db1[i] += h2Err[i];
		}
		
		for (int i = 0; i < this.b2.length; i++) {
			this.db2[i] += outErr[i];
		}
		
		//update weights
		for (int i = 0; i < this.w0.length; i++) {
			for (int j = 0; j < this.w0[0].length; j++) {
				this.dw0[i][j] += (this.act0[j] * h1Err[i]);
			}
		}
		
		for (int i = 0; i < this.w1.length; i++) {
			for (int j = 0; j < this.w1[0].length; j++) {
				this.dw1[i][j] += (this.act1[j] * h2Err[i]);
			}
		}
		
		for (int i = 0; i < this.w2.length; i++) {
			for (int j = 0; j < this.w2[0].length; j++) {
				this.dw2[i][j] += (this.act2[j] * outErr[i]);
			}
		}
		
		
//		System.out.println();
//		System.out.println("Output Activations After");
		for (int i = 0; i < 10; i ++) {
//			System.out.println(this.act3[i]);
		}
	}
	
	private void update() {
		
//		System.out.println();
//		System.out.println("db");
//		for (int i = 0; i < 10; i ++) {
////			System.out.println(this.db0[i]);
//		}
		
		for(int i = 0; i < b0.length; i++) {
			b0[i] -= db0[i]/this.batchSize * learnRate;
			db0[i] = 0;
		}
		
		for(int i = 0; i < b1.length; i++) {
			b1[i] -= db1[i]/this.batchSize * learnRate;
			db1[i] = 0;
		}
		
		for(int i = 0; i < b2.length; i++) {
			b2[i] -= db2[i]/this.batchSize * learnRate;
			db2[i] = 0;
		}
		
		for(int i = 0; i < w0.length; i++) {
			for(int j = 0; j < w0[0].length; j++) {
				w0[i][j] -= dw0[i][j]/this.batchSize * learnRate;
				dw0[i][j] = 0;
			}
		}
		
		for(int i = 0; i < w1.length; i++) {
			for(int j = 0; j < w1[0].length; j++) {
				w1[i][j] -= dw1[i][j]/this.batchSize * learnRate;
				dw1[i][j] = 0;
			}
		}
		
		for(int i = 0; i < w2.length; i++) {
			for(int j = 0; j < w2[0].length; j++) {
				w2[i][j] -= dw2[i][j]/this.batchSize * learnRate;
				dw2[i][j] = 0;
			}
		}
		
		
		
	
		
	}
	
	/**
	 * Transposes an input matrix.
	 * @param in The matrix to be transposed.
	 * @return Returns the transposed matrix.
	 */
	private double[][] transpose(double[][] in) {
		double[][] out = new double[in[0].length][in.length];
		
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[0].length; j++) {
				out[j][i] = in[i][j];
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

	
	private void feedForward(int[] inputs) {
		//input layer
		for(int i=0; i<this.sizes[0]; i++) {
			this.act0[i] = inputs[i];
		}
		
		//hidden layer 1
		for(int i=0; i<this.sizes[1]; i++) {
			this.act1[i] = 0.0;
			for(int j=0; j<this.sizes[0]; j++) {
				this.act1[i] += this.w0[i][j] * this.act0[j];
			}
			this.z1[i] = this.act1[i] + this.b0[i];
			this.act1[i] = sigmoid(this.act1[i] + this.b0[i]);
		}
		
		//hidden layer 2
		for(int i=0; i<this.sizes[2]; i++) {
			this.act2[i] = 0.0;
			for(int j=0; j<this.sizes[1]; j++) {
				this.act2[i] += this.w1[i][j] * this.act1[j];
			}
			this.z2[i] = this.act2[i] + this.b1[i];
			this.act2[i] = sigmoid(this.act2[i] + this.b1[i]);
		}
		
		//output layer
		for(int i=0; i<this.sizes[3]; i++) {
			act3[i] = 0.0;
			for(int j=0; j<this.sizes[2]; j++) {
				this.act3[i] += this.act2[j] * this.w2[i][j];
			}
			this.z3[i] = this.act3[i] + this.b2[i];
			this.act3[i] = sigmoid(this.act3[i] + this.b2[i]);
		}
	}
	
	private static double sigmoid(double x) {
		double activation = 1 / (1 + Math.exp(-x));
		return activation;
	}
	
	private double dSigmoid(double x) {
		return sigmoid(x) * (1 - sigmoid(x));
	}
	
	public void write_weights() {

		File file = new File("data/weights.txt");

		
			FileWriter writer;
			try {
				writer = new FileWriter(file);
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 784; j++) {
						writer.write(Double.toString(this.w0[i][j])  + " ");
					}
					writer.write("\n");
				}
				
				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						writer.write(Double.toString(this.w1[i][j]) + " ");
					}
					writer.write("\n");
				}
				
				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 16; j++) {
						writer.write(Double.toString(this.w2[i][j])  + " ");
					}
					writer.write("\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void read_weights_w0() {

			File file = new File("data/weights.txt");

			double[][] w_0 = new double[16][784];

			try {
				Scanner input = new Scanner(file);

				StringTokenizer line = new StringTokenizer(input.nextLine(), " ");

				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 784; j++) {
						w_0[i][j] = Double.parseDouble(line.nextToken());
					}
					line = new StringTokenizer(input.nextLine(), " ");
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			w0 = w_0;

		}

		public void read_weights_w1() {

			File file = new File("data/weights.txt");

			double[][] w_1 = new double[16][16];
		

			try {
				Scanner input = new Scanner(file);

				StringTokenizer line = new StringTokenizer(input.nextLine(), " ");
				
				for (int i = 0; i < 16; i++) {
					line = new StringTokenizer(input.nextLine(), " ");
				}

				for (int i = 0; i < 16; i++) {
					for (int j = 0; j < 16; j++) {
						w_1[i][j] = Double.parseDouble(line.nextToken());
					}
					line = new StringTokenizer(input.nextLine(), " ");
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			w1 = w_1;

		}

		public void read_weights_w2() {
			File file = new File("data/weights.txt");

			double[][] w_2 = new double[10][16];

			try {
				Scanner input = new Scanner(file);

				StringTokenizer line = new StringTokenizer(input.nextLine(), " ");
				
				for (int i = 0; i < 32; i++) {
					line = new StringTokenizer(input.nextLine(), " ");
				}

				for (int i = 0; i < 10; i++) {
					for (int j = 0; j < 16; j++) {
						w_2[i][j] = Double.parseDouble(line.nextToken());
					}
					line = new StringTokenizer(input.nextLine(), " ");
				}

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			w2 = w_2;

		}
		
		public void read_all() {
			read_weights_w0();
			read_weights_w1();
			read_weights_w2();	
		}
		
		public void write_biases() {
			File file = new File("data/biases.txt");

			FileWriter writer;
			try {
				writer = new FileWriter(file);
				for (int i = 0; i < 16; i++) {
					writer.write(Double.toString(this.b0[i]) + " ");
				}
				writer.write("\n");
				for (int i = 0; i < 16; i++) {
					writer.write(Double.toString(this.b1[i]) + " ");
				}
				writer.write("\n");
				for (int i = 0; i < 10; i++) {
					writer.write(Double.toString(this.b2[i]) + " ");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void read_biases() {
			File file = new File("data/biases.txt");

			double[] b_0 = new double[16];
			double[] b_1 = new double[16];
			double[] b_2 = new double[10];
			

			try {
				Scanner input = new Scanner(file);

				StringTokenizer line = new StringTokenizer(input.nextLine(), " ");

				for (int i = 0; i < 16; i++) {
					b_0[i] = Double.parseDouble(line.nextToken());
				}
				
				line = new StringTokenizer(input.nextLine(), " ");

				for (int i = 0; i < 16; i++) {
					b_1[i] = Double.parseDouble(line.nextToken());
				}
				
				line = new StringTokenizer(input.nextLine(), " ");

				for (int i = 0; i < 10; i++) {
					b_2[i] = Double.parseDouble(line.nextToken());
				}
				
				input.close();

			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			b0 = b_0;
			b1 = b_1;
			b2 = b_2;
		}

	}
	
	

