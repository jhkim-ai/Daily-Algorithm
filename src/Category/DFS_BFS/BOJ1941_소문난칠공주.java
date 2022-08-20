package Category.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ1941_소문난칠공주 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans;
    private static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = 5;
        map = new char[N][N];
        for (int i = 0; i < N; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        dfs(7, new int[7], new boolean[N * N], 0, 0);
        System.out.println(ans);
    }

    public static void dfs(int cnt, int[] selected, boolean[] visited, int startIdx, int cntSom) {
        if (cnt == 0) {
            if (cntSom >= 4) {
                if (check(selected, visited)) {
                    ++ans;
                }
            }
            return;
        }

        for (int i = startIdx; i < N * N; ++i) {
            visited[i] = true;
            selected[selected.length - cnt] = i;

            if (map[i / N][i % N] == 'S') { // 솜파
                dfs(cnt - 1, selected, visited, i + 1, cntSom + 1);
            } else { // 연파
                dfs(cnt - 1, selected, visited, i + 1, cntSom);
            }

            visited[i] = false;
        }
    }

    public static boolean check(int[] selected, boolean[] visited) {
        int cnt = 1;
        int start = selected[0];
        boolean[] isValid = new boolean[N * N];
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        isValid[start] = true;

        while (!q.isEmpty()) {
            int now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now / N + dy[d];
                int nx = now % N + dx[d];

                if (!isIn(ny, nx) || isValid[ny * N + nx] || !visited[ny * N + nx]) {
                    continue;
                }
                isValid[ny * N + nx] = true;
                cnt++;
                q.offer(ny*N + nx);
            }
        }

        if(cnt == 7) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}
