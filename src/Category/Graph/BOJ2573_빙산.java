package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ2573_빙산 {

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        // map 정보 입력
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

//        while (true) {
//            melt(); // 빙산 녹이기
////            getIceBerg(); // 빙산 덩어리 세기
//        }
        System.out.println(getIceBerg(1,1, new boolean[N][M]));
    }

    // bfs 를 이용한 각 빙산 당 바다와 접촉면 세기
    // 주의! Queue 를 이용하지 않으면, 이전에 감소된 빙산에 의해
    // 다음 빙산이 영향을 미칠 수 있기 때문에 조심해야 한다.
    public static void melt() {
        Queue<Point> q = new LinkedList<>();

        // map 을 돌며, 빙산을 탐색
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                if (map[y][x] == 0) continue; // 바다면 pass

                int count = 0; // 바다와 접촉한 면의 개수
                // 동서남북 탐색
                for (int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    // 새 좌표에 대한 유효성 검사 + 빙산일 경우 pass
                    if (!isIn(ny, nx) || map[ny][nx] != 0) continue;
                    count++; // 접촉한 바다면의 수
                }

                // 녹여야할 빙산의 좌표와 줄어야되는 높이를 Queue에 저장
                q.offer(new Point(y, x, count));
            }
        }

        // 선택된 빙산 녹이기
        while (!q.isEmpty()) {
            Point now = q.poll();
            map[now.y][now.x] -= now.melt;

            if (map[now.y][now.x] < 0) map[now.y][now.x] = 0;
        }
    }

    // 빙산의 덩어리 세기
    public static int getIceBerg(int y, int x, boolean[][] visited) {
        int n = 0;

        for (int d = 0; d < 4; ++d) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) continue;
            visited[ny][nx] = true;
            n += getIceBerg(ny, nx, visited);
        }

        return n;
    }

    // 배열 볌위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    // 위치(좌표) 저장
    static class Point {
        int y;
        int x;
        int melt;

        public Point(int y, int x, int melt) {
            this.y = y;
            this.x = x;
            this.melt = melt;
        }
    }
}
