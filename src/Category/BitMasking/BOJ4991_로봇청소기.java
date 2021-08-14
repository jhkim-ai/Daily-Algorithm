package Category.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991_로봇청소기 {

    private static int H, W;
    private static int totalDust;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        int startY = 0;
        int startX = 0;
        int ans = -1;

        for(int y = 0; y < H; ++y){
            String str = br.readLine();
            for(int x = 0; x < W; ++x){
                char c = str.charAt(x);
                switch (c){
                    case 'o':
                        map[y][x] = -2;
                        startY = y;
                        startX = x;
                        break;
                    case '.':
                        map[y][x] = -2;
                        break;
                    case '*':
                        map[y][x] = totalDust++;
                        break;
                    case 'x':
                        map[y][x] = -1;
                        break;
                }
            }
        }
        ans = bfs(startY, startX);

    }

    public static int bfs(int startY, int startX){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startY, startX));

        return -1;
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
