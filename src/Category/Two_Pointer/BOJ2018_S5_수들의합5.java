package Category.Two_Pointer;

import java.util.*;
import java.io.*;

public class BOJ2018_S5_수들의합5 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // ------- 알고리즘 ------- //
        // Idea. Two Pointer

        int left = 1;
        int right = 1;
        int ans = 1;    // 정답 (자기 자신 숫자 포함)
        
        while (left <= (N / 2)) {
            int sum = 0;
            // left ~ right 까지의 연속 합
            for (int i = left; i <= right; i++) {
                sum += i;
            }
            // 연속 합 sum 이 N과 같다면 정답횟수 증가
            if (sum == N) {
                left++;
                ans++;
            } 
            // 연속 합 sum 이 N보다 작다면 right 증가
            else if (sum < N) {
                right++;
            } 
            // 연속 합 sum 이 N보다 크면 left 증가
            else {
                left++;
            }
        }

        System.out.println(ans);
    }
}
