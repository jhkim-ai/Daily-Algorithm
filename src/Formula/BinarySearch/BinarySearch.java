package Formula.BinarySearch;

import java.util.Arrays;

// 이진 탐색 (Binary Search)
public class BinarySearch {
    public static void main(String[] args) {

        // 1st. 탐색 할 배열
        int[] arr = {24, 50, 88, 64, 12, 8, 100, 44};

        // 2nd. 정렬
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

        // 3rd. 사용
        int idx = binarySearch(arr, 12);
        if(idx < 0)
            System.out.println("찾는 값이 없습니다");
        else
            System.out.println(arr[idx]);

        // cf. java.util.Arrays 에 binarySearch API 가 있다.
        // 값이 없다면, -insertion Point -1 을 return 한다.

        // 값이 있을 때,
        System.out.println(Arrays.binarySearch(arr, 64));
        // 값이 없을 때,
        System.out.println(Arrays.binarySearch(arr, 87));

        // 범위를 주고 싶을 때.
        System.out.println(Arrays.binarySearch(arr, 0, arr.length, 44));
    }

    // 반복문을 이용한 Binary Search
    private static int binarySearch(int[] arr, int key){

        int start = 0, end = arr.length-1;

        // 반씩 잘라가며 탐색
        while(start <= end){
            int mid = (start + end) /2;
            if(arr[mid] == key)
                return mid;
            else if(arr[mid] < key)
                start = mid +1;
            else if(arr[mid] > key)
                end = mid -1;
        }

        // 값을 찾지 못했을 경우
        return -1;
    }


}

