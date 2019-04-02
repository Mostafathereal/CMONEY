package CMONEYPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;


public class NewNetwork {
	
	private final static int[] sizes = new int[]{784, 16, 16, 10};   // manually setting the size of each layer of nodes
	private double[][][] weights;
	private double[][] biases;
	
	//private actL1
	
	
	
	private void setRandWeights() {
		
		for(int i = 0; i < weights.length; i++) {
			for(int j = 0; j < weights[i].length; j++) {
				for(int k = 0; k < weights[i][j].length; k++ ) {
					weights[i][j][k] = -1.0 + (2.0)* Math.random();
				}
			}
		}
	}
	
	
	
	private void setRandBiases() {
		
		for(int i = 0; i < biases.length; i++) {
			for(int j = 0; j < biases[i].length; j++) {
				biases[i][j] = -1.0 + 2.0*Math.random();
			}
		}
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
