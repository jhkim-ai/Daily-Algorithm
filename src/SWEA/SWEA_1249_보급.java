package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1249_보급 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int T, N;
    static int[][] map, ex;
    static boolean[][] visited;
    static PriorityQueue<Point> pq;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visited = new boolean[N][N];
            ex = new int[N][N];

            for (int y = 0; y < N; y++) {
                String str = br.readLine();
                for (int x = 0; x < N; x++) {
                    map[y][x] = str.charAt(x) - '0';
                }
            }

            sb.append("#").append(t).append(" ").append(bfs(0, 0)).append("\n");
        }
        System.out.println(sb);
    }

    static int bfs(int y, int x) {
        pq = new PriorityQueue<>();
        pq.offer(new Point(y, x, map[y][x]));
        visited[y][x] = true;

        while (!pq.isEmpty()) {
//            System.out.println("test");
            print();
            Point now = pq.poll();

            if (now.y == N - 1 && now.x == N - 1)
                return now.cnt;

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (!isIn(ny, nx) || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                pq.offer(new Point(ny, nx, now.cnt + map[ny][nx]));
                ex[ny][nx] = ex[now.y][now.x] + map[ny][nx];
            }
        }
        return -1;
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static class Point implements Comparable<Point> {
        int y;
        int x;
        int cnt;

        public Point(int y, int x, int cnt) {
            this.y = y;
            this.x = x;
            this.cnt = cnt;
        }

        @Override
        public String toString() {
            return y + ":" + x;
        }

        @Override
        public int compareTo(Point p) {
            return Integer.compare(this.cnt, p.cnt);
        }
    }

    static void print(){
        System.out.println("=============");
        System.out.println(pq);
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(ex[y][x]);
            }
            System.out.println();
        }
    }

    static String input = "1\n6\n" +
            "011001\n" +
            "010100\n" +
            "010011\n" +
            "101001\n" +
            "010101\n" +
            "111010";
}
