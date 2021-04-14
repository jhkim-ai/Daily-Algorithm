package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_5656_벽돌깨기 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int T;
    static int N, W, H, total;
    static int[][] map;
    static int[][] copyMap;
    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            ans = Integer.MAX_VALUE;

            for (int y = 0; y < H; y++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < W; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    if(map[y][x] != 0)
                        total++;
                }
            }

            // 1. Column을 중복순열을 통하여 폭탄을 놓을 곳을 정한다.
            permutation(N, new int[N]);  // 중복순열
            sb.append(String.format("#%d %d\n", t, ans));
        }

        System.out.println(sb);
    }

    static void permutation(int cnt, int[] selected) {
        // 주의! 마지막 Test Case : 시간 초과 방지
        if(ans == 0){
            return;
        }
        // 2. 조합된 순열을 통하여 폭파 실행
        if (cnt == 0) {
            copyMap = new int[H][W];        // Map 복제
            for (int y = 0; y < H; y++) {
                copyMap[y] = map[y].clone();
            }

            for (int i = 0; i < N; i++) {
                Point top = findTop(selected[i]);   // 3. 해당 Column의 가장 첫 번째 폭탄위치를 찾는다.
                if(top == null) continue;           // 주의! top이 0이라면, null 이 표시된다.
                                                    // 만약, 0이라면, 벽돌을 폭파할 필요가 없다.

                bomb(top);                          // 4. 벽돌 폭파
                moveBrick();                        // 5. 남은 벽돌 옮기기
            }

            ans = Math.min(getCountOfBrick(), ans);
            return;
        }

        // 1-1. 중복순열 조합
        for (int i = 0; i < W; i++) {
            selected[selected.length - cnt] = i;
            permutation(cnt - 1, selected);
        }
    }

    // 3. 가장 위에 있는 벽돌 찾기
    static Point findTop(int x) {
        Point top = null;

        for (int y = 0; y < H; y++) {
            if (copyMap[y][x] != 0) {
                top = new Point(y, x, copyMap[y][x]);
                break;
            }
        }
        return top;
    }

    // 4. 벽돌 부시기
    static Queue<Point> q;
    static void bomb(Point start) {
        q = new LinkedList<>();     // 폭탄에 의해 깨질 벽돌 위치의 집합
        q.offer(start);

        while (!q.isEmpty()) {
            Point now = q.poll();
            int size = now.size;
            copyMap[now.y][now.x] = 0;       // 현 벽돌의 위치 0(폭파)표시

            if (size == 1) continue;         // 벽돌의 size가 1이면, 확산 X

            for (int d = 0; d < 4; d++) {    // 4방향 선택
                int ny, nx;

                // 각 직선 탐색에 대하여 1 ~ size-1 까지의 벽돌을 0(폭파)표시
                for (int len = 1; len < size; len++) {
                    ny = now.y + dy[d] * len;
                    nx = now.x + dx[d] * len;

                    // 유효성 검사 (valid check)
                    if (!isIn(ny, nx) || copyMap[ny][nx] == 0) continue;
                    // 새 벽돌을 Queue 에 저장
                    q.offer(new Point(ny, nx, copyMap[ny][nx]));
                }
            }
        }
    }

    // 5. 벽돌 옮기기
    static void moveBrick() {
        Queue<Integer> move = new LinkedList<>();
        // 열마다 탐색하여 옮김
        for (int x = 0; x < W; x++) {
            move.clear();   // 열을 옮길 때마다 초기화
            for (int y = H - 1; y >= 0; y--) {
                if (copyMap[y][x] != 0) {
                    move.offer(copyMap[y][x]);  // 아래서부터 위로 0이 아닌 수를 Queue에 저장
                    copyMap[y][x] = 0;          // 저장 후, 0으로 값을 변경
                }
            }
            for (int y = H - 1; y >= 0; y--) {    // 다시 아래서부터 Queue를 뽑아 벽돌을 이동한다.
                if (move.isEmpty()) break;
                copyMap[y][x] = move.poll();
            }
        }
    }

    // 6. 남은 벽돌 세기
    static int getCountOfBrick() {
        int cnt = 0;
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                if (copyMap[y][x] == 0) continue;
                cnt++;
            }
        }
        return cnt;
    }

    static void print() {
        System.out.println("============");
        for (int y = 0; y < H; y++) {
            for (int x = 0; x < W; x++) {
                System.out.print(copyMap[y][x] + " ");
            }
            System.out.println();
        }
    }

    static boolean isIn(int y, int x) {
        return y >= 0 && y < H && x >= 0 && x < W;
    }

    static class Point {
        int y;
        int x;
        int size;

        public Point(int y, int x, int size) {
            this.y = y;
            this.x = x;
            this.size = size;
        }
    }
}
