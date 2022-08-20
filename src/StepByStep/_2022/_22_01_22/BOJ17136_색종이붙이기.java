package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17136_색종이붙이기 {

    private static int N, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 10;
        int[] cntPaper = {0, 5, 5, 5, 5, 5};
        map = new int[N][N];
        ans = Integer.MAX_VALUE;
        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 0, cntPaper, 0);
        if(ans == Integer.MAX_VALUE){
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static void dfs(int y, int x, int[] cntPaper, int cnt) {
        if (y == N - 1 && x == N) {
            ans = Math.min(ans, cnt);
            return;
        }
        if (x == N) {
            dfs(y + 1, 0, cntPaper, cnt);
            return;
        }
        if (cnt > ans) {
            return;
        }

        if (map[y][x] == 1) {
            for (int n = 5; n >= 1; --n) {
                if (isAttach(y, x, n) && cntPaper[n] > 0) {
                    attach(y, x, n, true);
                    cntPaper[n]--;
                    dfs(y, x + 1, cntPaper, cnt + 1);
                    cntPaper[n]++;
                    attach(y, x, n, false);
                }
            }
        } else {
            dfs(y, x + 1, cntPaper, cnt);
        }

    }

    // 현 자리에서부터 N x N 색종이를 붙일 수 있나?
    public static boolean isAttach(int y, int x, int size) {
        for (int ny = y; ny < y + size; ++ny) {
            for (int nx = x; nx < x + size; ++nx) {
                if (!isIn(ny, nx) || map[ny][nx] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    // 배열 범위 안에 드는가?
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }

    // 색종이 붙이는 or 떼는 함수
    // isAttach = true  -->  색종이 붙이기
    // isAttach = false -->  색종이 떼기
    public static void attach(int y, int x, int size, boolean isAttach) {
        int flag = isAttach ? 5+size : 1;
        for (int ny = y; ny < y + size; ++ny) {
            for (int nx = x; nx < x + size; ++nx) {
                map[ny][nx] = flag;
            }
        }
    }
}
