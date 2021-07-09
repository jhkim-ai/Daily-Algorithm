package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ9663_N_Queen {

    private static final int[] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    private static final int[] dx = {0, 0, -1, 1, -1, 1, 1, -1};

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        permutation(0, 0, N, new int[N][N]);
        System.out.println(ans);
    }

    public static void permutation(int startY, int startX, int cnt, int[][] visited) {
        if (cnt == 0) {
            print(visited);
            ++ans;
            return;
        }
        if (startX == N) {
            ++startY;
            startX = 0;
        }
        for (int y = startY; y < N; ++y) {
            for (int x = startX; x < N; ++x) {
                if (visited[y][x] != 0) continue;
//                System.out.println(y + " " + x);
//                setVisited(cnt, y, x, visited);
//                print(visited);
                visited[y][x] = cnt;
                permutation(y, x + 1, cnt - 1, visited);
                visited[y][x] = 0;
//                unSetVisited(cnt, y, x, visited);
            }
        }
    }

    public static int index;
    public static void setVisited(int cnt, int y, int x, int[][] visited) {
        for (int d = 0; d < 8; ++d) {
            int ny = y, nx = x;
            while (isIn(ny, nx)) {
                if (visited[ny][nx] == 0) visited[ny][nx] = cnt;
                ny += dy[d];
                nx += dx[d];
            }
        }
    }

    public static void unSetVisited(int cnt, int y, int x, int[][] visited) {
        for (int d = 0; d < 8; ++d) {
            int ny = y, nx = x;
            while (isIn(ny, nx)) {
                if (visited[ny][nx] == cnt) visited[ny][nx] = 0;
                ny += dy[d];
                nx += dx[d];
            }
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    public static void print(int[][] visited){
        System.out.println("==========");
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                System.out.print(visited[y][x]);
            }
            System.out.println();
        }
    }
}
