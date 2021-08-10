package Category.BitMasking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4991_로봇청소기 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int W, H;
    private static int[][] map;
    private static int totalDust;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            totalDust = 0;

            if(W == 0 && H == 0){
                break;
            }

            map = new int[H][W];
            int startY = 0;
            int startX = 0;

            for (int y = 0; y < H; ++y) {
                String str = br.readLine();
                for (int x = 0; x < W; ++x) {
                    switch (str.charAt(x)) {
                        case '.':
                            map[y][x] = -1;
                            break;
                        case '*':
                            map[y][x] = totalDust++;
                            break;
                        case 'x':
                            map[y][x] = -2;
                            break;
                        case 'o':
                            map[y][x] = -1;
                            startY = y;
                            startX = x;
                            break;
                    }
                }
            }

            int ans = bfs(startY, startX);
            sb.append(ans).append("\n");
        }

        System.out.print(sb);
    }

    public static int bfs(int startY, int startX) {
        Queue<Point> q = new LinkedList<>();
        boolean[][][] visited = new boolean[H][W][1 << totalDust];
        int distance = 0;
        q.offer(new Point(startY, startX, 0));
        visited[startY][startX][0] = true;

        while (!q.isEmpty()) {
            int size = q.size();
            for (int s = 0; s < size; ++s) {
                Point now = q.poll();

                if (now.dust == (1 << totalDust) - 1) {
                    return distance;
                }

                for (int d = 0; d < 4; ++d) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    int dust = now.dust;
                    if (!isIn(ny, nx) || visited[ny][nx][dust] || map[ny][nx] == -2) {
                        continue;
                    }

                    if (map[ny][nx] >= 0) {
                        dust = dust | (1 << map[ny][nx]);
                    }

                    visited[ny][nx][dust] = true;
                    q.offer(new Point(ny, nx, dust));
                }
            }
            ++distance;
        }

        return -1;
    }

    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < H && x < W;
    }

    static class Point {

        int y;
        int x;
        int dust;

        public Point(int y, int x, int dust) {
            this.y = y;
            this.x = x;
            this.dust = dust;
        }
    }
}
