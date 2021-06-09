package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ14501_퇴사 {

    static class Work {
        int day;
        int profit;

        public Work(int day, int profit) {
            this.day = day;
            this.profit = profit;
        }
    }

    public static int N, ans;
    public static Work[] works;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        works = new Work[N + 1];

        // Schedule 입력
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int day = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());
            works[i] = new Work(day, profit);
        }
        ans = 0;
        for (int day = 1; day <= N; ++day) {

            Work work = works[day];
            int tmpDay = day + work.day - 1;
            if(tmpDay == N) ans = Math.max(work.profit, ans);
            dfs(tmpDay, work.profit);
        }

        System.out.println(ans);
    }

    public static void dfs(int startDay, int total) {

        if (startDay > N) return;
        ans = Math.max(ans, total);
        
        for (int nowDay = startDay + 1; nowDay <= N; ++nowDay) {
            Work work = works[nowDay];
            int endDay = nowDay + work.day - 1;

            int nowTotal = total + work.profit;
            

            dfs(endDay, nowTotal);
        }
    }
}
