package StepByStep.day210306;

import java.io.*;
import java.util.*;

public class Baekjoon2512_S3_예산 {

    static int N, T;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        int sum = 0, m = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            m = Math.max(m, arr[i]);
        }

        T = Integer.parseInt(br.readLine());

        // --------- 알고리즘 시작 --------- //
        // 제약 (1). 모든 요청이 가능할 경우 최대 금액을 출력
        if(sum <= T)
            System.out.println(m);
        // 제약 (2). 이분 탐색으로 최댓값 찾기
        else{
            int start = 0;
            int end = Integer.MAX_VALUE;
            int mid = 0;

            while(start <= end){
                mid = (start + end) / 2;
                int tmp = 0;
                for (int i = 0; i < N; i++) {
                    if(arr[i] > mid)
                        tmp += mid;
                    else
                        tmp += arr[i];
                }
                if(tmp > T)
                    end = mid -1;
                else
                    start = mid+1;
            }
            System.out.println(end);
        }


    }

    static String input = "4\n" +
            "120 110 140 150\n" +
            "485";
}
