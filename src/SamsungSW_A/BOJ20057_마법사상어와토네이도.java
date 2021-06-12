package SamsungSW_A;

import java.io.*;
import java.util.*;

public class BOJ20057_마법사상어와토네이도 {

    public static final int[] dy = {0, 1, 0, -1, -1, 1, 1, -1};
    public static final int[] dx = {-1, 0, 1, 0, -1, -1, 1, 1};

    public static int N, len, dir;
    public static int outer;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dir = 0;
        outer = 0;

        // 입력
        for (int y = 0; y < N; y++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        circulation();
        System.out.println(outer);
    }

    public static void circulation() {
        int ny = N / 2;
        int nx = N / 2;
        len = 1;

        int idx = 0;

        while (true) {
            for (int i = 0; i < len; i++) {
//                if (idx > 3) return;
                if (len == N && i == len - 1) break;
                ny = ny + dy[dir];
                nx = nx + dx[dir];
                idx++;
                tornado(ny, nx);
            }

            if (len == N) break;
            dir = (dir + 1) % 4;

            for (int i = 0; i < len; i++) {
                ny = ny + dy[dir];
                nx = nx + dx[dir];
                tornado(ny, nx);
            }

            len++;
            dir = (dir + 1) % 4;
        }
    }


    // 토네이도 진행
    public static void tornado(int y, int x) {
        int ny, nx;
        int sand = map[y][x];
        System.out.println("시작점: " + y + " " + x);

        // 위, 아래
        int[] tmp = {(dir + 1) % 4, (dir + 3) % 4};
        for (int s = 1; s <= 2; s++) {
            for (int i = 0; i < tmp.length; i++) {
                ny = y + dy[tmp[i]] * s;
                nx = x + dy[tmp[i]] * s;

                // 격자 밖으로 나갈 시
                if (!isIn(ny, nx)) {
                    if (s == 1) outer += (int) (sand * 0.07);
                    if (s == 2) outer += (int) (sand * 0.02);
                    continue;
                }
                // 흩날림
                if (s == 1) map[ny][nx] += (int) (sand * 0.07);
                else map[ny][nx] += (int) (sand * 0.02);
            }
        }

        // 같은 방향
        for (int s = 1; s <= 2; s++) {
            ny = y + dy[dir] * s;
            nx = x + dx[dir] * s;

            // 격자 밖으로 나갈 시
            if (!isIn(ny, nx)) {
                if (s == 1) outer += (int) (sand * 0.55);
                if (s == 2) outer += (int) (sand * 0.05);
                continue;
            }
            // 흩날림
            if (s == 1) map[ny][nx] += (int) (sand * 0.73);
            else map[ny][nx] += (int) (sand * 0.05);
        }

        // 대각선 방향
        for (int s = 4; s < 8; s++) {
            int d = dir + s;
            if (d >= 8) d = (d % 8) + 4;

            ny = y + dy[d];
            nx = x + dx[d];

            // 격자 밖으로 나갈 시
            if (!isIn(ny, nx)) {
                if (s == 4 || s == 5) outer += (int) (sand * 0.1);
                if (s == 6 || s == 7) outer += (int) (sand * 0.01);
                continue;
            }
            // 흩날림
            if (s == 4 || s == 5) map[ny][nx] += (int) (sand * 0.1);
            else map[ny][nx] += (int) (sand * 0.01);
        }
        map[y][x] = 0;
        print();
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print() {
        System.out.println("=================");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
