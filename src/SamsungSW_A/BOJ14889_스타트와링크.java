package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {

    private static int N, ans;
    private static int[][] stats;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = Integer.MAX_VALUE;
        stats = new int[N][N];

        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                stats[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(N/2, new int[N/2], 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] teamStart, int startIdx){
        if(cnt == 0){
            int[] teamLink = makeLinkTeam(teamStart);
            int diff = calculate(teamStart);
            diff -= calculate(teamLink);
            ans = Math.min(ans, Math.abs(diff));
            return;
        }

        for(int i = startIdx; i < N; ++i){
            teamStart[teamStart.length - cnt] = i;
            combination(cnt-1, teamStart, i+1);
        }
    }

    public static int calculate(int[] team){
        int sum = 0;

        for(int player : team){
            for(int otherPlayer : team){
                sum += stats[player][otherPlayer];
            }
        }

        return sum;
    }

    public static int[] makeLinkTeam(int[] teamStart){
        boolean[] isTeamStart = new boolean[N];
        int[] teamLink = new int[N/2];
        int index = 0;

        for(int num : teamStart){
            isTeamStart[num] = true;
        }

        for(int i = 0; i < N; ++i){
            if(!isTeamStart[i]){
                teamLink[index++] = i;
            }
        }

        return teamLink;
    }
}
