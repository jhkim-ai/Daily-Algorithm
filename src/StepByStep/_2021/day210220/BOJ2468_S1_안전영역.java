package StepByStep._2021.day210220;

import java.io.*;
import java.util.*;

public class BOJ2468_S1_안전영역 {

    static int N;
    static int minH = Integer.MAX_VALUE, maxH = Integer.MIN_VALUE;
    static int[][] map;
    static boolean[][] flood, visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int cnt, ans;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                minH = Math.min(minH, map[i][j]);
                maxH = Math.max(maxH, map[i][j]);
            }
        }

        ans = 1;
        for (int h = minH; h < maxH; h++) {
            cnt = 0;
            visited = new boolean[N][N];

            //dfs
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    // 방문하지 않았고, 물의 높이보다 높은 곳만 탐색
                    if(!visited[y][x] && map[y][x] > h){

                        dfs(y, x, h);
                        // dfs 를 다 마치면, 같은 영역이 방문표시가 됨
                        cnt++;
                    }
                }
            }

            // bfs(n);
            
            // 정답 갱신
            ans = Math.max(ans, cnt);
        }
        
        // 정답 출력
        System.out.println(ans);
    }

    static void dfs(int y, int x, int h){
        // 방문 체크
        visited[y][x] = true;

        // 4방탐색
        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];

            // 범위 유효성 검사 + 방문 하지 않으며 물의 높이보다 높은 영역
            if(isIn(ny, nx) && !visited[ny][nx] && map[ny][nx] > h)
                dfs(ny, nx, h);
        }
    }

    static void bfs(int h) {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] > h)
                    flood[i][j] = true;
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (flood[y][x] && !visited[y][x]) {
                    q.offer(new Point(y, x));
                    visited[y][x] = true;

                    while (!q.isEmpty()) {
                        Point p = q.poll();
                        visited[p.y][p.x] = true;

                        for (int d = 0; d < 4; d++) {
                            int ny = p.y + dy[d];
                            int nx = p.x + dx[d];

                            if(isIn(ny, nx) && flood[ny][nx] && !visited[ny][nx])
                                q.offer(new Point(ny,nx));
                        }
                        print();
                        System.out.println("=============================");
                    }

                    cnt++;
                    if(cnt > ans)
                    System.out.println("cnt = " + cnt + "=====================");
                    System.out.println("height = " + h + "=====================");
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
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static void print(){
        for(boolean[] a : visited)
            System.out.println(Arrays.toString(a));
    }

    static String input = "5\n" +
            "6 8 2 6 2\n" +
            "3 2 3 4 6\n" +
            "6 7 3 3 2\n" +
            "7 2 5 3 6\n" +
            "8 9 5 2 7";
}
