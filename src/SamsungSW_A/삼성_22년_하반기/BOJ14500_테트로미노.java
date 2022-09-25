package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14500_테트로미노 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static int[][] visitedMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visitedMap = new int[N][M];
        ans = Integer.MIN_VALUE;

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                visitedMap[y][x] = 1;
                combination(3, y, x, map[y][x]);
                visitedMap[y][x] = 0;
                ans = Math.max(ans, getCrossShape(y, x));
            }
        }

        System.out.println(ans);
    }

    public static void combination(int cnt, int y, int x, int sum) {
        if(cnt == 0){
            ans = Math.max(ans, sum);
            return;
        }

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];
            if(!isIn(ny , nx) || visitedMap[ny][nx] == 1) continue;

            visitedMap[ny][nx] = 1;
            combination(cnt - 1, ny, nx, sum + map[ny][nx]);
            visitedMap[ny][nx] = 0;
        }
    }

    public static int getCrossShape(int y, int x){
        int sum = map[y][x];
        int cnt = 4;
        int minVal = Integer.MAX_VALUE;

        for(int d = 0; d < 4; ++d){
            if(cnt < 3) return Integer.MIN_VALUE;

            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx)) {
                --cnt;
                continue;
            }

            sum += map[ny][nx];
            minVal = Math.min(minVal, map[ny][nx]);
        }
        if(cnt == 3) return sum;
        return sum - minVal;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
