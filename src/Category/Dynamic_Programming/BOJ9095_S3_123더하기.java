package Category.Dynamic_Programming;

import java.util.*;
import java.io.*;

public class BOJ9095_S3_123더하기 {

    static int T, N;
    static int[] dp = new int[11];
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        T = Integer.parseInt(br.readLine());
        top_down();
        // bottom_up();
    }

    // (1) Bottom_up 방식 (for 문)
    static void bottom_up() throws Exception{
        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
    }

    // (2) Top-down 방식 (Recursive 문)
    static void top_down() throws Exception{
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());
            sb.append(dp(N)).append("\n");
        }
        System.out.println(sb);
    }
    static int dp(int n){

        if (dp[n] == 0) {
            dp[n] = dp(n - 1) + dp(n - 2) + dp(n - 3);
        }

        return dp[n];
    }
}
