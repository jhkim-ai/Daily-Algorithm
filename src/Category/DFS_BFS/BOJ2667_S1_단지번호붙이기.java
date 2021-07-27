package Category.DFS_BFS;

import java.io.*;
import java.util.*;

// bfs
public class BOJ2667_S1_단지번호붙이기 {

    static int N, ans, count;
    static int[][] map;
    static boolean[][] visited;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};
    static List<Integer> aptComplex;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        // ----------- 데이터 입력 ----------- //

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        aptComplex = new ArrayList<>();

        // Map 입력
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = str.charAt(j) - '0';
                if (map[i][j] == 1) {
                    // 단지 수를 편하게 계산하기 위해 Map 의 1을 -1로 변경
                    // 아파트가 위치한 곳  = -1
                    map[i][j] = -1;
                }
            }
        }

        // ----------- 알고리즘 시작 ----------- //

        // 아파트가 위치한 곳을 bfs로 탐색  
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == -1) {
                    // 1. bfs
                    // visited = new boolean[N][N];
                    // bfs(new Point(y, x), 1);

                    // 2. dfs
                    count = 0;
                    ans++;
                    dfs(new Point(y, x), new boolean[N][N]);
                    aptComplex.add(count);
                }
            }
        }
        // 오름차순 정렬
        Collections.sort(aptComplex);

        // ----------- 출력 ----------- //

        StringBuilder sb = new StringBuilder();
        sb.append(ans).append("\n");
        for(int num : aptComplex)
            sb.append(num).append("\n");

        System.out.println(sb);
    }

    static void bfs(Point p, int cnt) {
        Queue<Point> q = new LinkedList<>();
        q.offer(p);
        visited[p.y][p.x] = true;
        ans++;  // 단지 번호 (=단지 수)
        map[p.y][p.x] = ans;
        
        while (!q.isEmpty()) {
            Point now = q.poll();
            // 4방 탐색
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                // 유효성 검사 - (범위, 방문한 곳, 아파트가 위치한 곳)
                if(isIn(ny, nx) && !visited[ny][nx] && map[ny][nx] == -1){
                    visited[ny][nx] = true;
                    q.offer(new Point(ny, nx));
                    map[ny][nx] = ans;  // 단지 번호를 매김
                    cnt++;  // 단지내 집의 수
                }
            }
        }
        
        aptComplex.add(cnt);
    }

    // dfs
    static void dfs(Point p, boolean[][] visited){
        visited[p.y][p.x] = true;
        map[p.y][p.x] = ans;
        count++;

        for (int d = 0; d < 4; d++) {
            int ny = p.y + dy[d];
            int nx = p.x + dx[d];

            if(isIn(ny, nx) && !visited[ny][nx] && map[ny][nx] == -1){
                dfs(new Point(ny, nx), visited);
            }
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    // 디버깅을 위해 임시로 만든 print 함수
    static void print(){
        for(int[] a : map){
            System.out.println(Arrays.toString(a));
        }
        System.out.println("============================");
    }

    static String input = "7\n" +
            "0110100\n" +
            "0110101\n" +
            "1110101\n" +
            "0000111\n" +
            "0100000\n" +
            "0111110\n" +
            "0111000";
}
