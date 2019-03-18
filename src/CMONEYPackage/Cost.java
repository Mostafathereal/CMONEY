package CMONEYPackage;
import java.lang.Math;

public class Cost {
	
	public double costFunc(double[] activations, int correctNumber) {
		double sum = 0;
		for (int i = 0; i < 10; i++) {
			if (i == correctNumber) {
				sum += (Math.pow(activations[i] - 1, 2));
			}
			else {
				sum += (Math.pow(activations[i], 2));
			}
		}
		
		return sum;
	}
}
