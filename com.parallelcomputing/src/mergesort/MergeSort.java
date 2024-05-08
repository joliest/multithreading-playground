package mergesort;

public class MergeSort {
    private int[] nums;
    private int[] tempArray;

    public MergeSort(int[] nums) {
        this.nums = nums;
        this.tempArray = new int[nums.length];
    }

    public void sort() {
        mergeSort(0, nums.length - 1);
    }

//    DIVIDE AND CONQUER APPROACH
    private void mergeSort(int low, int high) {
        // base case
        if (low >= high) {
            return;
        }

        // middle item
        int middleIndex = (low + high) / 2;

        // we keep splitting the array into smaller arrays until it will contain one item
        // Divide Phase
        mergeSort(low, middleIndex);
        mergeSort(middleIndex + 1, high);

        // we have to combine the subarrays
        // Conquer Phase
        merge(low, middleIndex, high);
    }

    private void merge(int low, int middleIndex, int high) {
        // copy the items into temp array
        for (int i = low; i <= high; i++)
            tempArray[i] = nums[i];


        // see image ref 1 here: https://docs.google.com/document/d/15T0AG8xZ78OJZUp4lJ00tZBL5beWlLcL1prh7zdKbwQ/edit#heading=h.ifmlsduktw9u
        int i = low;
        int j = middleIndex + 1;
        int k = low;

        // we consider the temp array and copy the items into the original nums[]
        // see image ref2 here: https://docs.google.com/document/d/15T0AG8xZ78OJZUp4lJ00tZBL5beWlLcL1prh7zdKbwQ/edit#heading=h.p7g62gtig3ww
        while(i <= middleIndex && j <= high) {
            // we simulate this https://docs.google.com/document/d/15T0AG8xZ78OJZUp4lJ00tZBL5beWlLcL1prh7zdKbwQ/edit#heading=h.pid4f0sda286
            if (tempArray[i] < tempArray[j]) {
                nums[k] = tempArray[i];
                ++i;
            } else {
                nums[k] = tempArray[j];
                ++j;
            }

            ++k; // temp array
        }

        // copy the items from left subarray if there are any numbers left
        while (i <= middleIndex) {
            nums[k] = tempArray[i];
            ++k;
            ++i;
        }
        // copy the items from right subarray if there are any numbers left
        while (j <= high) {
            nums[k] = tempArray[j];
            ++k;
            ++j;
        }
    }

    public void showArray() {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
    }

    private void swap(int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
