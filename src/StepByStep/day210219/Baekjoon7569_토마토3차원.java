package StepByStep.day210219;

import java.io.*;
import java.util.*;

public class Baekjoon7569_토마토3차원 {

    static int N, M, H;
    static int[][][] map;
    static int ans;
    static Queue<Point> q;
    static int[] dy = {-1, 1, 0, 0, 0, 0};
    static int[] dx = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, 1, -1};

    static boolean flag = false;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N][M];

        q = new LinkedList<>();
        ans = 0;
        for (int h = 0; h < H; h++) {
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[h][i][j] = Integer.parseInt(st.nextToken());
                    if (map[h][i][j] == 1)
                        q.offer(new Point(i, j, h));
                }
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

            for (int d = 0; d < 6; d++) {
                int nh = p.h + dh[d];
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];

                if(isIn(ny, nx, nh) && map[nh][ny][nx] == 0){
                    map[nh][ny][nx] = map[p.h][p.y][p.x] + 1;
                    q.offer(new Point(ny, nx, nh));
                }
            }
        }
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (map[h][y][x] == 0) {
                        flag = true;
                        return;
                    }
                    ans = Math.max(ans, map[h][y][x]);
                }
            }
        }
    }

    static boolean isIn(int y, int x, int h) {
        return 0 <= y && y < N && 0 <= x && x < M && 0<= h && h < H;
    }

    static class Point {
        int h;
        int x;
        int y;

        public Point(int h, int y, int x) {
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    static String input = "5 3 2\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 0 0 0\n" +
            "0 0 1 0 0\n" +
            "0 0 0 0 0";
}
