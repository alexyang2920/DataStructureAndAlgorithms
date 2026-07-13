package core.sort;

public class QuickSort {

    public static void main(String[] args) {
        int[] source = new int[] { 4, 1, 3, 9, 7 };
        new QuickSort.Solution().quickSort(source, 0, 4);
        for(int num : source) {
            System.out.println(num);
        }
    }

    static class Solution {

        public void quickSort(int[] arr, int low, int high) {
            if (low < high) {
                int mid = partition(arr, low, high);
                quickSort(arr, low, mid - 1);
                quickSort(arr, mid + 1, high);
            }

        }

        private int partition(int[] arr, int low, int high) {
            int pivot = arr[low];
            int i = low; // i always point to  the rightmost element are less or equal to the pivot.
            for (int j = low + 1; j <= high; j++) {
                if (arr[j] <= pivot) {
                    i++;
                    swap(arr, i, j);
                }
            }

            swap(arr, i, low); // swap the pivot element with the ith element.
            return i;
        }

        void swap(int[] arr, int i, int j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
