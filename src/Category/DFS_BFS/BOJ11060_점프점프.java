package Category.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11060_점프점프 {

    private static final int inf = Integer.MAX_VALUE;

    private static int N, ans;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        dp = new int[N+1];
        Arrays.fill(dp, inf);
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = 0;
        dp[1] = 0;
        for(int i = 1; i <= N; ++i){
            for(int j = 1; j <= arr[i]; ++j){
                int next = i + j;
                if(next > N){
                    break;
                }
                dp[next] = Math.min(dp[i] + 1, dp[next]);
            }
        }

        if(dp[N] == inf || dp[N] < 0) {
            dp[N] = -1;
        }
        System.out.println(dp[N]);
    }
}
