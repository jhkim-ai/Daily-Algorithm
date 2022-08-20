package StepByStep._2021.day211106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ16509_장군 {

    private static final int[] dy = {-1, -1, -1, -1, 1, 1, 1, 1};
    private static final int[] dx = {-1, 1, -1, 1, -1, 1, -1, 1};

    private static final int[] sy = {3, 3, 2, 2, 2, 2, 3, 3};
    private static final int[] sx = {2, 2, 3, 3, 3, 3, 2, 2};

    private static Point[] pieces;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        pieces = new Point[2];
        for (int i = 0; i < 2; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            pieces[i] = new Point(y, x);
        }

        System.out.println(bfs());
    }

    public static int bfs() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[10][9];
        Point elephant = pieces[0];
        visited[elephant.y][elephant.x] = true;
        q.offer(elephant);
        int size = 0;
        int ans = 0;
        int[][] map = new int[10][9];
        map[pieces[1].y][pieces[1].x] = 9;

        while (!q.isEmpty()) {
            size = q.size();
            ans++;
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                for (int d = 0; d < 8; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if (!isIn(ny, nx) || visited[ny][nx]) {
                        continue;
                    }

                    if (isKing(ny, nx)) {
                        return ans;
                    }
                    map[ny][nx] = ans;
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
            print(map);
        }

        return -1;
    }

    public static void print(int[][] map){
        System.out.println("========================");
        for (int y = 0; y < 10; y++) {
            for (int x = 0; x < 9; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
        System.out.println("========================");
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < 10 && x < 9;
    }

    public static boolean isKing(int y, int x){
        return y == pieces[1].y && x == pieces[1].x;
    }

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
