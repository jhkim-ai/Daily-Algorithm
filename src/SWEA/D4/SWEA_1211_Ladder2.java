package SWEA.D4;

import java.util.*;
import java.io.*;

public class SWEA_1211_Ladder2 {

    public static final int N = 100;
    public static final int[] dy = {0, 0, 1};
    public static final int[] dx = {-1, 1, 0};

    public static int minDistance, ans;
    public static int dir;
    public static int[][] map;
    public static Point now;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // TestCase
        int T = 10;
        while (T-- > 0) {
            int t = Integer.parseInt(br.readLine());
            minDistance = Integer.MAX_VALUE;
            map = new int[N][N];
            dir = 2;
            ans = 0;

            // map 정보 입력
            for (int y = 0; y < N; ++y) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; ++x) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

            for (int x = 0; x < N; ++x) {
                if (map[0][x] == 0) continue; // 시작점이 아니라면, pass
                now = new Point(0, x);     // 시작점 위치 저장

                int getLen = findPath();      // 시작점으로부터 총 거리 get
                if(minDistance > getLen){     // 최솟값 비교
                    minDistance = getLen;     // 최솟값 갱신
                    ans = x;                  // x 위치(정답) 갱신
                }
            }
            sb.append(String.format("#%d %d\n", t, ans));
        }
        // 출력
        System.out.println(sb);
    }

    public static int findPath() {
        int len = 1;    // 이동거리

        while (true) {
            if (now.y == N - 1 && map[now.y][now.x] == 1) break; // 도착
            move(); // 이동
            len++;  // 이동거리 증가
        }

        return len;
    }

    public static void move() {
        // 아래 방향으로 내려갈 때마다 좌, 우 탐색
        if (dir == 2) {
            for (int d = 0; d < 2; ++d) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (!isIn(ny, nx)) continue;

                // 좌, 우로 갈 수 있을 시, 방향 전환
                if (map[ny][nx] == 1) dir = d;
            }
        }

        // 좌, 우로 움직일 때
        else {
            int ny = now.y + dy[2];
            int nx = now.x + dx[2];

            // 아래 방향으로 가는 길이 존재할 때
            if (map[ny][nx] == 1) dir = 2;
        }

        // 좌표 갱신
        now.y = now.y + dy[dir];
        now.x = now.x + dx[dir];
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
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
