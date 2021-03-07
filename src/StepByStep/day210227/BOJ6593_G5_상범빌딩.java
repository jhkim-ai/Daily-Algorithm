package StepByStep.day210227;

import java.util.*;
import java.io.*;

public class BOJ6593_G5_상범빌딩 {

    static int H, Y, X;
    static char[][][] map;
    static int[][][] ans;
    static Point start, end;
    static int[] dh = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dx = {0, 0, 0, 0, 1, -1};
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            if(H==0 && Y==0 && X==0 )
                break;
            map = new char[H][Y][X];
            ans = new int[H][Y][X];

            for (int h = 0; h < H; h++) {
                for (int y = 0; y < Y; y++) {
                    String str = br.readLine();
                    for (int x = 0; x < X; x++) {
                        map[h][y][x] = str.charAt(x);
                        if (map[h][y][x] == 'S')
                            start = new Point(h, y, x);
                        if (map[h][y][x] == 'E')
                            end = new Point(h, y, x);
                    }
                }
                br.readLine();
            }

            bfs();

            if (ans[end.h][end.y][end.x] == 0)
                System.out.println("Trapped!");
            else
                System.out.printf("Escaped in %d minute(s).\n", ans[end.h][end.y][end.x]);
        }
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        while (!q.isEmpty()) {
            Point now = q.poll();
            if (end == now) {
                break;
            }
            for (int d = 0; d < 6; d++) {
                int nh = now.h + dh[d];
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if(isIn(nh, ny, nx) && (map[nh][ny][nx] == '.' || map[nh][ny][nx] == 'E') && ans[nh][ny][nx] == 0){
                    ans[nh][ny][nx] = ans[now.h][now.y][now.x] + 1;
                    q.offer(new Point(nh, ny, nx));
                }
            }
        }
    }

    static boolean isIn(int h, int y, int x) {
        return 0 <= h && h < H && 0 <= y && y < Y && 0 <= x && x < X;
    }

    static class Point {
        int h;
        int y;
        int x;

        public Point(int h, int y, int x) {
            this.h = h;
            this.y = y;
            this.x = x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return h == point.h && y == point.y && x == point.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(h, y, x);
        }
    }

    static void print() {
        System.out.println("start ==========");
        for (int h = 0; h < H; h++) {
            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    System.out.print(ans[h][y][x]);
                }
                System.out.println();
            }
            System.out.println(h+1 +"층 : ==========");
        }
        System.out.println("end ==========");
        System.out.println();
    }

    static String input = "3 4 5\n" +
            "S....\n" +
            ".###.\n" +
            ".##..\n" +
            "###.#\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "##.##\n" +
            "##...\n" +
            "\n" +
            "#####\n" +
            "#####\n" +
            "#.###\n" +
            "####E\n" +
            "\n" +
            "1 3 3\n" +
            "S##\n" +
            "#E#\n" +
            "###\n" +
            "\n" +
            "0 0 0";
}
