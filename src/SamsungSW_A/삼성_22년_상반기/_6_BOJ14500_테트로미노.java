package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _6_BOJ14500_테트로미노 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ans = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                visited[y][x] = true;
                getMaxValue(3, y, x, map[y][x]);
                visited[y][x] = false;
                ans = Math.max(ans, cross(y, x, map[y][x]));
            }
        }
        System.out.println(ans);
    }

    public static void getMaxValue(int cnt, int y, int x, int sum){
        if(cnt == 0){
            ans = Math.max(ans, sum);
            return;
        }

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx) || visited[ny][nx]) continue;

            visited[ny][nx] = true;
            getMaxValue(cnt - 1, ny, nx, sum + map[ny][nx]);
            visited[ny][nx] = false;
        }
    }

    public static int cross(int y, int x, int sum){
        int minVal = Integer.MAX_VALUE;
        int cnt = 0;
        for(int d = 0; d < 4; ++d){
            if(cnt >= 2) return 0;

            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx)) {
                cnt++;
                continue;
            }

            minVal = Math.min(minVal, map[ny][nx]);
            sum += map[ny][nx];
        }

        if(cnt == 0) sum -= minVal;

        return sum;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
