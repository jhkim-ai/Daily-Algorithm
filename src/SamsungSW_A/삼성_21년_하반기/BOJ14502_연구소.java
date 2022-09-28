package SamsungSW_A.삼성_21년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {

    private static int[] dy = {-1, 1, 0, 0};
    private static int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        ans = Integer.MIN_VALUE;
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(3, new int[3], 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            int[][] copyMap = new int[N][];
            for (int y = 0; y < N; y++) {
                copyMap[y] = map[y].clone();
            }

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < M; x++) {
                    if (copyMap[y][x] == 2) {
                        bfs(y, x, copyMap);
                    }
                }
            }
            ans = Math.max(getMaxArea(copyMap), ans);
            return;
        }

        for (int i = startIdx; i < N * M; i++) {
            int y = i / M;
            int x = i % M;
            if (map[y][x] == 0) {
                map[y][x] = 1;
                selected[selected.length - cnt] = i;
                combination(cnt - 1, selected, i + 1);
                map[y][x] = 0;
            }
        }
    }

    public static int getMaxArea(int[][] copyMap) {
        int count = 0;
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (copyMap[y][x] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void bfs(int y, int x, int[][] copyMap) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        copyMap[y][x] = 2;

        while(!q.isEmpty()){
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if(!isIn(ny, nx) || copyMap[ny][nx] != 0){
                    continue;
                }

                copyMap[ny][nx] = 2;
                q.offer(new Point(ny, nx));
            }
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    public static void print(int[][] copyMap){
        System.out.println("===========");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                System.out.print(copyMap[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static class Point {

        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
