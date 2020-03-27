import java.util.Random;

class ArraySorts {
	//text book way, random

	private static void swap(int[] arr, int savedValue, int switchValue) {
		int temp = arr[savedValue];
		arr[savedValue] = arr[switchValue];
		arr[switchValue] = temp;
	}


	private static int partition(int[] arr, int left, int right, int pivot) {
		swap(arr, left, pivot);
		int pivot2 = arr[left];
		int lastS1 = left;
		int firstUnknown = left + 1;
		boolean small = false;
		while(firstUnknown <= right) {
			if(arr[firstUnknown]<pivot2) {
				lastS1++;
				swap(arr, firstUnknown, lastS1);
				firstUnknown++;
			}
			//it's the same as the pivot (this is the new part)  
			else if(arr[firstUnknown] > pivot2) {		
				//do nothing for loop will increase it
				firstUnknown++;
			}
			else {
				if(small==true) {
					//If so, swap the element into the small partition. 	
						lastS1++;
						swap(arr, firstUnknown, lastS1);
						firstUnknown++;
				}
				else {
					firstUnknown++;
				//If the small variable is false put it in the large partition 
				//by just incrementing the first unknown pointer 
				}
				small = !small;
			}
		}
		swap(arr, left, lastS1);
		return lastS1;
	}

	
	//2 ptr partition

	private static pair partition2(int[] arr, int left, int right, int pivot) { 
		int startPoint = left;
		int endPoint = right;
		int pivot2 = arr[pivot];
		//compare start pointer and end pointer
		//start pinter must be less or equal to end pointer
		while(startPoint <= endPoint) {
			//compare start pointer value and value of pivot
			//it must lesser than value of pivot
			while(arr[startPoint] < pivot2) {
				startPoint++;
			}
			//compare end pointer value and value of pivot
			//it must be bigger than value of pivot
			while(arr[endPoint] > pivot2) {
				endPoint--;
			}
	        
			//since we must not swap and start point and end point cross each other
			//check the number of startpoint and end point
			if(startPoint <= endPoint) {
				swap(arr, startPoint, endPoint);
				startPoint++;
				endPoint--;
			}
		}

		//since end point and start point stoped when it corss, put right, left
		pair result = new pair(endPoint, startPoint);
		return result;
	}

	private static pair partition3(int arr[], int left, int right, int pivot1, int pivot2) {
		//move first pivot to left
		swap(arr,left, pivot1);
		//move second pivot to right
		swap(arr,right, pivot2);
		
		//since two pivot number is random
		//if pivot1 is bigger than pivot2
		//swap two number
		if(arr[left]>arr[right]) {
			swap(arr, left, right);
		}
		
		int firstunknown = left+1;
		
		boolean check = true;
		
		int lastbig = right;
		int lastsmall = left;
		int smallPivot = arr[left];
		int bigPivot = arr[right];	

		//move first unknown up to the right
		//since lastbig is right and right is pivot2
		//we could just stop before last big
		while(firstunknown < lastbig) {
			//if unknown value is smaller than pivot1 value
            if(arr[firstunknown] < smallPivot) {
            	//increase 1 in last small
            	lastsmall++;
            	//swap last small and first unknown
            	swap(arr, lastsmall, firstunknown);
            	//increase first unknown
                firstunknown++;
            }
            //if unknown value is bigger than pivot2 value
            else if(arr[firstunknown] > bigPivot) {
            	//decrease last big value
            	lastbig--;
            	//swap decrease value and unknown value
            	swap(arr, lastbig, firstunknown);
            	//we don't need to increase first unkown value
            	//because we are not sure about lastbig-- value
            	//so we need to check
                
            }
            else{
                if(check) {
                    if(arr[firstunknown] == smallPivot) {
                    	lastsmall++;
                    	swap(arr,lastsmall, firstunknown);
                        firstunknown++;                    
                    }
                    else if(arr[firstunknown] == bigPivot) {
                    	lastbig--;
                    	swap(arr, lastbig, firstunknown);
                    }
                }                    
                else {
                   firstunknown++;
                }
                check = !check;            
            }
        }

		swap(arr, lastsmall, left);
		swap(arr, lastbig, right);

		pair result = new pair(lastsmall, lastbig);
		
		return result;
	}

	
	public static void insertionSort(int[] arr, int n) {
		int save;
		int secondList;
		for(int aList = 1; aList < n; aList++) {
			save = arr[aList];
			secondList = aList - 1;
			while(secondList >= 0 && arr[secondList] > save) {
				arr[secondList + 1] = arr[secondList];
				secondList--;
			}
			arr[secondList + 1] = save;
		}
	}
	//random pivot
	
