package CMONEYPackage;

public class TestClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		NeuralNet n = new NeuralNet();
		n.NeuralNet();
		System.out.println("hello");
		
		for(int i = 0; i < 3; i++) {
			n.weight[i].print();
		}
		

	}

}
