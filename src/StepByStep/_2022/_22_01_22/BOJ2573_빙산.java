package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ2573_빙산 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        int idx = 0;
        while(true) {
            idx++;
            List<int[]> points = getMeltedPoint();
            melt(points);
            visited = new boolean[N][M];
            int cnt = 0;
            boolean flag = false;
            for (int y = 0; y < N; ++y){
                for(int x = 0; x < M; ++x) {
                    if(map[y][x] != 0 && !visited[y][x]) {
                        cnt += getIceBerg(y, x);
                        flag = true;
                    }
                }
            }

            if(cnt > 1){
                break;
            }
            if(!flag){
                idx = 0;
                break;
            }
        }
        System.out.println(idx);
    }

    public static List<int[]> getMeltedPoint(){
        List<int[]> point = new ArrayList<>();
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(map[y][x] == 0){ // 빙산이 아니면 pass
                    continue;
                }
                int cnt = 0; // 인접한 바다의 수
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    if(!isIn(ny, nx) || map[ny][nx] != 0){
                        continue;
                    }

                    cnt++;
                }
                point.add(new int[]{y, x, cnt});
            }
        }
        return point;
    }

    public static void melt(List<int[]> points){
        for(int[] point : points){
            int y = point[0];
            int x = point[1];
            int cnt = point[2];

            map[y][x] -= cnt;
            if(map[y][x] < 0) {
                map[y][x] = 0;
            }
        }
    }

    public static int getIceBerg(int y, int x){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        visited[y][x] = true;

        while(!q.isEmpty()){
            Point now = q.poll();
            for(int d = 0; d < 4; ++d){
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0){
                    continue;
                }
                visited[ny][nx] = true;
                q.offer(new Point(ny, nx));
            }
        }

        return 1;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
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
