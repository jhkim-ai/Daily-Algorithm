package StepByStep.day210306;

import java.io.*;
import java.util.*;

public class Baekjoon1205_S4_등수구하기 {

    static int N, S, P;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        // N > 0
        if (N != 0) {
            arr = new int[N];
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            // --------- 알고리즘 시작 --------- //
            // 초기화
            int cnt = 1;
            int rank = 1;

            // 하나씩 비교하며 rank 및 카운트 증가
            for (int i = 0; i < N; i++) {
                // 값보다 클 경우 ranking 정착
                if (S > arr[i]) {
                    break;
                // 값이 같다면, cnt만 증가
                } else if (S == arr[i]) {
                    cnt++;
                // 값이 작다면, ranking과 카운트를 동시에 증가
                } else {
                    cnt++;
                    rank++;
                }
            }
            if (cnt <= P)
                System.out.println(rank);
            else
                System.out.println(-1);
        }
        // N == 0
        else {
            System.out.println(1);
        }
    }

    static String input = "3 90 10\n" +
            "100 90 80";
}
