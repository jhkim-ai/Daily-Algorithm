package 다시풀기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14501_퇴사 {

    private static int N, ans;
    private static int[][] schedules;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        schedules = new int[N + 1][2];
        ans = Integer.MIN_VALUE;

        for (int i = 1; i <= N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            schedules[i][0] = Integer.parseInt(st.nextToken()); // 작업 시간
            schedules[i][1] = Integer.parseInt(st.nextToken()); // 이익
        }

        for (int i = 1; i <= N; ++i) {
            dfs(i, 0, 0);
        }
        System.out.println(ans);
    }

    public static void dfs(int n, int sum, int before) {
        if(n-1 == N){
            ans = Math.max(ans, sum);
            return;
        }
        if (n-1 > N) {
            ans = Math.max(ans, sum - schedules[before][1]);
            return;
        }

        for (int i = n; i <= N; ++i) {
            dfs(i + schedules[i][0], sum + schedules[i][1], i);
        }
    }

}
