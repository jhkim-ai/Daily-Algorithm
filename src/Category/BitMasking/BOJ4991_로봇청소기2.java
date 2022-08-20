package Category.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991_로봇청소기2 {

    private static final int WALL = -2;

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int H, W;
    private static int dustCnt;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            if(W == 0 && H == 0){
                break;
            }

            map = new int[H][W];
            dustCnt = 0;

            int startY = 0;
            int startX = 0;
            for (int y = 0; y < H; ++y) {
                Arrays.fill(map[y], -1);
                String str = br.readLine();
                for (int x = 0; x < W; ++x) {
                    char c = str.charAt(x);
                    if (c == 'o') {
                        startY = y;
                        startX = x;
                    } else if (c == 'x') {
                        map[y][x] = WALL;
                    } else if (c == '*') {
                        map[y][x] = dustCnt++;
                    }
                }
            }

            sb.append(run(startY, startX)).append("\n");
        }
        System.out.print(sb);
    }

    public static int run(int startY, int startX) {
        int moveCnt = 0;
        boolean[][][] visited = new boolean[H][W][1 << dustCnt];
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(startY, startX, 0));
        visited[startY][startX][0] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for(int s = 0; s < size; ++s) {
                Point now = q.poll();
                // 다 청소했다면 종료
                if (now.clear == (1 << dustCnt) - 1) {
                    return moveCnt;
                }
                for(int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    int clear = now.clear;
                    // 배열 index 확인, 방문 체크, 벽 체크
                    if(!isIn(ny, nx) || visited[ny][nx][clear] || map[ny][nx] == WALL){
                        continue;
                    }

                    if(map[ny][nx] > -1){
                        clear = clear | (1<<map[ny][nx]);
                    }

                    visited[ny][nx][clear] = true;
                    q.offer(new Point(ny, nx, clear));
                }
            }
            moveCnt++;
        }

        return -1;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < W;
    }

    static class Point {

        int y;
        int x;
        int clear;

        public Point(int y, int x, int clear) {
            this.y = y;
            this.x = x;
            this.clear = clear;
        }
    }
}