	public static void QuickSort1(int a[], int n, int cutoff) {
		QuickSort1(a, 0, n-1, cutoff);
		insertionSort(a, n);
	}
	
	private static void QuickSort1(int a[], int left, int right, int cutoff) {
		int pivot;
		int lfSize;
		int rtSize;
		int pivot2;
		
		while(right - left + 1 >= cutoff) {
			if(left == right) {
				pivot = right;
			}
			else {
				pivot = left + (int)(Math.random()*(right-left+1));				
			}
			pivot2 = partition(a, left, right, pivot);
			
			lfSize = pivot2 - left;
			rtSize = right - pivot2;
			if(lfSize < rtSize) {
				QuickSort1(a, left, pivot2 - 1, cutoff);
				left = pivot2 + 1;
			}
			else {
				QuickSort1(a, pivot2 + 1, right, cutoff);
				right = pivot2 -1;
			}
		}
		
	}
	//2 ptr partion, random pivot
	public static void QuickSort2(int a[], int n, int cutoff) {
		QuickSort2(a, 0, n-1, cutoff);
		insertionSort(a, n);
	}

	private static void QuickSort2(int a[], int left, int right, int cutoff) {
		int pivot;
		int lfSize;
		int rtSize;

		while(right - left + 1 >= cutoff) {
			if(right==left){
				pivot = right;
			}
			else {
				pivot = left + (int)(Math.random()*(right-left+1));
			}
			pair pivot2 = partition2(a, left, right, pivot);
			lfSize = pivot2.left- left;
			rtSize = right - pivot2.right;
			if(lfSize < rtSize) {
				QuickSort2(a, left, pivot2.left, cutoff);
				left = pivot2.right;
			}
			else {
				QuickSort2(a, pivot2.right, right, cutoff);
				right = pivot2.left;
			}
		}
	}
	
	public static void QuickSort3(int[] a, int n, int cutoff) {
		QuickSort3(a, 0, n-1, cutoff);
		insertionSort(a, n);
	}
	
	//pivot left
	private static void QuickSort3(int[] a, int left, int right, int cutoff) {
		int pivot;
		int lfSize;
		int rtSize;
		int pivot2;
		
		while(right - left + 1 >= cutoff) {
			if(left == right) {
				pivot = right;
			}
			else {
				pivot = left;				
			}
			pivot2 = partition(a, left, right, pivot);
			lfSize = pivot2 - left;
			rtSize = right - pivot2;
			if(lfSize < rtSize) {
				QuickSort3(a, left, pivot2 - 1, cutoff);
				left = pivot2 + 1;
			}
			else {
				QuickSort3(a, pivot2 + 1, right, cutoff);
				right = pivot2 -1;
			}
		}				
	}
	
	//2 ptr partition pivot left
	public static void QuickSort4(int a[], int n, int cutoff) {
		QuickSort4(a, 0, n-1, cutoff);
		insertionSort(a, n);
	}

	private static void QuickSort4(int a[], int left, int right, int cutoff) {
		int pivot;
		int lfSize;
		int rtSize;

		while(right - left + 1 >= cutoff) {
			if(right==left){
				pivot = right;
			}
			else {
				pivot = left;
			}
			pair pivot2 = partition2(a, left, right, pivot);
			lfSize = pivot2.left- left;
			rtSize = right - pivot2.right;
			if(lfSize < rtSize) {
				QuickSort4(a, left, pivot2.left, cutoff);
				left = pivot2.right;
			}
			else {
				QuickSort4(a, pivot2.right, right, cutoff);
				right = pivot2.left;
			}
		}
	}
	
