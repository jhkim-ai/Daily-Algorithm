package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ14940_쉬운최단거리 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static Point start;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for(int y= 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x= 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 2) start = new Point(y, x);
            }
        }
        bfs();
        print();
    }

    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        q.offer(start);
        map[start.y][start.x] = 0;
        visited[start.y][start.x] = true;

        while(!q.isEmpty()){
            Point now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // 배열 범위 검사, 장애물 검사, 방문한 곳 검사
                if(!isIn(ny, nx) || map[ny][nx] == 0 || visited[ny][nx]) continue;
                visited[ny][nx] = true;
                map[ny][nx] += map[now.y][now.x];
                q.offer(new Point(ny, nx));
            }
        }
    }

    public static void print(){
        StringBuilder sb = new StringBuilder();
        for(int y = 0; y < N; ++y){
            for(int x= 0; x < M; ++x){
                if(visited[y][x] || map[y][x] == 0) sb.append(map[y][x]);
                else sb.append("-1");
                sb.append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
