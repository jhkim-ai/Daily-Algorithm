package Category.Dynamic_Programming;

import java.io.*;
import java.util.*;

public class BOJ13301_B1_타일장식몰 {

    static int N;
    static long[] dp;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N+2];
        dp[0] = 1;
        dp[1] = 1;

        // (1). Bottom-Up 방식 (for 문)
        // dp_bottom_up();

        // (2). Top-Down 방식 (recursive)
        System.out.println(dp_top_down(N+1)*2);

    }

    // (1). Bottom-Up 방식 (for 문)
    static void dp_bottom_up(){
        for (int i = 2; i < N+2; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N+1]*2);
    }

    // (2). Top-Down 방식 (recursive)
    static long dp_top_down(int n){
        if(dp[n] == 0)
            dp[n] = dp_top_down(n-1) + dp_top_down(n-2);
        return dp[n];
    }
}
