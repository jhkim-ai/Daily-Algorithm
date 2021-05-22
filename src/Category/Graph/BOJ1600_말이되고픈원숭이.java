package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ1600_말이되고픈원숭이 {
    public static final int[] dy = {-1, 1, 0, 0, -1, -2, -2, -1, 1, 2, 2, 1};
    public static final int[] dx = {0, 0, -1, 1, -2, -1, 1, 2, 2, 1, -1, -2};

    public static int K, N, M;
    public static int[][] map;
    public static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][K + 1];

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = bfs(0, 0, 0, 0);
        System.out.println(ans);
    }

    public static Queue<Point> q;

    // bfs
    public static int bfs(int y, int x, int k, int dis) {
        q = new LinkedList<>();
        q.offer(new Point(y, x, k, dis));
        Arrays.fill(visited[y][x], true);   // 시작점 방문체크

        while (!q.isEmpty()) {
            Point now = q.poll();

            // 도착 시, 종료
            if (now.y == N - 1 && now.x == M - 1) {
                return now.dis;
            }

            search_4(now);  // 4방 탐색 (상,하,좌,우)
            if (now.k == K) continue; // 말처럼 움직인 횟수 == K 일 경우, 다음으로 넘어감;
            search_horse(now); // 8방 탐색 (말처럼 움직임)
        }
        return -1;
    }
    // 4방 탐색 (상,하,좌,우)
    public static void search_4(Point now){
        for (int d = 0; d < 4; ++d) {
            int ny = now.y + dy[d];
            int nx = now.x + dx[d];
            int nk = now.k;
            int nDis = now.dis;

            // 1. 배열 범위 over, 2. 이미 방문, 3. 장애물 시, 다음으로 넘어감
            if(isValidPoint(ny, nx, nk)) continue;

            // 방문 표시
            visited[ny][nx][nk] = true;

            // 다음 Queue에 삽입
            q.offer(new Point(ny, nx, nk, nDis + 1));
        }
    }
    // 8방 탐색 (말처럼 움직임)
    public static void search_horse(Point now){
        for (int d = 4; d < dy.length; ++d) {
            int ny = now.y + dy[d];
            int nx = now.x + dx[d];
            int nk = now.k + 1;
            int nDis = now.dis;

            // 1. 배열 범위 over, 2. 이미 방문, 3. 장애물 시, 다음으로 넘어감
            if(isValidPoint(ny, nx, nk)) continue;

            // 방문 표시
            visited[ny][nx][nk] = true;

            // 다음 Queue에 삽입
            q.offer(new Point(ny, nx, nk, nDis + 1));
        }
    }

    // 유효성 검사
    public static boolean isValidPoint(int y, int x, int k) {
        // 1. 배열 범위 over
        // 2. 이미 방문
        // 3. 장애물
        return (!isIn(y, x) || visited[y][x][k] || map[y][x] == 1);
    }

    // 배열 범위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    static class Point {
        int y;
        int x;
        int k;
        int dis;

        public Point(int y, int x, int k, int dis) {
            this.y = y;
            this.x = x;
            this.k = k;
            this.dis = dis;
        }
    }
}
