package Category.Floyd_Warshall;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404_플로이드 {

    private static final int INF = 987654321;

    private static int N, M;
    private static int[][] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        // 1. init
        dist = new int[N + 1][N + 1];
        for(int y = 1; y <= N; ++y) {
            Arrays.fill(dist[y], INF);
            for(int x = 1; x <= N; ++x){
                if(y == x) dist[y][x] = 0;
            }
        }

        while (M-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            dist[from][to] = Math.min(dist[from][to], cost);
        }

        // 2. 최단 거리 구하기
        for(int m = 1; m <= N; ++m){
            for(int s = 1; s <= N; ++s) {
                for(int e = 1; e <= N; ++e) {
                    if(dist[s][e] > dist[s][m] + dist[m][e]) {
                        dist[s][e] = dist[s][m] + dist[m][e];
                    }
                }
            }
        }

        // 3. 정답 출력
        StringBuilder sb = new StringBuilder();
        for(int y = 1; y <= N; ++y) {
            for(int x = 1; x <= N; ++x) {
                if(dist[y][x] == INF) dist[y][x] = 0;
                sb.append(dist[y][x]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
