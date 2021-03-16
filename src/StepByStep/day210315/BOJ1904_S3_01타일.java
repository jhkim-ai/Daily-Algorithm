package StepByStep.day210315;

import java.util.*;
import java.io.*;

public class BOJ1904_S3_01타일 {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        N = Integer.parseInt(br.readLine());
        dp = new int[1000001];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <1000001 ; i++) {
            dp[i] = (dp[i-1] + dp[i-2])%15746;
        }

        System.out.println(dp[111]);
    }

    static String input = "4";
}
