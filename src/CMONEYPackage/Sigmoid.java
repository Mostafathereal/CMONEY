package CMONEYPackage;

import java.lang.Math;

public class Sigmoid {

	public double sigmoidFunc(double x) {
		double activation = 1 / (1 + Math.exp(x));
		return activation;
	}
	
	public double sigmoidDerivative(double x) {
		return sigmoidFunc(x) * (1 - sigmoidFunc(x));
	}
}
