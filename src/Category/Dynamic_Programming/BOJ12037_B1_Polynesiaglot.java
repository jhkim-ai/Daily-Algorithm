package Category.Dynamic_Programming;

import java.io.*;
import java.util.*;

public class BOJ12037_B1_Polynesiaglot {

    static int T, C, V, L;
    static long[] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            C = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            dp = new long[L+1];
            dp[0] = 1;
            dp[1] = 1;
            sb.append("Case #").append(t).append(": ").append(fibo(L)).append("\n");
        }

        System.out.println(sb);
    }

    static long fibo(int n){
        if(dp[n] == 0)
            dp[n] = fibo(n-1) + fibo(n-2);

        return dp[n];
    }

}
