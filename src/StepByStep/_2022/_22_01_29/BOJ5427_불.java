package StepByStep._2022._22_01_29;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ5427_불 {
    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int W, H;
    private static char[][] map;
    private static Point man;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for(int t = 0; t < T; ++ t){
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new char[H][W];

            for(int y = 0; y < H; ++y){
                String str = br.readLine();
                for(int x = 0; x < W; ++x){
                    map[y][x] = str.charAt(x);
                    if(map[y][x] == '@'){
                        man = new Point(y, x);
                    }
                }
            }
        }
    }

    public static void bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[H][W];
        q.offer(man);
        visited[man.y][man.x] = true;

        while(!q.isEmpty()){

            Point now = q.poll();

        }
    }

    public static class Point {
        int y;
        int x;

        public Point(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
