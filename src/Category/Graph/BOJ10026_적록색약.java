package Category.Graph;

import java.io.*;

public class BOJ10026_적록색약 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, ans1, ans2;
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력
        N = Integer.parseInt(br.readLine());
        map = new char[N][];
        for (int i = 0; i < N; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        // 알고리즘 시작
        visited = new boolean[N][N]; // 방문 체크
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if (visited[y][x]) continue;
                dfs(y, x, map[y][x]); // dfs 탐색
                ans1++;
            }
        }

        // 적록색약일 경우, 'G'나'R'을 하나의 색으로 바꾼다.
        for (int y = 0; y < N; ++y) {
            for(int x = 0; x < N; ++x){
                if(map[y][x] == 'G') map[y][x] = 'R';
            }
        }
        visited = new boolean[N][N];
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                if (visited[y][x]) continue;
                dfs(y, x, map[y][x]);
                ans2++;
            }
        }

        // 정답 출력
        System.out.print(ans1 + " " + ans2);
    }

    // dfs 탐색
    public static void dfs(int y, int x, char c) {
        for (int d = 0; d < 4; ++d) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] != c) continue;
            visited[ny][nx] = true;
            dfs(ny, nx, c);
        }
    }

    // 배열 유효 범위 체크
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < N;
    }
}