package Category.Dynamic_Programming;

import java.io.*;
import java.util.*;

public class BOJ12865_G5_평범한배낭_지웅 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] w = new int[102];
        int[] v = new int[102];
        int[][] dp = new int[102][100002];

        for(int i = 1; i <= n; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            w[i] = Integer.parseInt(st.nextToken());
            v[i] = Integer.parseInt(st.nextToken());
        }

        for(int i = 1; i <= n; ++i){
            for(int j = 1; j <= k; ++j){
                dp[i][j] = dp[i-1][j];
                if(j >= w[i])
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-w[i]]+v[i]);
            }
        }

        for(int i = 0; i <= n; ++i){
            for(int j = 0; j <= k; ++j)
                System.out.print(dp[i][j] + "  ");
            System.out.println();
        }

        System.out.printf("%d\n", dp[n][k]);
    }
}
