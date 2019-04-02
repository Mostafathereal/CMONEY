package ImageProcessing;
import java.util.List;


import java.util.List;

public class IReader {
	
	
	public static MnistIReader R = (MnistIReader) new MnistIReader();
	
	
	public static List<int[][]> images = (List<int[][]>) R.getImages("data/t10k-images.idx3-ubyte");
	
	public static int[] getLabels() {
		return (int[]) R.getLabels("data/t10k-labels.idx1-ubyte");
	}
	
	public List<int[][]> getImages() {
		return R.getImages("data/t10k-images.idx3-ubyte");
	}

	/**
	 * The stores the pictures and their labels
	 * @param args string array
	 */
	public static void main(String[] args) {
		
		int[] labels = getLabels();
		
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
