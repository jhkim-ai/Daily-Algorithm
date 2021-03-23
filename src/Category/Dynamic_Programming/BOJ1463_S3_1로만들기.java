package Category.Dynamic_Programming;

import java.io.*;
import java.util.*;

public class BOJ1463_S3_1로만들기 {

    static int N;
    static int[] dp; // memoization

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];

        // Bottom - Up (for 문 이용)
        // ex. N = 6
        // 3가지 경우의 수
        // (1) N - 1
        // (2) N / 2
        // (3) N / 3
        for (int i = 2; i < N+1; i++) {
            dp[i] = dp[i-1] + 1;    // (1)
            if(i%2 == 0) dp[i] = Math.min(dp[i], dp[i/2]+1); // (2)
            if(i%3 == 0) dp[i] = Math.min(dp[i], dp[i/3]+1); // (3)
        }

        System.out.println(dp[N]);
    }

    static String input = "10";
}
