package CMONEYPackage;

import java.lang.Math;

//https://github.com/ChriZ982/NeuralNetTest/tree/master/src/zindach/mathlib
/**
 * This module creates the neural network that 
 * recognizes the picture of digits
 * @author Group 2
 * @version 1.0
 * @since 2019-03-07
 */
public class Network {
	
	private final int numLayers;
	private final int[] sizes;
	private double[] biases;
	private double[] weights;
	
	
    public Network(int[] sizes){
    	this.numLayers = sizes.length;
    	this.sizes = sizes;
    	
    }
    
    
    /**
     * This function finds the sigmoid of a value
     * @param z double value of the sum of weights 
     * times activation plus biases
     * @return gives the sigmoid value of weights,
     * activations and biases as a double
     */
    private double sigmoid(double z) {
    	return 1.0/(1.0 + Math.exp(-z));
    }
    /**
     * This function computes the derivative of the 
     * simgoid function
     * @param z double value of the sum of weights 
     * times activation plus biases
     * @return the derivative of the sigmoid
     */
    private double sigmoifPrime(double z) {
    	return sigmoid(z)*(1-sigmoid(z));
    }

}