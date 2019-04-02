package CMONEYPackage;

public class read_Weights {
	
	public double[][] read_weights_w0() {

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

		return w_0;

	}

	public double[][] read_weights_w1() {

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

		return w_1;

	}

	public double[][] read_weights_w2() {

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

		return w_2;

	}

}
