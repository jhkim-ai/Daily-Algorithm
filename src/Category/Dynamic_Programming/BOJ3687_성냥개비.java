package Category.Dynamic_Programming;

import java.util.*;
import java.io.*;

public class BOJ3687_성냥개비 {

    public static int T, N;
    public static long[] dp;
    public static void main(String[] arge) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);

        // 초기화
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;  // 예외 처리 해야함
        dp[7] = 8;
        dp[8] = 10; // (1 + 7) 에서 1은 존재하지 않기 때문에 입력 해야함.

        StringBuilder sb = new StringBuilder();
        for(int idx = 9; idx <= 100; ++idx){
            for(int n = 2; n <= 7; ++n){
                long num;
                if(n == 6) num = Long.parseLong(sb.append(dp[idx-n]).append(0).toString());
                else num = Long.parseLong(sb.append(dp[idx-n]).append(dp[n]).toString());
                dp[idx] = Math.min(dp[idx], num);
                sb.setLength(0);
            }
        }

        for(int t = 0; t < T; ++t){
            N = Integer.parseInt(br.readLine());
            sb.append(dp[N]).append(" ").append(maxValue()).append("\n");
        }
        System.out.println(sb);
    }

    public static String maxValue(){
        StringBuilder sb = new StringBuilder();
        int quotient = N/2;
        if(N % 2 == 0){
            for(int i = 0; i < quotient; ++i){
                sb.append("1");
            }
        }else {
            sb.append("7");
            for (int i = 1; i < quotient; ++i) {
                sb.append("1");
            }
        }

        return sb.toString();
    }
}
