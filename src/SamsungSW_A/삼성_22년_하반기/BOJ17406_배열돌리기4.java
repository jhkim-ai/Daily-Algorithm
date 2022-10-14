package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ17406_배열돌리기4 {

    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, K, ans;
    private static int[][] map;
    private static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        info = new int[K][3];
        ans = Integer.MAX_VALUE;

        for(int y = 0; y < N; ++y) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            info[i] = new int[]{r, c, s};
        }

        permutation(K, new int[K], new boolean[K]);
        System.out.println(ans);
    }

    public static void rotate(int i, int[][] copyMap) {
        boolean[][] visited = new boolean[N][M];
        int r = info[i][0];
        int c = info[i][1];
        int s = info[i][2];

        int startY = r - s;
        int startX = c - s;
        int endY = r + s;
        int endX = c + s;

        int size = (startY + endY) / 2;

        for(int cnt = 0; cnt < size; ++cnt) {
            int sy = startY + cnt;
            int sx = startX + cnt;
            int ey = endY - cnt;
            int ex = endX - cnt;
            int d = 0;

            if(sy == ey || sx == ex) return;

            int tmp = copyMap[sy][sx];
            int y = sy;
            int x = sx;

            while(true) {
                int ny = y + dy[d];
                int nx = x + dx[d];

                if(ny == sy && nx == sx) break;
                if(!isIn(ny, nx, sy, sx, ey, ex) || visited[ny][nx]) {
                    d = (d + 1) % 4;
                    continue;
                }

                visited[ny][nx] = true;
                copyMap[y][x] = copyMap[ny][nx];
                y = ny;
                x = nx;

            }

            if(sx < ex) copyMap[sy][sx+1] = tmp;
        }
    }

    public static void permutation(int cnt, int[] selected, boolean[] visited) {
        if(cnt == 0) {
            int[][] copyMap = getCopyMap();
            for(int i : selected) {
                rotate(i, copyMap);
            }

            ans = Math.min(ans, getMatrixValue(copyMap));
            return;
        }

        for(int i = 0; i < K; ++i) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[selected.length - cnt] = i;
            permutation(cnt-1, selected, visited);
            visited[i] = false;
        }
    }

    public static int[][] getCopyMap() {
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y) {
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }

    public static boolean isIn(int y, int x, int sy, int sx, int ey, int ex) {
        return y >= sy && x >= sx && y <= ey && x <= ex;
    }

    public static int getMatrixValue(int[][] copyMap) {
        int sum = Integer.MAX_VALUE;
        for(int y = 0; y < N; ++y) {
            int tmp = 0;
            for(int x = 0; x < M; ++x) {
                tmp += copyMap[y][x];
            }

            sum = Math.min(sum, tmp);
        }

        return sum;
    }
}
