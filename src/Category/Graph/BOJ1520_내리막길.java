package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ1520_내리막길 {

    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M, ans;
    public static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dp = new int[N][M];

        // map 읽기
        for (int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
                dp[y][x] = -1;
            }
        }
        // 출발
        System.out.println(dfs(0, 0));
        // print();
    }

    public static int dfs(int y, int x) {
        // 도착
        if (y == N - 1 && x == M - 1) {
            return 1;
        }
        // 이미 갔던 길이면 return
        if (dp[y][x] != -1){
            return dp[y][x];
        }
        // 방문 표시
        dp[y][x] = 0;

        for (int d = 0; d < 4; ++d) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            
            // 1. 범위를 벗어나거나, 2. 이미 방문했거나, 3. 현재보다 크거나
            if(!isIn(ny, nx) ||  map[y][x] <= map[ny][nx]) continue;

            // 모든 조건에 만족한다면, 1씩 증가
            dp[y][x] += dfs(ny, nx);
        }
        return dp[y][x];
    }
    
    // map 범위 검사 (ArrayIndexOutofRange)
    public static boolean isIn(int y, int x){
        return y >= 0 && y < N && x >= 0 && x < M;
    }

    public static void print(){
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(dp[y][x]+" ");
            }
            System.out.println();
        }
    }
}
