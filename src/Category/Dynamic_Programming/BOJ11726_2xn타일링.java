package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ11726_2xn타일링 {
    private static final int MOD = 10007;
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[1001];
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i < dp.length; ++i){
            dp[i] = (dp[i] + dp[i-2]) % MOD;
            dp[i] = (dp[i] + dp[i-1]) % MOD;
        }

        System.out.println(dp[N]);
    }
}
