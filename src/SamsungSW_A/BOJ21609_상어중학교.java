package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ21609_상어중학교 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, score;
    private static int maxRow, maxCol;
    private static int maxCntBlock, maxCntRainbowBlock;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // Simulation 시작
        while (run()) {
            print();
            System.out.println("##############################");
        }
        System.out.println(score);
    }

    public static boolean run() {

        // Step 1. 가장 큰 블록 그룹 찾기
        maxCntBlock = Integer.MIN_VALUE; // 가장 큰 블록 그룹의 블록 수
        maxCntRainbowBlock = Integer.MIN_VALUE;
        maxRow = Integer.MIN_VALUE;
        maxCol = Integer.MIN_VALUE;
        boolean[][] blockGroup = null;

        // 가장 큰 블록 그룹 구하기
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] >= 1) {
                    boolean[][] tmp = getGroup(y, x);
                    if (tmp != null) {
                        blockGroup = tmp;
                    }
                }
            }
        }

        // Group 을 구할 수 없을 경우, 종료
        if (blockGroup == null) {
            return false;
        }

        // Step 2. 그룹의 모든 블록 제거 + B^2 점 획득
        removeBlockGroup(blockGroup);
        score += maxCntBlock * maxCntBlock;

        System.out.println("======================");
        System.out.println("블록제거");
        print();

        // Step 3. 중력 작용
        System.out.println("======================");
        System.out.println("중력");
        runGravity();
        print();

        // Step 4. 90도 반시계 회전
        System.out.println("======================");
        System.out.println("회전");
        rotate();
        print();

        // Step 5. 중력 작용
        System.out.println("======================");
        System.out.println("중력");
        runGravity();
        print();
        return true;
    }

    // bfs 를 이용한 Group 구하기
    public static boolean[][] getGroup(int y, int x) {
        int color = map[y][x];
        int cntBlock = 1;
        int rainbowBlock = 0;
        boolean[][] visited = new boolean[N][N];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // (범위 밖 + 검은색 블록 + 이미 방문)일 경우 Pass
                if (!isIn(ny, nx) || isBlack(ny, nx) || visited[ny][nx]) {
                    continue;
                }

                // 무지개 블록이 아니고, 같은 색의 블록이 아닐 경우 pass
                if (map[ny][nx] > 0 && map[ny][nx] != color) {
                    continue;
                }

                if (map[ny][nx] == 0) { // 무지개 Block count 하기
                    rainbowBlock++;
                }

                visited[ny][nx] = true;
                q.offer(new Point(ny, nx));
                cntBlock++;
            }
        }

        if (cntBlock < 2) { // 전체 블록 수가 2개 미만일 경우 불가
            return null;
        }

        // 기준 블록 좌표 구하기
        int row = -1;  // 기준 Block 의 행
        int col = -1;  // 기준 Block 의 열
        outer:
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이중 for 문을 돌며
                // Rainbow Block 이 아니면서 가장 먼저 만나는 색깔 Block 이 기준 Block 이 된다.
                if (visited[i][j] && map[i][j] != 0) {
                    row = i;
                    col = j;
                    break outer;
                }
            }
        }

        if (maxCntBlock < cntBlock) { // 가장 큰 블록 그룹.
            maxCntBlock = cntBlock;
            maxRow = row;
            maxCol = col;
            maxCntRainbowBlock = rainbowBlock;
        } else if (maxCntBlock == cntBlock) {        // 가장 큰 블록 그룹이 여러개일 경우,
            if (maxCntRainbowBlock < rainbowBlock) { // rainbow block 이 많은 그룹으로 선택.
                maxCntRainbowBlock = rainbowBlock;
                maxRow = row;
                maxCol = col;
            } else if (maxCntRainbowBlock == rainbowBlock) { // rainbow Block 수가 같을 시,
                if (maxRow < row) {                          // 기준 Block 의 행이 큰 그룹으로
                    maxRow = row;
                    maxCol = col;
                } else if (maxRow == row) { // 기준 Block 의 행이 같을 시,
                    if (maxCol < col) {     // 기준 Block 의 열이 큰 그룹으로
                        maxCol = col;
                    } else {
                        return null;
                    }
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } else { // 작다면 pass
            return null;
        }
        return visited;
    }

    // Block 제거
    public static void removeBlockGroup(boolean[][] blockGroup) {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (blockGroup[y][x]) {
                    map[y][x] = -2; // -2 = 빈 공간
                }
            }
        }
    }

    // 중력 작용 (가장 어려운 부분)
    // Idea. 밑바닥을 나타내는 변수를 만들어 밑바닥을 갱신하고 다른 색깔 Block 들은 이동하자.
    public static void runGravity() {
        for (int x = 0; x < N; ++x) {
            int bottom = N;
            for (int y = N - 1; y >= 0; --y) { // 검정색 Block 시, 밑바닥을 현 위치(y)로 갱신
                if (isBlack(y, x)) {
                    bottom = y;
                    continue;
                }
                if (map[y][x] == -2) { // 빈 공간(-2)은 pass
                    continue;
                }

                if (map[y][x] >= 0) { // Color Block 은 설정된 밑바닥 위[bottom -1]로 이동
                    if (bottom - 1 != y) {
                        map[bottom - 1][x] = map[y][x];
                        map[y][x] = -2;
                    }
                    bottom--; // 밑바닥 갱신
                }
            }
        }
    }

    // 반시계 90도 회전
    public static void rotate() {
        List<Integer>[] rows = new ArrayList[N]; // 임시 List 에 다 저장 후, map 에 덮어쓰기

        for (int i = 0; i < N; i++) { // 임시 List 생성
            rows[i] = new ArrayList<>();
        }

        for (int y = 0; y < N; ++y) { // 임시 List 에 row 별로 저장
            for (int x = 0; x < N; x++) {
                rows[y].add(map[y][x]);
            }
        }

        for (int x = 0; x < N; x++) { // 임시 List 를 col 별로 아래에서 위로 map 갱신
            for (int y = N - 1; y >= 0; --y) {
                map[y][x] = rows[x].get(N - 1 - y);
            }
        }
    }

    // 검은색 Block 확인 method
    public static boolean isBlack(int y, int x) {
        return map[y][x] == -1;
    }

    // 배열 범위 확인 method
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void print() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(String.format("%4d", map[y][x]));
            }
            System.out.println();
        }
        System.out.println("=================");
    }
}
