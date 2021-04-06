package 오답_및_복습;

import java.util.*;
import java.io.*;

public class BOJ16946_벽부수고이동하기4 {

    static final int[] dy = {-1, 1, 0, 0};
    static final int[] dx = {0, 0, -1, 1};

    static int N, M;
    static int set;
    static int[][] map;
    static Point[][] move;  // 0의 위치에만 인접한 0의 개수를 저장하기 위한 배열
    static int[][] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        move = new Point[N][M];
        ans = new int[N][M];

        for (int y = 0; y < N; y++) {
            String str = br.readLine();
            for (int x = 0; x < M; x++) {
                map[y][x] = str.charAt(x) - '0';
            }
        }

        // --------- 알고리즘 --------- //

        // Idea.
        // 1. 인접한 0의 개수를 센다.
        // 2. 벽(1)일 시, 사방 탐색하여 연속된 0의 개수를 센다
        // 3. 2의 경우, 이미 1에서 진행 하였기 때문에 기준 벽에서 벽이 아닌 사방 탐색을 더하면 된다.
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0)
                    bfs(y, x);
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if(map[y][x] == 1){
                    int sum = 1;
                    List<Integer> checkSet = new ArrayList<>();
                    for (int d = 0; d < 4; d++) {
                        int ny = y + dy[d];
                        int nx = x + dx[d];
                        if(!isIn(ny, nx) || map[ny][nx] != -1 || checkSet.contains(move[ny][nx].set)) continue;
                        sum += move[ny][nx].cnt;
                        checkSet.add(move[ny][nx].set);
                    }
                    ans[y][x] = sum;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int[] arr: ans){
            for(int num : arr)
                sb.append(num);
            sb.append("\n");
        }
        System.out.println(sb);
    }


    static void bfs(int y, int x) {
        int cnt = 1; // 0의 개수
        List<Point> list = new ArrayList<>(); // 인접한 0의 좌표를 저장하기 위함
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(y, x));
        list.add(new Point(y, x));
        ++set;

        while (!q.isEmpty()) {
            Point p = q.poll();
            map[p.y][p.x] = -1; // 0의 위치를 -1로 바꿔주기
            cnt = ++cnt % 10;

            for (int d = 0; d < 4; d++) {
                int ny = p.y + dy[d];
                int nx = p.x + dx[d];
                if(!isIn(ny, nx))   continue;
                if(map[ny][nx] != 0) continue;
                q.offer(new Point(ny, nx));
                list.add(new Point(ny, nx));
            }
        }
        for(Point p : list) {
            move[p.y][p.x] = new Point(p.y, p.x);   // 0의 위치에 갈 수 있는 칸(인접한 0의 개수) 기록
            move[p.y][p.x].set = set;
            move[p.y][p.x].cnt = cnt-1;
        }
    }

    static boolean isIn(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    static class Point {
        int y;
        int x;
        int set = 0; // 집합 번호
        int cnt = 0; // 이동 가능 횟수

        public Point(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
