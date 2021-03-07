package StepByStep.day210219;

import java.io.*;
import java.util.*;

public class BOJ7576_S1_토마토 {

    static int N, M;
    static int[][] map;
    static int ans;
    static Queue<Point> q;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static boolean flag = false;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];

        q = new LinkedList<>();
        ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1)
                    q.offer(new Point(i, j));
            }
        }

        bfs();
        if(flag)
            System.out.println(-1);
        else {
            sb.append(ans-1);
            System.out.println(sb);
        }
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point p = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];

                if(isIn(ny, nx) && map[ny][nx] == 0){
                    map[ny][nx] = map[p.y][p.x] + 1;
                    q.offer(new Point(ny, nx));
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(map[y][x] == 0) {
                    flag = true;
                    return;
                }
                ans = Math.max(ans, map[y][x]);
            }
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static class Point {
        int x;
        int y;

        public Point(int y, int x) {
            this.x = x;
            this.y = y;
        }
    }

    static String input = "6 4\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 0\n" +
            "0 0 0 0 0 1";
}
