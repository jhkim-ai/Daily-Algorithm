package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ11053_가장_긴_증가하는_부분수열 {

    private static int N;
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        for(int now = 2; now <= N; ++now) {
            dp[now] = 1;
            for(int prev = 1; prev < now; ++prev) {
                if(arr[prev] < arr[now] && dp[now] <= dp[prev]){
                    dp[now] = dp[prev] + 1;
                }
            }
        }

        int ans = 0;
        for(int i : dp){
            ans = Math.max(ans, i);
        }

        System.out.println(ans);
    }
}
