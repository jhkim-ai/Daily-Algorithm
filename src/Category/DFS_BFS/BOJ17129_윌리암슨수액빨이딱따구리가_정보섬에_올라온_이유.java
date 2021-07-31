package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ17129_윌리암슨수액빨이딱따구리가_정보섬에_올라온_이유 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static Point start;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int y = 0; y < N; ++y){
            String tmp = br.readLine();
            for(int x = 0; x < M; ++x){
                map[y][x] = tmp.charAt(x) - '0';
                if(map[y][x] == 2) start = new Point(y, x);
            }
        }

        int dis = bfs();
        if(dis == 0) System.out.println("NIE");
        else {
            System.out.println("TAK");
            System.out.println(dis);
        }
    }

    public static int bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        q.offer(start);
        visited[start.y][start.x] = true;
        int dis = 0;

        while(!q.isEmpty()){
            int size = q.size();
            for(int s = 0; s < size; ++s){
                Point now = q.poll();
                for(int d = 0; d < 4; ++d){
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 1) continue;

                    // 음식 도착
                    if(isFood(ny, nx)) return dis+1;
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
            dis++;
        }
        return 0;
    }

    public static boolean isFood(int y, int x){
        int now = map[y][x];
        return now == 3 || now == 4 || now == 5;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    static class Point{
        int y;
        int x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
