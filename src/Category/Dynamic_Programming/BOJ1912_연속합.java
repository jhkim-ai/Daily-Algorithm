package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912_연속합 {

    private static int N;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int idx = 1; idx <= N; ++idx) {
            arr[idx] = Integer.parseInt(st.nextToken());
        }

        dp[1] = arr[1];
        for(int i = 2; i <= N; ++i) {
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
        }

        int ans = dp[1];
        for(int i = 2; i <= N; ++i) {
            ans = Math.max(ans, dp[i]);
        }

        System.out.println(ans);
    }
}
