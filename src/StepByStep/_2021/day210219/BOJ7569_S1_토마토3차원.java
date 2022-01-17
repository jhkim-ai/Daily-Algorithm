package StepByStep.day210219;

import java.io.*;
import java.util.*;

public class BOJ7569_S1_토마토3차원 {

    public static final int[] dz = {0, 0, 0, 0, 1, -1}; // 위, 아래
    public static final int[] dy = {-1, 1, 0, 0, 0, 0}; // 상, 하
    public static final int[] dx = {0, 0, -1, 1, 0, 0}; // 좌, 우

    public static int N, M, H;
    public static int[][][] map;
    public static boolean[][][] visited;
    public static Queue<Point> q;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][N][M];
        q = new LinkedList<>();
        visited = new boolean[H][N][M];

        // 입력
        for (int z = 0; z < H; ++z) {
            for (int y = 0; y < N; ++y) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; ++x) {
                    map[z][y][x] = Integer.parseInt(st.nextToken());
                    if (map[z][y][x] == 1) {
                        q.offer(new Point(z, y, x));    // 토마토를 q에 삽입
                        visited[z][y][x] = true;        // 토마토가 있는 위치는 방문 표시
                    }
                }
            }
        }

        // ========== 알고리즘 시작 ==========
        System.out.println(bfs());
    }

    // bfs 탐색을 이용한 최단거리 찾기기
   public static int bfs() {
        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 6; ++d) {
                int nz = now.z + dz[d];
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // 배열 범위를 벗어나거나 방문한 적이 있거나 토마토가 들어있지 않은 칸일 때
                if(!isIn(nz, ny, nx) || visited[nz][ny][nx] || map[nz][ny][nx] == -1) continue;

                // 유효성 검사를 마친 후, 인접한 토마토에 영향
                visited[nz][ny][nx] = true; // 방문 표시
                map[nz][ny][nx] = map[now.z][now.y][now.x] + 1; // 거리 증가
                q.offer(new Point(nz, ny, nx)); // q에 삽입
            }
        }

        // 정답 찾기
        int ans = Integer.MIN_VALUE;
        for (int z = 0; z < H; z++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    int day = map[z][y][x];
                    if(day == 0)
                        return -1;
                    ans = Math.max(day, ans);
                }
            }
        }
        return ans-1;
    }
    
    // 배열 범위 검사
    public static boolean isIn(int z, int y, int x) {
        return z >= 0 && y >= 0 && x >= 0 && z < H && y < N && x < M;
    }

    static class Point {
        int z;
        int y;
        int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }

    public static void print(int[][][] arr) {
        System.out.println("===============");
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    System.out.print(arr[i][j][k] + " ");
                }
                System.out.println();
            }
            System.out.println();
        }
    }
}
