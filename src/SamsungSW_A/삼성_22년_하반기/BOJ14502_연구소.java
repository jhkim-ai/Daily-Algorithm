package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};
    private static int N, M, K, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = N * M;
        map = new int[N][M];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(3, new int[3], 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if(cnt == 0){
            for(int idx : selected) {
                int y = idx / M;
                int x = idx % M;
                if(map[y][x] != 0) return;
            }

            int[][] copyMap = copyMap();

            for(int idx : selected) {
                int y = idx / M;
                int x = idx % M;
                copyMap[y][x] = 1;
            }

            int total = getSafeZone(copyMap);
            ans = Math.max(ans, total);

            return;
        }

        for(int i = startIdx; i < K; ++i) {
            selected[selected.length - cnt] = i;
            combination(cnt - 1, selected, i + 1);
        }
    }

    public static void spread(int y, int x, int[][] copyMap, boolean[][] visited) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now[0] + dy[d];
                int nx = now[1] + dx[d];
                if(!isIn(ny, nx) || copyMap[ny][nx] != 0 || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                copyMap[ny][nx] = 2;
                q.offer(new int[]{ny, nx});
            }
        }
    }

    public static int getSafeZone(int[][] copyMap) {
        int cnt = 0;
        boolean[][] visited = new boolean[N][M];

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < M; ++x) {
                if(copyMap[y][x] == 2 && !visited[y][x]) {
                    spread(y, x, copyMap, visited);
                }
            }
        }

        for(int y = 0; y < N; ++y) {
            for(int x = 0; x < M; ++x) {
                if(copyMap[y][x] == 0) {
                    cnt++;
                }
            }
        }

        return cnt;
    }

    public static int[][] copyMap() {
        int[][] copyMap = new int[N][];
        for(int y = 0; y < N; ++y){
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
