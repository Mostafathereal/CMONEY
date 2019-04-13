package CMONEYPackage;

public class binSearch {
	
	/**
	 * Uses a binary search algorithm to search an array of Strings for a input currency and returns the index of it if it is in the list.
	 * @param currencies The list of currencies to search through.
	 * @param curr The currencies you are trying to find in the list.
	 * @return The index of the currency if it is there if not it return -1.
	 */
	public static int binarySearch(String[] currencies, String curr) {
		int low = 0;
		int high = currencies.length;
		while(low <= high) {
			int mid = low + (high-1)/2;
			
			int comp = curr.compareTo(currencies[mid]);
			
			if (comp == 0) 
				return mid;
			
			else if (comp > 0) 
				low = mid + 1;
			
			else
				high = mid - 1;
		}
		
		return -1;
	}
}
