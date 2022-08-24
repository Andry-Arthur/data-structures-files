public class MergeSort 
{
	private static Double[] merge(Double[] leftSide, Double[] rightSide) 
	{
		Double[] result = new Double[leftSide.length + rightSide.length];
		int leftInd = 0, rightInd = 0, resultInd = 0;
		
		while(leftInd < leftSide.length && rightInd < rightSide.length) {
			if(leftSide[leftInd] >= rightSide[rightInd]) {
				result[resultInd] = rightSide[rightInd];
				++rightInd;
			}
			else if(leftSide[leftInd] < rightSide[rightInd]) {
				result[resultInd] = leftSide[leftInd];
				++leftInd;
			}
			++resultInd;
		}
		
		while(resultInd < result.length) {
			if(rightInd < rightSide.length) {
				result[resultInd] = rightSide[rightInd];
				++resultInd;
				++rightInd;
			}
			else {
				result[resultInd] = leftSide[leftInd];
				++resultInd;
				++leftInd;
			}
		}
		return result;
	}

	private static Double[] sort(Double[] items, int i, int j)
	{
		if(items.length == 0) {
			return new Double[0];
		}
		else if(j==i) {
			Double[] single = new Double[1];
			single[0] = items[i];
			return single;
		}
		else {
			int mid = (j+i)/2;
			Double[] left = sort(items, i, mid);
			Double[] right = sort(items, mid+1, j);
			
			return merge(left, right);
		}
	}

	public static Double[] sort(Double [] items) 
	{
		return sort(items, 0, items.length - 1);
	}
}
