package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ17144_미세먼지안녕 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, T;
    private static int[] filter;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == -1) filter = new int[]{y, x};
            }
        }

        while(T-- > 0) {
            spread();
            blow();
            print(map);
        }
    }

    public static void spread() {
        int[][] tmp = new int[N][M];

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < M; ++x) {
                tmp[y][x] += map[y][x];
                if(map[y][x] == -1 || map[y][x] == 0) continue;
                int volume = map[y][x] / 5;

                for(int d = 0; d < 4; ++d) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || isFilter(ny, nx)) continue;

                    tmp[ny][nx] += volume;
                    tmp[y][x] -= volume;
                }
            }
        }

        for(int y = 0; y < N; ++y) {
            map[y] = tmp[y].clone();
        }
    }

    public static void blow() {
        counterClockwise(filter[0], filter[1]);
        clockwise(filter[0] + 1, filter[1]);
    }

    public static void counterClockwise(int y, int x) {
        for(int i = y-1; y > 0; --y) {
            map[i][0] = map[i-1][0];
        }

        for(int i = 0; i < M-1; ++i) {
            map[0][i] = map[0][i+1];
        }

        for(int i = 0; i < y; ++i) {
            map[M-1][i] = map[M-1][i+1];
        }

        for(int i = M-1; i > 1; --i) {
            map[N-1][i] = map[N-1][i-1];
        }

        map[y][1] = 0;
    }

    public static void clockwise(int y, int x) {
        for(int i = y+1; i < N-1; ++i) {
            map[i][0] = map[i+1][0];
        }

        for(int i = 0; i < M-1; ++i) {
            map[0][i] = map[0][i+1];
        }

        for(int i = N-1; i > y; --i) {
            map[M-1][i] = map[M-1][i-1];
        }

        for(int i = M-1; i > 0; --i) {
            map[y][i] = map[y][i-1];
        }

        map[y][1] = 0;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static boolean isFilter(int y, int x) {
        return map[y][x] == -1;
    }

    public static void print(int[][] map) {
        System.out.println("=================");
        for(int y = 0; y < N; ++y) {
            System.out.println(Arrays.toString(map[y]));
        }
        System.out.println("=================");
    }
}
