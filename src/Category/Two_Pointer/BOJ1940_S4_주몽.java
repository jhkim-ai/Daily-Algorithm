package Category.Two_Pointer;

import java.util.*;
import java.io.*;

public class BOJ1940_S4_주몽 {

    static int N, M, ans;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // ------ 알고리즘 시작 ------ //
        // Idea. Two Pointer

        // 1. 정렬
        Arrays.sort(arr);

        // 2. 선형 반복을 통한 개수 확인
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];
            if(sum == M){
                left++;
                right--;
                ans++;
            }
            else if(sum < M){
                left++;
            }
            else if(sum > M){
                right--;
            }
        }

        System.out.println(ans);
    }
}
