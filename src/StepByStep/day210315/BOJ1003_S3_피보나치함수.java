package StepByStep.day210315;

import java.io.*;
import java.util.*;

public class BOJ1003_S3_피보나치함수 {

    static int T;
    static int N;
    static int[][] dp = new int[41][2];
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        dp[0][0] = 1; dp[0][1] = 0;
        dp[1][0] = 0; dp[1][1] = 1;
        for (int i = 2; i < 41; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0];
            dp[i][1] = dp[i-1][1] + dp[i-2][1];
        }

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N][0]).append(" ").append(dp[N][1]).append("\n");
        }
        System.out.println(sb);
    }

    static String input = "3\n" +
            "0\n" +
            "1\n" +
            "3";
}
