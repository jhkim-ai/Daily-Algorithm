package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16935_배열돌리기3 {


    private static int N, M, R;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        while (R-- > 0) {
            int d = Integer.parseInt(st.nextToken());
            switch (d) {
                case 1:
                    rotate1();
                    break;
                case 2:
                    rotate2();
                    break;
                case 3:
                    rotate3();
                    break;
                case 4:
                    rotate4();
                    break;
                case 5:
                    rotate5();
                    break;
                case 6:
                    rotate6();
                    break;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                sb.append(map[y][x]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

    public static void rotate1() {

        int idx = 0;
        int[] tmpMap = new int[N * M];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                tmpMap[idx++] = map[y][x];
            }
        }

        idx = 0;
        map = new int[N][M];
        for (int y = N - 1; y >= 0; --y) {
            for (int x = 0; x < M; ++x) {
                map[y][x] = tmpMap[idx++];
            }
        }
    }

    public static void rotate2() {
        int idx = 0;
        int[] tmpMap = new int[N * M];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                tmpMap[idx++] = map[y][x];
            }
        }

        idx = 0;
        map = new int[N][M];
        for (int y = 0; y < N; ++y) {
            for (int x = M - 1; x >= 0; --x) {
                map[y][x] = tmpMap[idx++];
            }
        }
    }

    public static void rotate3() {
        int idx = 0;
        int[] tmpMap = new int[N * M];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                tmpMap[idx++] = map[y][x];
            }
        }

        idx = 0;
        int tmp = N;
        N = M;
        M = tmp;
        map = new int[N][M];

        for (int x = M - 1; x >= 0; --x) {
            for (int y = 0; y < N; ++y) {
                map[y][x] = tmpMap[idx++];
            }
        }
    }

    public static void rotate4() {
        int idx = 0;
        int[] tmpMap = new int[N * M];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                tmpMap[idx++] = map[y][x];
            }
        }

        idx = 0;
        int tmp = N;
        N = M;
        M = tmp;
        map = new int[N][M];

        for (int x = 0; x < M; ++x) {
            for (int y = N - 1; y >= 0; --y) {
                map[y][x] = tmpMap[idx++];
            }
        }
    }

    public static void rotate5() {
        int[][] tmpMap = new int[N][M];
        int lenY = N / 2;
        int lenX = M / 2;

        for (int y = 0; y < lenY; ++y) {
            for (int x = 0; x < lenX; ++x) {
                tmpMap[y][x + lenX] = map[y][x];
            }
        }

        for (int y = 0; y < lenY; ++y) {
            for (int x = lenX; x < M; ++x) {
                tmpMap[y + lenY][x] = map[y][x];
            }
        }

        for (int y = lenY; y < N; ++y) {
            for (int x = lenX; x < M; ++x) {
                tmpMap[y][x - lenX] = map[y][x];
            }
        }

        for (int y = lenY; y < N; ++y) {
            for (int x = 0; x < lenX; ++x) {
                tmpMap[y - lenY][x] = map[y][x];
            }
        }

        map = new int[N][M];
        for (int y = 0; y < N; ++y) {
            map[y] = tmpMap[y].clone();
        }
    }

    public static void rotate6() {
        int lenY = N / 2;
        int lenX = M / 2;
        int[][] tmpMap = new int[N][M];

        for (int y = 0; y < lenY; ++y) {
            for (int x = 0; x < lenX; ++x) {
                tmpMap[y + lenY][x] = map[y][x];
            }
        }

        for (int y = 0; y < lenY; ++y) {
            for (int x = lenX; x < M; ++x) {
                tmpMap[y][x - lenX] = map[y][x];
            }
        }

        for (int y = lenY; y < N; ++y) {
            for (int x = lenX; x < M; ++x) {
                tmpMap[y - lenY][x] = map[y][x];
            }
        }

        for (int y = lenY; y < N; ++y) {
            for (int x = 0; x < lenX; ++x) {
                tmpMap[y][x + lenX] = map[y][x];
            }
        }

        map = new int[N][M];
        for (int y = 0; y < N; ++y) {
            map[y] = tmpMap[y].clone();
        }
    }
}

