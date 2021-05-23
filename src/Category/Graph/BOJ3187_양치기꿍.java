package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ3187_양치기꿍 {
    public static final int[] dy = {-1, 1, 0, 0};
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, M;
    public static int ansWolf, ansSheep;
    public static char[][] map;
    public static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        ansSheep = ansWolf = 0;

        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
        }

        // ======= 알고리즘 시작 =======
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                char cNow = map[y][x];
                if (cNow == '#' || visited[y][x]) continue;
                bfs(y, x);
            }
        }
        System.out.println(ansSheep + " " + ansWolf);
    }

    public static void bfs(int y, int x) {
        int wolf = 0, sheep = 0;
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        visited[y][x] = true;

        while (!q.isEmpty()) {
            Point now = q.poll();

            // 현재 좌표의 동물 정보 확인
            if (map[now.y][now.x] == 'v') wolf++;
            else if (map[now.y][now.x] == 'k') sheep++;

            // 같은 구역 내에서 (양, 늑대)의 수 체크
            for (int d = 0; d < 4; ++d) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // 범위 체크 + 방문 체크 + 울타리 체크
                if (!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == '#') continue;

                visited[ny][nx] = true;
                q.offer(new Point(ny, nx));
            }
        }

        // 조건
        // "양의 수 > 늑대의 수" 라면, 양이 늑대를 잡아 먹는다.
        // 그 외의 조건은 늑대가 양을 잡아 먹는다.
        if (wolf < sheep) ansSheep += sheep;
        else ansWolf += wolf;
    }
    
    // 범위 체크
    public static boolean isIn(int y, int x) {
        return y >= 0 && x >= 0 && y < N && x < M;
    }
    
    // 좌표 저장을 위해 Point 객체 사용
    static class Point {
        int y;
        int x;

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
    
    // debugging 용
    public static void print() {
        System.out.println("============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                int val = 0;
                if(visited[y][x]) val = 1;
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
