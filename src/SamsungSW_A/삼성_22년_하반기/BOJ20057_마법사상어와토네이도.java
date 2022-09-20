package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ20057_마법사상어와토네이도 {

    private static final int M = 8;
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for(int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        start();
        System.out.println(ans);
    }

    public static void start() {
        int y = N / 2;
        int x = N / 2;
        int ny = y;
        int nx = x;

        int len = 1;
        int d = 6;

        outer:
        while(true) {
            for(int i = 0; i < len; ++i) {
                ny += dy[d];
                nx += dx[d];

                if(!isIn(ny, nx)) break outer;
                scatter(ny, nx, d);
            }

            d = ((d - 2) % M + M) % M;

            for(int i = 0; i < len; ++i) {
                ny += dy[d];
                nx += dx[d];

                if(!isIn(ny, nx)) break outer;
                scatter(ny, nx, d);
            }

            d = ((d - 2) % M + M) % M;
            ++len;
        }
    }

    public static void scatter(int y, int x, int d) {
        int partialSum = 0;
        int partialSand = 0;
        int totalSand = map[y][x];
        map[y][x] = 0;

        int ny = y + dy[d] * 2;
        int nx = x + dx[d] * 2;

        partialSand = (int)(totalSand * 0.05);

        if(isIn(ny, nx)) map[ny][nx] += partialSand;
        else ans += partialSand;

        partialSum += partialSand;

        partialSum += calculate(y, x, d, totalSand, 3, 0.01, 1);
        partialSum += calculate(y, x, d, totalSand, 2, 0.07, 1);
        partialSum += calculate(y, x, d, totalSand, 2, 0.02, 2);
        partialSum += calculate(y, x, d, totalSand, 1, 0.1, 1);

        ny = y + dy[d];
        nx = x + dx[d];

        if(isIn(ny, nx)) map[ny][nx] += totalSand - partialSum;
        else ans += totalSand - partialSum;
    }

    public static int calculate(int y, int x, int d, int totalSand, int D, double rate, int s) {
        int nd = (d + D) % M;
        int ny = y + dy[nd] * s;
        int nx = x + dx[nd] * s;

        int partialSum = 0;
        int partialSand = (int)(totalSand * rate);

        if(isIn(ny, nx)) map[ny][nx] += partialSand;
        else ans += partialSand;

        partialSum += partialSand;

        nd = (((d - D) % M) + M) % M;
        ny = y + dy[nd] * s;
        nx = x + dx[nd] * s;

        if(isIn(ny, nx)) map[ny][nx] += partialSand;
        else ans += partialSand;

        partialSum += partialSand;

        return partialSum;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}

