package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15988_123더하기3 {

    private static int N, T;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        dp = new int[1000001];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i < dp.length; ++i){
            dp[i] = ((dp[i-1] + dp[i-2])%1000000009 + dp[i-3])%1000000009;
        }

        T = Integer.parseInt(br.readLine());
        for(int i = 0; i < T; ++i){
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
