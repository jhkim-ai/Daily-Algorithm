package Category.DFS_BFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7562_나이트의이동 {

    private static final int[] dy = {-1, -2, -2, -1, 1, 2, 2, 1};
    private static final int[] dx = {-2, -1, 1, 2, 2, 1, -1, -2};

    private static int T, N;
    private static Point start, end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            start = new Point(y, x);

            st = new StringTokenizer(br.readLine(), " ");
            y = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            end = new Point(y, x);
            int ans = 0;

            if(start.y != end.y || start.x != end.x) {
                ans = bfs();
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int bfs(){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        visited[start.y][start.x] = true;
        q.offer(start);
        int ans = 0;
        while(!q.isEmpty()){
            int size = q.size();
            ++ans;
            for(int s = 0; s < size; ++s){
                Point now = q.poll();
                for(int d = 0; d < 8; ++d){
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];

                    if(!isIn(ny, nx) || visited[ny][nx]) continue;
                    if(ny == end.y && nx == end.x) return ans;
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                }
            }
        }

        return -1;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < N;
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
