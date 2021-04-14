import java.io.*;
import java.util.*;

public class Main {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M;

    static int[][] map;
    static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        q = new LinkedList<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 1) {
                    q.offer(new Point(y, x));
                }
            }
        }
        int day = bfs();
        if(day != -1)
            System.out.println(day-1);
        else
            System.out.println(day);
    }

    static int bfs() {
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if(!isIn(ny, nx) || map[ny][nx] != 0) continue;
                map[ny][nx] = map[now.y][now.x] + 1;
                q.offer(new Point(ny, nx));
            }
        }

        int day = Integer.MIN_VALUE;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(map[y][x] == 0) {
                    return -1;
                }
                day = Math.max(map[y][x], day);
            }
        }
        return day;
    }

    static boolean isIn(int y, int x){
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
