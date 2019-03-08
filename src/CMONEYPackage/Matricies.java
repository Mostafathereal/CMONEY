package CMONEYPackage;

import java.lang.Math;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

public class Matricies {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        INDArray nd = Nd4j.create(new float[]{1,2},new int[]{1, 2});        //vector as row
        INDArray nd2 = Nd4j.create(new float[]{3,4},new int[]{2, 1});       //vector as column
        INDArray nd3 = Nd4j.create(new float[][]{{1,2},{3,4}});
        INDArray nd4 = Nd4j.create(new float[][]{{3,4},{5,6}});

        System.out.println(nd);
        System.out.println(nd2);
        System.out.println(nd3);
		

	}

}
