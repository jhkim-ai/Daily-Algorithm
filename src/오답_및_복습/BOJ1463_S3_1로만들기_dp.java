package 오답_및_복습;

import java.io.BufferedReader;
import java.io.StringReader;

public class BOJ1463_S3_1로만들기_dp {

    static int N;
    static int[] dp;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        // Bottom - Up (for 문 이용)
        for (int i = 2; i < N+1; i++) {
            dp[i] = dp[i-1] + 1;
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1);
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1);
        }

        System.out.println(dp[N]);
    }

    static String input = "10";
}
