package StepByStep.day210416;

import java.util.*;
import java.io.*;

public class BOJ2512_예산 {
    public static void main(String[] args) throws Exception {
        // ------ 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int budget = Integer.parseInt(br.readLine());

        // ------ 알고리즘 시작
        // Idea. N <= 100,000 (N^2은 안됨 => 이분탐색)
        int start = 0;
        int end = Integer.MAX_VALUE;
        int mid = 0;
        while (start <= end) {
            int sum = 0;
            mid = (start + end) / 2;
            for (int i = 0; i < N; i++) {
                if (arr[i] <= mid) {
                    sum += arr[i];
                    continue;
                }
                sum += mid;
            }

            if (sum < budget)
                start = mid + 1;
            else
                end = mid - 1;

        }
        System.out.println(end);
    }
}
