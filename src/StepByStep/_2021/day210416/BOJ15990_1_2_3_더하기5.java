package StepByStep.day210416;

import java.util.*;
import java.io.*;

public class BOJ15990_1_2_3_더하기5 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[100001][4];
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[1][3] = 0;
        dp[2][1] = 0;
        dp[2][2] = 1;
        dp[2][3] = 0;
        dp[3][1] = 1;
        dp[3][2] = 1;
        dp[3][3] = 1;

        // 같은 수 두 번 연속 X
        for (int n = 4; n < dp.length; n++) {
            dp[n][1] = (dp[n-1][2] + dp[n-1][3])%1000000009;
            dp[n][2] = (dp[n-2][1] + dp[n-2][3])%1000000009;
            dp[n][3] = (dp[n-3][1] + dp[n-3][2])%1000000009;
        }

        // 1. 첫 번째 실수 : 최대값을 안 넣어봄
        // 2. 두 번째 실수 : 마지막 Sum 값이 10억9를 넘는 것을 확인안 함
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int N = Integer.parseInt(br.readLine());
            int sum = 0;
            for (int i = 1; i < 4; i++) {
                sum += dp[N][i];
                sum %= 1000000009;
            }
            sb.append(sum).append("\n");
        }

        System.out.print(sb);
    }
}
