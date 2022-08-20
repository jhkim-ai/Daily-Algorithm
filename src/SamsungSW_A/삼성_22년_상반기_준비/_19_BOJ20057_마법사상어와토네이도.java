package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _19_BOJ20057_마법사상어와토네이도 {
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy2 = {-1, 1, 1, -1};
    private static final int[] dx2 = {-1, -1, 1, 1};

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        run();
        System.out.println(ans);
    }

    public static void run() {
        int y = N / 2;
        int x = N / 2;

        int size = 1;
        int d = 0;

        outer:
        while(true) {
            for(int i = 0; i < size; ++i){
                y = y + dy[d];
                x = x + dx[d];
                if(!isIn(y, x)) break outer;
                scatter(y, x, d);
            }
            d = (d + 1) % 4;
            for(int i = 0; i < size; ++i){
                y = y + dy[d];
                x = x + dx[d];
                scatter(y, x, d);
            }
            d = (d + 1) % 4;
            ++size;
        }

    }

    public static void scatter(int y, int x, int d) {
        int totalSand = map[y][x];
        map[y][x] = 0;

        int _10 = (int)(totalSand * 0.1);
        int _7 = (int)(totalSand * 0.07);
        int _5 = (int)(totalSand * 0.05);
        int _2 = (int)(totalSand * 0.02);
        int _1 = (int)(totalSand * 0.01);

        totalSand -= (_10 + _7 + _2 + _1) * 2 + _5;
        int ny = y + dy[d];
        int nx = x + dx[d];
        if(!isIn(ny, nx)) ans += totalSand;
        else map[ny][nx] += totalSand;

        for(int i = 0; i < 2; ++i) {
            int nd = (d + i) % 4;
            ny = y + dy2[nd];
            nx = x + dx2[nd];
            if (!isIn(ny, nx)) ans += _10;
            else map[ny][nx] += _10;
        }

        // 7%
        for(int i = -1; i < 2; i += 2){
            int nd = (d + i + 4) % 4;
            ny = y + dy[nd];
            nx = x + dx[nd];
            if (!isIn(ny, nx)) ans += _7;
            else map[ny][nx] += _7;
        }
        //5%
        ny = y + dy[d]*2;
        nx = x + dx[d]*2;
        if (!isIn(ny, nx)) ans += _5;
        else map[ny][nx] += _5;

        // 2%
        for(int i = -1; i < 2; i += 2){
            int nd = (d + i + 4) % 4;
            ny = y + dy[nd]*2;
            nx = x + dx[nd]*2;
            if (!isIn(ny, nx)) ans += _2;
            else map[ny][nx] += _2;
        }

        // 1%
        for(int i = -2; i < 0; ++i){
            int nd = (d + i + 4) % 4;
            ny = y + dy2[nd];
            nx = x + dx2[nd];
            if (!isIn(ny, nx)) ans += _1;
            else map[ny][nx] += _1;
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
