package StepByStep.day210306;

import java.io.*;
import java.util.*;

public class BOJ2589_G5_보물섬 {

    static int Y, X, ans;
    static char[][] map;
    static int[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        map = new char[Y][X];
        ans = Integer.MIN_VALUE;

        for (int i = 0; i < Y; i++) {
            map[i] = br.readLine().toCharArray();
        }

        // -------- 알고리즘 시작 ---------- //
        // Idea. 최단거리 = bfs
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if(map[y][x] == 'L') {
                    visited = new int[Y][X];
                    bfs(y, x);
                }
            }
        }
        System.out.println(ans);
    }

    static void bfs(int y, int x) {
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));

        while (!q.isEmpty()) {
            Point p = q.poll();
            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                if(isIn(ny,nx) && map[ny][nx] == 'L' && visited[ny][nx] == 0){
                    visited[ny][nx] = visited[p.y][p.x] + 1;
                    ans = Math.max(ans, visited[ny][nx]);
                    q.offer(new Point(ny,nx));
                }
            }
        }



    }
    static void print(){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
                sb.append(visited[i][j]);
            }
            sb.append("\n");
        }
        sb.append("============");
        System.out.println(sb);
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
        return 0 <= y && y < Y && 0 <= x && x < X;
    }

    static String input = "5 7\n" +
            "WLLWWWL\n" +
            "LLLWLLL\n" +
            "LWLWLWW\n" +
            "LWLWLLL\n" +
            "WLLWLWW";
}
