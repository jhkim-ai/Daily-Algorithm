package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ16946_벽부수고이동하기4 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;
    static int[][] ans;
    static int[][] copyMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        ans = new int[N][M];

        for (int y = 0; y < N; y++) {
            String str = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        // --------- 알고리즘 시작 --------- //
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(map[y][x] == 1) {
                    System.out.println("다음 *********************");
                    int sum = 1;
                    copyMap = new int[N][M];
                    for (int r = 0; r < N; r++) {
                        copyMap[r] = map[r].clone();
                    }
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if(!isIn(ny, nx) || map[ny][nx] != 0) continue;
                        sum += bfs(ny, nx);
                    }
                    ans[y][x] = sum;
                }
            }
        }
        System.out.println("정답!!!!!!!!!!!!!!!");
        for(int [] a : ans){
            System.out.println(Arrays.toString(a));
        }
    }

    static int bfs(int y, int x) {
        int len = 1;
        Queue<Point> q = new LinkedList<>();
        Point start = new Point(y, x);
        copyMap[start.y][start.x] = len;
        q.offer(start);
        while (!q.isEmpty()){
            Point now = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if(!isIn(ny, nx) || map[ny][nx] != 0 || copyMap[ny][nx] != 0) continue;
                q.offer(new Point(ny, nx));
                copyMap[ny][nx] = copyMap[now.y][now.x]+1;
                len = Math.max(len, copyMap[ny][nx]);
            }
        }
        print();

        return len;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static void print(){
        System.out.println("===============");
        for(int [] a : copyMap)
            System.out.println(Arrays.toString(a));
    }
}
