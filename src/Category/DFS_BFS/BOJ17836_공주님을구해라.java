package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ17836_공주님을구해라 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, T, ans;
    private static int swordY, swordX;

    private static int[][] map;
    private static boolean[][][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M][2]; // 0 : sword(x), 1: sword(o)

        // 입력
        for(int y = 0 ; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
                // sword 위치
                if(map[y][x] == 2){
                    swordY = y;
                    swordX = x;
                }
            }
        }

        // 탐색 시작
        start();
        if(ans == 0 || ans > T) System.out.println("Fail");
        else System.out.println(ans);
    }

    public static void start(){
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0, 0, false));
        visited[0][0][0] = true; // 첫 위치

        while(!q.isEmpty()){
            Point now = q.poll();

            // 4방 탐색
            for(int d = 0; d < 4; ++d){
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                // (1)유효 범위 체크, (2)"그람"을 든 상태에서 방문했다면 pass, (3)"그람"을 들지 않은 상태에서 방문했다면 pass
                if(!isIn(ny, nx)) continue;

                // "그람"을 가지고 있다면 벽을 뚫고 갈 수 있음
                if(!visited[ny][nx][1] && now.getSword) {
                    // 공주 구출
                    if(isPrincess(ny, nx)){
                        ans = now.time+1;
                        return;
                    }
                    // 공주 구출이 아니라면 이동
                    q.offer(new Point(ny, nx, now.time+1, true));
                    visited[ny][nx][1] = true;
                    continue;
                }

                // "그람"이 없는 상태에서 벽을 만난다면 pass
                if(visited[ny][nx][0] || map[ny][nx] == 1) continue;
                if(isPrincess(ny, nx)){ // "그람"이 없는 상태로 공주 구출
                    ans = now.time+1;
                    return;
                }

                boolean isSword = false;
                if(map[ny][nx] == 2) isSword = true; // "그람 획득"
                if(isSword) visited[ny][nx][1] = true; // 방문 체크
                else visited[ny][nx][0] = true;
                q.offer(new Point(ny, nx, now.time+1, isSword)); // 새로운 좌표로 이동
            }
        }
    }

    // 배열 범위 확인
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }

    // 공주를 찾았을 때
    public static boolean isPrincess(int y, int x){
        return y == N-1 && x == M-1;
    }

    static class Point{
        int y;
        int x;
        int time;
        boolean getSword;

        public Point(int y, int x, int time, boolean getSword){
            this.y = y;
            this.x = x;
            this.time = time;
            this.getSword = getSword;
        }
    }
}
