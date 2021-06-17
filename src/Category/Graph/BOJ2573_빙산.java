package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ2573_빙산 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M;
    private static int[][] map;
    private static Queue<Point> q;
    private static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());   // 행
        M = Integer.parseInt(st.nextToken());   // 열
        map = new int[N][M];                    // map 정보
        q = new LinkedList<>();

        // map 입력
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine());
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0; // 걸리는 시간
        while(true) {
            time++;
            search(); // 1. 물에 맞닿은 빙산 조회
            melt();   // 2. 1에서 조회된 빙산들을 녹임

            // 3. 빙산의 덩어리 개수 구하기
            visited = new boolean[N][M];
            int getIceBergNum = 0; // 빙산의 덩어리 수

            // 빙산을 찾아본다.
            for (int y = 0; y < N; ++y) {
                for (int x = 0; x < M; ++x) {
                    // 물이거나 이미 방문했던 빙산이라면 pass
                    if (map[y][x] == 0 || visited[y][x]) continue;
                    visited[y][x] = true;
                    getIceBergNum++; // 빙산 덩어리 수 증가
                    getIceBerg(y, x); // 빙산 dfs 탐색
                }
            }
            if (getIceBergNum >= 2) break; // 2개 이상으로 분리 되었다면, 종료

            // 만약 빙산이 다 녹을때까지 두 덩어리 이상 분해되지 않을 때 실행
            int cnt = 0;
            outer: for (int y = 0; y < N; ++y) {
                for (int x = 0; x < M; ++x) {
                    if (map[y][x] != 0) break outer;
                    cnt++;
                }
            }
            if(cnt == N*M){
                time = 0;
                break;
            }
        }

//5 6
//0 0 0 0 0 0
//0 2 2 2 2 0
//0 2 0 0 2 0
//0 2 2 2 2 0
//0 0 0 0 0 0



        System.out.println(time);
    }

    public static void search(){
        q.clear();

        // 1. 물과 맞닿은 빙산 찾기
        for(int y = 0; y < N; ++y){
            for(int x = 0; x< M; ++x){
                if(map[y][x] == 0) continue;    // 빙산이 아니면 pass

                int count = 0;
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];

                    // 배열 범위 안에 없거나 빙산인 경우 녹지 않음
                    if(!isIn(ny, nx) || map[ny][nx] != 0) continue;
                    ++count;
                }
                q.offer(new Point(y, x, count));
            }
        }
    }

    // 2. 빙산 녹이기
    public static void melt(){
        while(!q.isEmpty()){
            Point now = q.poll();
            int y = now.y;
            int x = now.x;

            map[y][x] -= now.count;
            if(map[y][x] < 0) map[y][x] = 0;
        }
    }

    // 3. 빙산 덩어리 개수 구하기
    public static void getIceBerg(int y, int x){

        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) continue;
            visited[ny][nx] = true;
            getIceBerg(ny, nx);
        }
    }



    // 배열 유효성 검사
    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 &&  y < N && x < M;
    }

    static class Point{
        int y;
        int x;
        int count;

        public Point(int y, int x, int count){
            this.y = y;
            this.x = x;
            this.count = count;
        }
    }
}
