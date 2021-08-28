package Category.Dynamic_Programming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ15486_퇴사2 {

    private static int N;
    private static int[] dp;
    private static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+2][2];
        dp = new int[N+2];

        for(int i = 1; i <= N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int day = Integer.parseInt(st.nextToken());
            int pay = Integer.parseInt(st.nextToken());
            arr[i][0] = day;
            arr[i][1] = pay;
        }

        int maxVal = 0;
        for(int i =1; i <= N+1; ++i){
            maxVal = Math.max(maxVal, dp[i]); // 내가 가진게 높냐, 쌓아온 것이 높냐

            int day = arr[i][0];
            int pay = arr[i][1];
            if(i+day > N + 1){
                continue;
            }

            dp[i+day] = Math.max(maxVal + pay, dp[i+day]);
        }
        System.out.println(maxVal);

    }

}
