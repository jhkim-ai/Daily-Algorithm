package StepByStep._2021.day210219;

import java.io.*;
import java.util.*;

public class BOJ1012_S2_유기농배추 {

    static int T, M, N, K;
    static int[][] map;
    static boolean[][] visited;

    // 상, 하, 좌, 우
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int cnt;
    static int ans;

    public static void main(String[] args) throws Exception {

        // -------- 데이터 입력 -------- //

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        // -------- Test Case -------- //

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            visited = new boolean[N][M];

            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                map[y][x] = 1;
            }

            // 정답 초기화
            ans = 0;
            
            // DFS 탐색
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[y][x] == 1 && !visited[y][x]) {
                        dfs(y, x);
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        
        // 정답 출력
        System.out.println(sb);
    }

    static void dfs(int y, int x) {

        // 방문 처리
        visited[y][x] = true;

        // 4방 탐색
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            // Index 유효 검사 후, 방문하지 않은 배추면 탐색
            if (isIn(ny, nx) && !visited[ny][nx] && map[ny][nx] == 1) {
                dfs(ny, nx);
            }
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static String input = "2\n" +
            "10 8 17\n" +
            "0 0\n" +
            "1 0\n" +
            "1 1\n" +
            "4 2\n" +
            "4 3\n" +
            "4 5\n" +
            "2 4\n" +
            "3 4\n" +
            "7 4\n" +
            "8 4\n" +
            "9 4\n" +
            "7 5\n" +
            "8 5\n" +
            "9 5\n" +
            "7 6\n" +
            "8 6\n" +
            "9 6\n" +
            "10 10 1\n" +
            "5 5";
}
