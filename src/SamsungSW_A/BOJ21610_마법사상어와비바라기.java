package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ21610_마법사상어와비바라기 {

    public static final int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    public static final int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    public static int N, M;
    public static Command[] commands;
    public static int[][] map;

    public static boolean[][] isDisappeared;
    public static Queue<Cloud> clouds;
    public static Queue<Cloud> isIncrement;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        commands = new Command[M];
        map = new int[N][N];
        clouds = new LinkedList<>();

        // map 정보 입력
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령어 정보 입력
        for (int q = 0; q < M; q++) {
            st = new StringTokenizer(br.readLine());
            commands[q] = new Command(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // ============ 알고리즘 시작 ============ //

        // 0. 초기 구름 생성
        initCloud();

        for (int q = 0; q < M; q++) {
            // 1. 모든 구름 이동 + 2. 바구니의 물의양 증가 + 3.구름이 모두 사라진다.
            moveCloud(commands[q]);
            // 4. 물복사버그 마법
            copyWater();
            // 5. 구름 생성
            createCloud();
        }
        System.out.println(getTotalWater());
    }

    // 0. 초기 구름 생성 (init 구름)
    public static void initCloud() {
        for (int y = N - 2; y < N; y++) {
            for (int x = 0; x < 2; x++) {
                clouds.offer(new Cloud(y, x));
            }
        }
    }

    // 1. 모든 구름 이동 + 2. 바구니의 물의양 +1 + 3.구름이 모두 사라진다.
    public static void moveCloud(Command command) {
        int d = command.d; // 입력된 방향
        int s = command.s; // 입력된 속력
        isDisappeared = new boolean[N][N]; // 4번에서 사용하기 위해 구름이 만들어진 곳인지 check
        isIncrement = new LinkedList<>();

        // 1. Queue 에 있는 모든 구름 이동
        while (!clouds.isEmpty()) {
            Cloud now = clouds.poll(); // 3. 구름 사용과 동시에 사라짐

            // 구름 이동
            int ny = (now.y + dy[d] * s) % N;
            int nx = (now.x + dx[d] * s) % N;

            if (ny < 0) ny += N;
            if (nx < 0) nx += N;

            // 2. 바구니의 물의양 + 1
            map[ny][nx]++;
            isDisappeared[ny][nx] = true;
        }
    }

    // 4. 물복사버그 마법
    public static void copyWater() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {

                // 구름이 없었던 곳이면, pass
                if (!isDisappeared[y][x]) continue;

                // 물이 증가한 칸만
                // 대각선 방향으로 거리가 1인 칸에 물이 있는 바구니 수 탐색
                int count = 0;
                for (int d = 2; d < 9; d += 2) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if (!isIn(ny, nx)) continue;
                    if (map[ny][nx] > 0) ++count;
                }

                // 물이 있는 바구니 수만큼 물의 양 증가
                map[y][x] += count;
            }
        }
    }

    // 5. 구름 생성
    public static void createCloud() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (isDisappeared[y][x]) continue;
                if (map[y][x] <= 1) continue;
                clouds.offer(new Cloud(y, x));
                map[y][x] -= 2;
            }
        }
    }

    // 5. 남은 바구니의 물의 양
    public static int getTotalWater() {
        int sum = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                sum += map[y][x];
            }
        }
        return sum;
    }

    // 배열 버위 검사
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print() {
        System.out.println("===========");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    static class Command {
        int d;
        int s;

        public Command(int d, int s) {
            this.d = d;
            this.s = s;
        }

        @Override
        public String toString() {
            return "Command{" +
                    "d=" + d +
                    ", s=" + s +
                    '}';
        }
    }

    static class Cloud {
        int y;
        int x;

        public Cloud(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "Cloud{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
}
