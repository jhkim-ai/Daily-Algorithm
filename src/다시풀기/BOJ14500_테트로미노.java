package 다시풀기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        ans = Integer.MIN_VALUE;

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                visited[y][x] = true;
                run(3, y, x, map[y][x]);
                visited[y][x] = false;
                ans = Math.max(getMaxVal(y, x), ans);
            }
        }
        System.out.println(ans);
    }

    public static void run(int cnt, int y, int x, int sum) {
        if (cnt == 0) {
            ans = Math.max(sum, ans);
            return;
        }

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!isIn(ny, nx) || visited[ny][nx]) {
                continue;
            }
            visited[ny][nx] = true;
            run(cnt - 1, ny, nx, sum + map[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    public static int getMaxVal(int y, int x) {
        int sum = 0;

        int count = 0;
        int minVal = Integer.MAX_VALUE;
        sum += map[y][x];
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!isIn(ny, nx)) {
                continue;
            }
            count++;
            sum += map[ny][nx];
            minVal = Math.min(minVal, map[ny][nx]);
        }

        if (count < 3) {
            return 0;
        } else if (count == 4) {
            return sum - minVal;
        } else {
            return sum;
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
