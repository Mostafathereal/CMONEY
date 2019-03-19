package CMONEYPackage;

public class feedForward {
	
	public double [] feedforward(double a) {
		double sum = 0;
		
		for(int i=0; i<weights.length; i++) {
			sum += weights[i]*a[i] + biases[i];
			a = Sigmoid.sigmoidFunc(sum);
		}
	}
	
}
