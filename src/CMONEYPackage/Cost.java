package CMONEYPackage;

import java.lang.Math;
import org.ejml.simple.SimpleMatrix;

public class Cost {
	
	public double costFunc(SimpleMatrix activations, int correctNumber) {
		double sum = 0;
		for (int i = 0; i < 10; i++) {
			if (i == correctNumber) {
				sum += (Math.pow(activations.get(i, 0) - 1, 2));
			}
			else {
				sum += (Math.pow(activations.get(i, 0), 2));
			}
		}
		
		return sum;
	}
	
	public double gradient(SimpleMatrix A) {
		
		
		return 1.;
	}
}
