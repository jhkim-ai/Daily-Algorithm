package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon1920 {
    static int N, M, input;
    static int[] arr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // N 받아오기
        N = sc.nextInt();
        arr = new int[N];
        // A[N]에 값 초기화
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr); // 이진탐색을 위한 정렬

        // M 받아오기
        M = sc.nextInt();
        for (int i = 0; i < M; i++){
            input = sc.nextInt();
            int res = binarySearch(input, 0, arr.length);
            System.out.println(res);
        }
    }

    static int binarySearch(int input, int start, int end) {
        int mid = (start + end) / 2;

        if (mid >= end || mid < start)
            return 0;

        if (arr[mid] == input)
            return 1;
        else if (input < arr[mid])
            return binarySearch(input, start, mid);
        else
            return binarySearch(input, mid+1, end);
    }
}
