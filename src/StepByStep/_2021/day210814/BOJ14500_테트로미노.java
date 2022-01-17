package StepByStep.day210814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static boolean[][] visited;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = Integer.MIN_VALUE;
        visited = new boolean[N][M];

        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < M; ++x) {
                visited[y][x] = true;
                dfs(4, y, x, 0);
                exception(y, x);
                visited[y][x] = false;
            }
        }
        System.out.println(ans);
    }

    public static void dfs(int cnt, int y, int x, int sum) {
        if (cnt == 0) {
            ans = Math.max(ans, sum);
            return;
        }

        for (int d = 0; d < 4; ++d) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!isIn(ny, nx) || visited[ny][nx]) continue;
            visited[ny][nx] = true;
            dfs(cnt - 1, ny, nx, sum + map[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    public static void exception(int y, int x) {
        int sum = map[y][x];
        int min = Integer.MAX_VALUE;
        int count = 4;
        for (int d = 0; d < 4; ++d) {

            if(count < 3) return;

            int ny = y + dy[d];
            int nx = x + dx[d];

            if (!isIn(ny, nx)) {
                count--;
                continue;
            }

            min = Math.min(map[ny][nx], min);
            sum += map[ny][nx];
        }
        if(count == 4) sum -= min;
        ans = Math.max(sum, ans);
        return;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
