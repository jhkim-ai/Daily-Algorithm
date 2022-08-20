package Formula.Sorting;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {

        int[] arr = {2, 4, 5, 7, 1, 8, 3, 1, 6};
        // arr = {1, 2, 3, 4, 5};

        quickSort(arr, 0, arr.length-1);

        System.out.println(Arrays.toString(arr));
    }

    // quick 정렬 = (투 포인터 + 분할정복)을 이용한 정렬 기법 (불안정정렬)
    // quick 정렬의 Worst Case(N^2) = 정렬된 상태이면서 pivot을 맨 앞으로 설정할 경우
    public static void quickSort(int[] arr, int start, int end){
        if(start >= end) return; // 원소가 1개일 때

        int pivot = (start + end) / 2; // 최악의 상황 방지를 위해 pivot을 중앙값으로 설정
        int left = start;
        int right = end;
        int tmp;

        while(left <= right){
            while(arr[left] < arr[pivot]){ // 부등호에 등호가 없는 이유: pivot을 지나서 탐색하면 안되기 때문
                ++left;
            }
            while(arr[right] > arr[pivot]){
                --right;
            }

            if(left <= right){
                tmp = arr[right];
                arr[right] = arr[left];
                arr[left] = tmp;
                ++left;
                -- right;
            }
        }

        quickSort(arr, start, right);
        quickSort(arr, left, end);
    }
}
