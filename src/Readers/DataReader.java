package Readers;
import java.util.List;


public class DataReader {
	
	public static MnistReader R = (MnistReader) new MnistReader();
	
	public static int[] labels = (int[]) R.getLabels("t10k-labels.idx1-ubyte");
	
	public static List<int[][]> images = (List<int[][]>) R.getImages("t10k-images.idx3-ubyte");


	public static void main(String[] args) {
		
		for(int i = 0; i < 16; i++) {
			System.out.println(labels[i]);
		}
		
		
		for(int z = 0; z < 10; z++) {
			for(int j = 0; j < 28; j++) {
				for (int k = 0; k < 28; k++) {
					
					System.out.printf("%3s", images.get(z)[j][k]);
				}
				System.out.println();
			}
			
			System.out.println("");
			System.out.println("");
		}
		
		
		
		
		
		
		//labels = R.getLabels("train-images.idx3-ubyte");
		
		
		//int[] labels = (int[]) R.getLabels(R);

		
		//for(int i = 0; )

	}

}
