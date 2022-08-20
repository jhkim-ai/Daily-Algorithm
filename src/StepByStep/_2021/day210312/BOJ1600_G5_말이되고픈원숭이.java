package StepByStep._2021.day210312;

import java.util.*;
import java.io.*;

public class BOJ1600_G5_말이되고픈원숭이 {

    static int K, Y, X;
    static int[][] map, ans;
    static int[] dy = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dx = {0, 0, -1, 1, -2, -1, 1, 2, -2, -1, 1, 2};

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        K = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new int[Y][X];
        ans = new int[Y][X];

        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        ans[0][0] = 1;
        bfs(0, 0);
        System.out.println(ans[Y - 1][X - 1] - 1);
    }

    static void bfs(int sy, int sx) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(sy, sx, 0));

        while (!q.isEmpty()) {
//            System.out.println("q: " + q);

            Point now = q.poll();

            for (int d = 0; d < dx.length; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                int nk = now.k;
                // 범위 유효성 검사 + 장애물 검사
                if (isIn(ny, nx) && map[ny][nx] != 1 && ans[ny][nx] == 0) {
                    if (d > 3 && nk < K) {
                        q.offer(new Point(ny, nx, nk + 1));
                        ans[ny][nx] = ans[now.y][now.x] + 1;
                        System.out.println("d: " + d + ", Point: " + new Point(ny, nx, nk + 1));
                    } else {
                        q.offer(new Point(ny, nx, nk));
                        ans[ny][nx] = ans[now.y][now.x] + 1;
                    }
                }
            }
            print(now.y, now.x);
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < Y && 0 <= x && x < X;
    }

    static class Point {
        int y;
        int x;
        int k;

        public Point(int y, int x, int k) {
            this.y = y;
            this.x = x;
            this.k = k;
        }

        @Override
        public String toString() {
            return "{" +
                    "y=" + y +
                    ", x=" + x +
                    ", k=" + k +
                    '}';
        }
    }

    static void print(int y, int x) {
        System.out.println("현위치 " + y + " " + x);

        for (int[] a : ans) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println("===================");
    }

    static String input = "1\n" +
            "4 4\n" +
            "0 0 0 0\n" +
            "1 0 0 0\n" +
            "0 0 1 0\n" +
            "0 1 0 0";
}
