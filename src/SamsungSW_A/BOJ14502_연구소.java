package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14502_연구소 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;
    private static int[][] copyMap;
    private static List<Point> locListOfVirus;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        locListOfVirus = new LinkedList<>();
        ans = Integer.MIN_VALUE;

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 2) {
                    locListOfVirus.add(new Point(y, x));
                }
            }
        }

        combination(3, new int[3], 0);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            copyMap = createCopyMap();
            spreadOfInfection();
            ans = Math.max(getSafeArea(), ans);
            return;
        }

        for (int i = startIdx; i < N * M; i++) {
            int y = i / M;
            int x = i % M;
            if (map[y][x] == 0) {
                selected[selected.length - cnt] = i;
                map[y][x] = 1;
                combination(cnt - 1, selected, i + 1);
                map[y][x] = 0;
            }
        }
    }

    public static void spreadOfInfection() {
        Queue<Point> q = new LinkedList<>();
        for(Point p : locListOfVirus){
            q.offer(p);
        }

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

    public static int getSafeArea(){
        int count = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(copyMap[y][x] == 0){
                    ++count;
                }
            }
        }

        return count;
    }

    public static int[][] createCopyMap() {
        int[][] copyMap = new int[N][];
        for (int y = 0; y < N; y++) {
            copyMap[y] = map[y].clone();
        }

        return copyMap;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
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
