package CMONEYPackage;

import java.lang.Math;

public class Sigmoid {

	public double sigmoidFun(double x) {
		double activation = 1 / (1 + Math.exp(x));
		return activation;
	}
}
