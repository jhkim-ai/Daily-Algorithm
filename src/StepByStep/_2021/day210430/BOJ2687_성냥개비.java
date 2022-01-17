package StepByStep.day210430;

import java.util.*;
import java.io.*;

public class BOJ2687_성냥개비 {

    static final int[] arr = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
    static long[] dp;
    static int T, N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // Idea. DP
        // 1. 최솟값은 dp를 이용해서 푼다.
        initDp();
        // 2. 최댓값은 getMaxValue()를 이용

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());
            long min = dp[N];
            String max = getMaxValue(N);
            sb.append(String.format("%d %s\n", min, max));
        }

        System.out.println(sb);
    }

    public static void initDp() {
        dp = new long[101];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        dp[3] = 7;
        dp[4] = 4;
        dp[5] = 2;
        dp[6] = 6;  // 처음에만 6 나머지는 0 이다.
        dp[7] = 8;
        dp[8] = 10; // dp[1] + dp[7] 에서 예외 발생 (성냥개비가 1개 남음)

        for (int i = 9; i <= 100; ++i) {
            // 2 ~ 7까지 하는 이유 : 일의 자리를 만들기 위해선 최소 2개(1) ~ 최대 7개(8)까지 가능하다.
            for (int j = 2; j <= 7; ++j) {
                String now = "";
                if (j == 6)
                    now = dp[i - j] + "0";
                else
                    now = dp[i - j] + Long.toString(dp[j]);
                dp[i] = Math.min(dp[i], Long.parseLong(now));
            }
        }
    }

    public static String getMaxValue(int n) {
        StringBuilder sb = new StringBuilder();

        // 몫(digit)만큼 길이(자릿수)가 생긴다.
        int len = n / 2;

        // 짝수면, 1111...
        if (n % 2 == 0) {
            for (int i = 1; i <= len; ++i) {
                sb.append("1");
            }
        }
        // 홀수면, 7111...
        else {
            sb.append("7");
            for (int i = 2; i <= len; ++i) {
                sb.append("1");
            }
        }

        return sb.toString();
    }
}
