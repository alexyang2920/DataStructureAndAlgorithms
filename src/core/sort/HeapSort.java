package core.sort;

public class HeapSort {

    static class Solution {

        public void heapSort(int arr[]) {
            int n = arr.length;

            // heapify from the lowest level of node that has child.
            for (int i = (n - 1)/2; i >= 0; i--) {
                heapify(arr, i, n - 1);
            }

            for (int i = n - 1; i > 0; i--) {
                swap(arr, i, 0); // swap the ith with first element.
                heapify(arr, 0, i - 1); // heapify the first element with effective size i-1.
            }
        }

        void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }

        void heapify(int[] arr, int i, int end) {
            int j = 2 * i + 1; // left child of the ith element.
            if (j <= end) {
                // update j if the right child exists and is larger than the left child.
                if (j + 1 <= end && arr[j] < arr[j + 1]) {
                    j = j + 1;
                }

                // swap and move to the child when the parent is less than the larger child
                if (arr[i] < arr[j]) {
                    swap(arr, i, j);
                    heapify(arr, j, end);
                }
            }
        }
    }

}
