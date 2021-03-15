package StepByStep.day210315;

import java.util.*;
import java.io.*;

public class BOJ9461_S3_파도반수열 {

    static int T;
    static long[] dp;

    // 1 <= N <= 100
    // 항상 마지막 값을 넣어서 확인해보자
    // 주의! 큰 수가 들어가기에 int -> long 선언

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        dp = new long[101];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < 101; i++) {
            dp[i] = dp[i-2] + dp[i-3];
        }

        for (int i = 0; i < T; i++) {
            sb.append(dp[Integer.parseInt(br.readLine())]).append("\n");
        }

        System.out.println(sb);
    }

    static String input = "2\n" +
            "6\n" +
            "100";
}
