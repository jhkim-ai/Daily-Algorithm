package Category.Dynamic_Programming;

import java.util.*;
import java.io.*;

public class BOJ2748_B1_피보나치수2 {

    static int N;
    static long[] dp; // Memoization

    public static void main(String[] args) throws Exception{

        // (1). Bottom-Up 방식 (for 문)
        // DP_Bottom_Up();

        // (2). Top-Down 방식 (Recursive)
        DP_Top_Down();
    }

    // (1). Bottom-Up 방식 (for 문)
    static void DP_Bottom_Up() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];

        dp[0] = 0;
        dp[1] = 1;

        // Bottom-Up 방식 (for 문)
        for (int i = 2; i < N+1; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        System.out.println(dp[N]);
    }

    // ***************************************************************************

    // (2). Bottom-Up 방식 (Recursive 문)
    static void DP_Top_Down() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new long[N+1];
        dp[0] = 0;
        dp[1] = 1;

        // (2) -1 fibo 함수를 이용한 재귀
        System.out.println(fibo(N));
    }
    // (2) - 2 재귀적 dp 접근
    static long fibo(int n){
        if(n == 0)
            return 0;
        if(dp[n] == 0)
            dp[n] = fibo(n-1) + fibo(n-2);
        return dp[n];
    }
}
