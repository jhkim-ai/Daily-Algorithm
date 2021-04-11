package StepByStep.day210416;

import java.util.*;
import java.io.*;

public class BOJ2512_예산 {

    static int N, budget;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        budget = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        // Idea. 이분 탐색
        // (1) 요청된 예산(arr)의 총액이 국가 예산(budget)보다 작거나 같다면, 요청된 예산 중 최대를 출력
        if (sum <= budget) {
            System.out.println(arr[N - 1]);
        }

        // (2) 요청된 예산의 총액이 국가 예산보다 크다면, 이분탐색을 이용한 최대치(end)를 구함
        else {
            int start = 0;
            int end = Integer.MAX_VALUE;
            int mid = 0;

            while (start <= end) {
                sum = 0;
                mid = (start + end) / 2;

                // System.out.println(start + " : " + end + " = " + mid);

                // sum : 책정된 국가 예산 이하에서 지방의 예산 요청 총액
                for (int i = 0; i < N; i++) {
                    if (arr[i] <= mid)
                        sum += arr[i];
                    else
                        sum += mid;
                }

                // 국가 예산보다 크다면, 예산 책정 범위를 좁힌다(낮춘다).
                if (sum > budget)
                    end = mid - 1;
                // 국가 예산보다 작다면, 예산 책정 범위를 넓힌다(높인다).
                else
                    start = mid + 1;
            }
            System.out.println(end);
        }
    }
}
