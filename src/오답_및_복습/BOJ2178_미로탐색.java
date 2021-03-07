package 오답_및_복습;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2178_미로탐색 {

    static int N, M;
    static int[][] map;
    static int ans;
    static Queue<Point> q;

    static boolean[][] visited;

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];

        ans = Integer.MAX_VALUE;
        q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bfs(0, 0);
        System.out.println(map[N-1][M-1]);
    }

    static void bfs(int y, int x) {

        q.offer(new Point(y, x));

        while (!q.isEmpty()) {
            Point p = q.poll();
            visited[p.y][p.x] = true;

            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];


                if(isIn(ny, nx) && !visited[ny][nx] && map[ny][nx] == 1){
                    q.offer(new Point(ny, nx));
                    visited[ny][nx] = true;
                    map[ny][nx] = map[p.y][p.x] + 1;
                }
            }
        }
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

    static String input = "4 6\n" +
            "101111\n" +
            "101010\n" +
            "101011\n" +
            "111011";
}
