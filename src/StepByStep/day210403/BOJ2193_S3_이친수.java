package StepByStep.day210403;

import java.util.*;
import java.io.*;

public class BOJ2193_S3_이친수 {

    static int N;
    static long[][] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[91][2];

        // 기저 조건
        dp[1][0] = 0;
        dp[1][1] = 1;

        for (int i = 2; i < 91 ; i++) {
            dp[i][0] = dp[i-1][1] + dp[i-1][0]; // 마지막에 0이 왔을 때
            dp[i][1] = dp[i-1][0];              // 마지막에 1이 왔을 때
        }

        // 정답 (양끝의 범위 1, 90을 꼭 넣고 제출)
        System.out.println(dp[N][0]+dp[N][1]);
    }
}
