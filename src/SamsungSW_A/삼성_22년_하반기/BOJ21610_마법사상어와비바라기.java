package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ21610_마법사상어와비바라기 {

    private static final int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    private static final int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};

    private static int N, M;
    private static int[][] map;
    private static boolean[][] nowCloud;
    private static boolean[][] newCloud;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        nowCloud = new boolean[N][N];

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        nowCloud[N-1][0] = true;
        nowCloud[N-1][1] = true;
        nowCloud[N-2][0] = true;
        nowCloud[N-2][1] = true;

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            newCloud = new boolean[N][N];

            moveCloud(d, s);
            rain();
            copyWater();
            createCloud();
        }

        System.out.println(getRestWater());
    }

    public static int getRestWater() {
        int sum = 0;
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                sum += map[y][x];
            }
        }
        return sum;
    }
    public static void createCloud() {
        for(int y = 0; y < N; ++y) {
            nowCloud[y] = new boolean[N];
            for(int x = 0; x < N; ++x) {
                if(newCloud[y][x]) continue;
                if(map[y][x] < 2) continue;

                nowCloud[y][x] = true;
                map[y][x] -= 2;
            }
        }
    }

    public static void copyWater() {
        int[][] copyMap = new int[N][N];

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(!newCloud[y][x]) continue;
                int cnt = 0;
                for(int d = 1; d < 8; d += 2) {
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] <= 0) continue;
                    ++cnt;
                }

                copyMap[y][x] += cnt;
            }
        }

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(!newCloud[y][x]) continue;
                map[y][x] += copyMap[y][x];
            }
        }
    }

    public static void rain() {
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(!newCloud[y][x]) continue;
                map[y][x]++;
            }
        }
    }

    public static void moveCloud(int d, int s) {
        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x) {
                if(!nowCloud[y][x]) continue;

                int ny = ((y + dy[d] * s) % N + N) % N;
                int nx = ((x + dx[d] * s) % N + N) % N;

                newCloud[ny][nx] = true;
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
