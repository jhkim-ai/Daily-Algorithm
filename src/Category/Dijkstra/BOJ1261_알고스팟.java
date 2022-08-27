package Category.Dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1261_알고스팟 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static int N, M;
    private static int[][] cntDestroyWall;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cntDestroyWall = new int[N][M];

        for(int y = 0; y < N; ++y) {
            char[] c = br.readLine().toCharArray();
            for(int x = 0; x < M; ++x) {
                map[y][x] = c[x] - '0';
            }

            Arrays.fill(cntDestroyWall[y], Integer.MAX_VALUE);
        }

        dijkstra();
        System.out.println(cntDestroyWall[N-1][M-1]);
    }

    public static void dijkstra() {
        PriorityQueue<Point> pq = new PriorityQueue<>();
        Point start = new Point(0, 0, 0);
        boolean[][] visited = new boolean[N][M];

        cntDestroyWall[0][0] = 0;
        pq.offer(start);

        while(!pq.isEmpty()) {
            Point now = pq.poll();

            if(now.y == N-1 && now.x == M-1) break;
            if(visited[now.y][now.x]) continue;
            visited[now.y][now.x] = true;

            for(int d = 0; d < 4; ++d) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if(!isIn(ny, nx)) continue;

                int isWall = 0;
                if(map[ny][nx] == 1) isWall = 1;

                if(cntDestroyWall[ny][nx] > cntDestroyWall[now.y][now.x] + isWall) {
                    cntDestroyWall[ny][nx] = cntDestroyWall[now.y][now.x] + isWall;
                    pq.offer(new Point(ny, nx, cntDestroyWall[ny][nx]));
                }
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static class Point implements Comparable<Point>{
        int y;
        int x;
        int cntWall;

        public Point(int y, int x, int cntWall) {
            this.y = y;
            this.x = x;
            this.cntWall = cntWall;
        }

        @Override
        public int compareTo(Point p) {
            return Integer.compare(this.cntWall, p.cntWall);
        }
    }

    public static void print() {
        System.out.println("=======================");
        for(int y = 0; y < N; ++y){
            System.out.println(Arrays.toString(cntDestroyWall[y]));
        }
    }
}
