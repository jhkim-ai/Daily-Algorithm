package Category.Implementation;

import java.util.*;
import java.io.*;

public class BOJ15683_감시 {

    static final int[] dy = {-1, 0, 1, 0};
    static final int[] dx = {0, 1, 0, -1};

    static int N, M, ans, wall;
    static int[][] map;
    static List<Point> cctvList;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvList = new ArrayList<>();
        ans = Integer.MAX_VALUE;

        // Map 설정
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());

                // 1. 정보 담기
                int nowInfo = map[y][x];
                if (nowInfo == 0) continue; // 0 이면 Pass
                if (nowInfo == 6) {         // 6 이면 벽 증가
                    wall++;
                    continue;
                }                           // CCTV 면 정보 담기
                cctvList.add(new Point(y, x, nowInfo));
            }
        }

        int countCCTV = cctvList.size();
        permutation(countCCTV, new int[countCCTV]);

        System.out.println(ans);
    }

    // 2. 중복 순열로 CCTV의 방향 부여
    static void permutation(int cnt, int[] selected) {
        // 2-2. 하나의 경우의 수에 대한 감시영역 구하기
        if (cnt == 0) {
            // 선택된 방향에 대한, 감시 가능한 영역 '구하기' + '비교하기'
            int area = surveillance(selected);
            int blind = (N * M) - area - wall - cctvList.size();
            ans = Math.min(ans, blind);
            return;
        }

        // 2-1. 각 CCTV의 방향 (상, 우, 하, 좌) 부여
        for (int i = 0; i < 4; ++i) {
            selected[selected.length - cnt] = i;
            permutation(cnt - 1, selected);
        }
    }

    // 2-3. CCTV 모든 종류의 감시 영억 얻기
    static int surveillance(int[] selected) {
        int area = 0; // 감시 가능한 영역
        boolean[][] visited = new boolean[N][M];        // 중복 체크

        // cctv :     [1, 1, 1, 5, 5, 1, 1, 1]
        // selected : [0, 2, 1, 0, 0, 2, 3, 0] (방향)
        // 모든 cctv에 대한 탐색
        for (int idx = 0; idx < cctvList.size(); ++idx) {
            Point CCTV = cctvList.get(idx);             // List 에서 CCTV 하나 얻기
            int cctvNumber = CCTV.cctv;                 // cctv 번호
            int dir = selected[idx];                    // 방향
            int[] redir = null;                         // cctv 번호에 따른 감시 방향향

            // 2-4. CCTV 종류에 대한 방향 설정
            if (cctvNumber == 1) {
                redir = new int[]{dir};
            } else if (cctvNumber == 2) {
                redir = new int[]{dir, (dir + 2) % 4};
            } else if (cctvNumber == 3) {
                redir = new int[]{dir, (dir + 1) % 4};
            } else if (cctvNumber == 4) {
                redir = new int[]{dir, (dir + 1) % 4, (dir + 3) % 4};
            } else if (cctvNumber == 5) {
                redir = new int[]{0, 1, 2, 3};
            }

            // 2-5 area 구하기
            area += getArea(CCTV, redir, visited);
        }

        return area;
    }

    // 해당 CCTV의 감시 영역 확인
    static int getArea(Point loc, int[] dir, boolean[][] visited) {
        int cnt = 0;

        // []dir 에 있는 모든 방향에 대하여 직선 탐색
        for (int d : dir) {
            int y = loc.y;  // 원본 백업
            int x = loc.x;

            while (true) {
                // 새 좌표 (벽을 제외하면 탐색 가능하기에 미리 선언)
                int ny = y + dy[d];
                int nx = x + dx[d];

                if (!isIn(ny, nx)) break;

                y = ny;
                x = nx;

                int nextInfo = map[ny][nx];

                if (nextInfo == 6) break;   // 벽이면 탐색 불가

                if (nextInfo >= 1 && nextInfo <= 5) continue; // 다른 CCTV면 통과
                if (visited[ny][nx]) continue;

                // 이동 가능 거리 증가
                cnt++;
                visited[ny][nx] = true;
            }
        }
        return cnt;
    }

    // Map 범위 체크
    static boolean isIn(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    static class Point {
        int y;
        int x;
        int cctv;

        public Point(int y, int x, int cctv) {
            this.y = y;
            this.x = x;
            this.cctv = cctv;
        }

        @Override
        public String toString() {
            return cctv + "";
        }
    }
}
