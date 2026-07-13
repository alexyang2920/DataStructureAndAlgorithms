package core.sort;

import java.util.List;
import java.util.ArrayList;

public class MergeSort {

    public static void main(String[] args) {
        int[] source = new int[] { 4, 1, 3, 9, 7 };
        new Solution().mergeSort(source, 0, 4);
        for(int num : source) {
            System.out.println(num);
        }
    }
    static class Solution {
        public void mergeSort(int arr[], int l, int r) {
            if (l < r) {
                int mid = l + (r - l) / 2;
                mergeSort(arr, l, mid);
                mergeSort(arr, mid + 1, r);
                merge(arr, l, mid, r);
            }
        }

        void merge(int[] arr, int l, int mid, int r) {
            List<Integer> tmp = new ArrayList<>();

            int i = l;
            int j = mid + 1;
            while (i <= mid && j <= r) {
                if (arr[i] <= arr[j]) {
                    tmp.add(arr[i]);
                    i++;
                } else {
                    tmp.add(arr[j]);
                    j++;
                }
            }

            while (i <= mid) {
                tmp.add(arr[i]);
                i++;
            }
            while (j <= r) {
                tmp.add(arr[j]);
                j++;
            }

            for (i = l; i <= r; i++) {
                arr[i] = tmp.get(i - l);
            }
        }

    }

}
