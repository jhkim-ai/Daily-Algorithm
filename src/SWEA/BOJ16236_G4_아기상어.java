package SWEA;

import java.util.*;
import java.io.*;

public class BOJ16236_G4_아기상어 {

    static int N;
    static int[][] map;
    static Point shark;
    static boolean visited[][];
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static int time = 0, sSize = 2;
    static List<Point> list;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        list = new ArrayList<>();

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if (map[y][x] == 9) {
                    shark = new Point(y, x, 0);
                }
            }
        }

        bfs();
    }

    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(shark);
        visited[shark.x][shark.y] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                int nm = now.move +1;
                if(isIn(ny, nx) && 0 < map[ny][nx] && map[ny][nx] <= sSize && !visited[ny][nx]){
                    if(map[ny][nx] < sSize){
                        list.add(new Point(ny, nx, nm));
                    }
                }
            }
        }
    }

    static class customSort implements Comparator<Point>{
        @Override
        public int compare(Point p1, Point p2){
            // 거리가 같다면
            if(p1.move == p2.move){
                // 윗라인에 같이 있다면
                if(p1.y == p2.y){
                    return Integer.compare(p1.x, p2.x);
                }
                // 윗방향 우선
                else
                    return Integer.compare(p1.y, p2.y);
            }else{
                return Integer.compare(p1.move, p2.move);
            }
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static class Point{
        int y;
        int x;
        int move;

        public Point(int y, int x, int move) {
            this.y = y;
            this.x = x;
            this.move = move;
        }
    }

    static String input = "4\n" +
            "4 3 2 1\n" +
            "0 0 0 0\n" +
            "0 0 9 0\n" +
            "1 2 3 4";
}
