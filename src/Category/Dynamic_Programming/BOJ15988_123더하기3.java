package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ15988_123더하기3 {

    private static int T, N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        dp = new int[100001];
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 3;
        dp[4] = 7;
        for(int i = 5; i < dp.length; ++i){
            dp[i] = (((dp[i-1] + dp[i-2])%1000000009 + dp[i-3])%1000000009) % 1000000009;
        }
        T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; ++t){
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append("\n");
        }
        System.out.println(sb);
    }
}
