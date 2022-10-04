package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14889_스타트와링크 {
    private static int N, ans;

    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        ans = Integer.MAX_VALUE;

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(N/2, new int[N/2], 0);

        System.out.println(ans);
    }

    public static void combination(int cnt, int[] teamStart, int startIdx) {
        if(cnt == 0) {
            int sumTeamStart = 0;
            int sumTeamLink = 0;
            int[] teamLink = new int[N/2];
            boolean[] isTeamStart = new boolean[N];

            for(int i = 0; i < teamStart.length; ++i) {
                isTeamStart[teamStart[i]] = true;
            }
            for(int i = 0; i < teamStart.length - 1; ++i){
                for(int j = i+1; j < teamStart.length; ++j) {
                    sumTeamStart += map[teamStart[i]][teamStart[j]];
                    sumTeamStart += map[teamStart[j]][teamStart[i]];
                }
            }

            int idx = 0;
            for(int i = 0; i < N; ++i) {
                if(isTeamStart[i]) continue;
                teamLink[idx++] = i;
            }

            for(int i = 0; i < teamLink.length - 1; ++i){
                for(int j = i+1; j < teamLink.length; ++j) {
                    sumTeamLink += map[teamLink[i]][teamLink[j]];
                    sumTeamLink += map[teamLink[j]][teamLink[i]];
                }
            }

            ans = Math.min(ans, Math.abs(sumTeamLink - sumTeamStart));

            return;
        }

        for(int i = startIdx; i < N; ++i){
            teamStart[teamStart.length - cnt] = i;
            combination(cnt - 1, teamStart, i + 1);
        }
    }
}