	//3 partitons, 2 random pivots
	public static void QuickSort5(int a[], int n, int cutoff) {
		QuickSort5(a, 0, n-1, cutoff);
		insertionSort(a, n);
	}	 
	
	private static void QuickSort5(int arr[], int left, int right, int cutoff) {
		int pivot1;
		int pivot2;
		int lfsize;
		int midsize;
		int rtsize;
		while(right - left + 1 >= cutoff) {
			pivot1 = left + (int)(Math.random()*(right-left+1));
			pivot2 = left + (int)(Math.random()*(right-left+1));

			pair pivot = partition3(arr, left, right, pivot1, pivot2);
			//int a[], int left, int right, int pivot1, int pivot2
			lfsize = pivot.left-1 - left;
			midsize = pivot.right-1 - pivot.left + 1;
			rtsize = right - (pivot.right+1);
			
			//professor gave us this part
			if(lfsize>rtsize && lfsize>midsize) {
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);			
				QuickSort5(arr, pivot.right + 1, right, cutoff);
				right = pivot.left - 1;
			}
			else if(midsize>lfsize && midsize>rtsize) {			
				QuickSort5(arr, left, pivot.left - 1, cutoff);			
				QuickSort5(arr, pivot.right + 1, right, cutoff);//given one
				left = pivot.right + 1;
				right = pivot.left - 1;

				
				QuickSort5(arr, left, pivot.left - 1, cutoff);
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);//given one
				left=pivot.right + 1;
			}
			else{
				QuickSort5(arr, left, pivot.left - 1, cutoff);
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);//given one
				left=pivot.right + 1;
			}			
		}
	}
	
	public static void AlmostQS1(int a[], int n, int cutoff) {
		AlmostQS1(a, 0, n-1, cutoff);
	}
	
	private static void AlmostQS1(int a[], int left, int right, int cutoff) {
		Random rnd = new Random();
		int pivot;
		int lfSize;
		int rtSize;
		int pivot2;

		while(right - left + 1 >= cutoff) {
			pivot = left + rnd.nextInt((right-left+1));

			pivot2 = partition(a, left, right, pivot);
			
			lfSize = pivot2 - left;
			rtSize = right - pivot2;
			if(lfSize < rtSize) {
				AlmostQS1(a, left, pivot2 - 1, cutoff);
				left = pivot2 + 1;
			}
			else {
				AlmostQS1(a, pivot2 + 1, right, cutoff);
				right = pivot2 -1;
			}
		}
		
	}
	//2 ptr partion, random pivot
	public static void AlmostQS2(int a[], int n, int cutoff) {
		AlmostQS2(a, 0, n-1, cutoff);
	}
	
	private static void AlmostQS2(int a[], int left, int right, int cutoff) {
		Random rnd = new Random();
		int pivot;
		int lfSize;
		int rtSize;

		while(right - left + 1 >= cutoff) {
			if(right==left){
				pivot = right;
			}
			else {
				pivot = left + rnd.nextInt((right-left));
			}
			pair pivot2 = partition2(a, left, right, pivot);
			lfSize = pivot2.left- left;
			rtSize = right - pivot2.right;
			if(lfSize < rtSize) {
				AlmostQS2(a, left, pivot2.left, cutoff);
				left = pivot2.right;
			}
			else {
				AlmostQS2(a, pivot2.right, right, cutoff);
				right = pivot2.left;
			}
		}
		
	}
	
	public static void AlmostQS5(int a[], int n, int cutoff) {
		AlmostQS5(a, 0, n-1, cutoff);
	}

	private static void AlmostQS5(int arr[], int left, int right, int cutoff) {
		int pivot1;
		int pivot2;
		int lfsize;
		int midsize;
		int rtsize;
		Random rnd = new Random();
		while(right - left + 1 >= cutoff) {
			pivot1 = left + rnd.nextInt((right-left)+1);
			pivot2 = left + rnd.nextInt((right-left)+1);

		
				while(pivot1 == pivot2){
					pivot1 = left + rnd.nextInt((right-left)+1);
					pivot2 = left + rnd.nextInt((right-left)+1);
				}
			

			pair pivot = partition3(arr, left, right, pivot1, pivot2);
			lfsize = pivot.left-1 - left;
			midsize = pivot.right-1 - pivot.left + 1;
			rtsize = right - (pivot.right+1);
			
			//professor gave us this part
			if(lfsize>rtsize && lfsize>midsize) {
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);			
				QuickSort5(arr, pivot.right + 1, right, cutoff);
				right = pivot.left - 1;
			}
			else if(midsize>lfsize && midsize>rtsize) {			
				QuickSort5(arr, left, pivot.left - 1, cutoff);			
				QuickSort5(arr, pivot.right + 1, right, cutoff);//given one
				left = pivot.right + 1;
				right = pivot.left - 1;

				
				QuickSort5(arr, left, pivot.left - 1, cutoff);
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);//given one
				left=pivot.right + 1;
			}
			else{
				QuickSort5(arr, left, pivot.left - 1, cutoff);
				QuickSort5(arr, pivot.left + 1, pivot.right - 1, cutoff);//given one
				left=pivot.right + 1;
			}			
		}
	}

	public static void HeapSortBU(int[] arr, int size) {
		//to start, we must build heap first
		//to start, we must build heap sort first
		for(int index = (size-2)/2; index >= 0; index--) {
			trickleDown(arr, index, size);
		}
		//since our rebuild start from size
		int last = size - 1;
		for(int index = 0; index < size; index++) {
			swap(arr, 0, last);
			trickleDown(arr, 0, last);
			last--;
		}
	}
	
	//trickledown
	public static void trickleDown(int[] arr, int swapN, int size) {
		int savedNumber = swapN;
		size = size -1;
		//since there is a chance that left<swapN
		//and right > swapN therefore, we could not
		//start with 
		int count = swapN;
		int savedValue = arr[swapN]; 
		do{
			swapN = savedNumber;//
			int leftChild = 2 * swapN + 1;//
			//save the root(parent)
			//check where is left child
			//be sure it is not over the size
			if(leftChild <= size) {//
					//since we are not doing avl tree
					//we are not doing binary tree
					//we just need to find which is bigger
				if(arr[swapN]<arr[leftChild]) {				
					savedNumber = leftChild;
				}
					//since right child will be
					//arr[2], arr[4], arr[6]
					//we could just add + 1 the leftchild
					//if we don't have leftchild, we could not have
					//rightchild
				int rightChild = leftChild + 1;
					//check the array does it have right side
				if(rightChild <= size) {
					if(arr[savedNumber]<arr[rightChild]) {
						savedNumber = rightChild;
					}
				}
			}		
			if(arr[savedNumber] > arr[swapN] && savedNumber<=size) {
				arr[swapN] = arr[savedNumber];
				arr[savedNumber] = savedValue;
			}
		}while(savedNumber > swapN && savedNumber <= size);
	}

	public static void HeapSortTD(int[] arr, int size) {
		
		for(int index = 1; index < size; index++) {
			trickleUP(arr, index);
		}		
		int last = size - 1;
		for(int index = 0; index < size; index++) {
			swap(arr, 0, last);
			trickleDown(arr, 0, last);
			last--;
		}
	}
	
	public static void trickleUP(int[] arr, int size) {		
		int child = size;
		boolean check = false;
		int saved = arr[child];
		int parent = (child-1)/2;
		while(!check) {
			if(arr[parent]<arr[child]) {
				arr[child] = arr[parent];
				arr[parent] = saved;
				child = parent;
				parent = (child-1)/2;
			}
			else {
				check = true;
			}
		}

	}
	public static String myName() {
		return "Haemin Lee";
	}
}

class pair {
	public int left, right;
	
	public pair(int left, int right) {
		this.left = left;
		this.right = right;
	}
}
