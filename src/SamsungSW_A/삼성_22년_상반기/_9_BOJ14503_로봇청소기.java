package SamsungSW_A.삼성_22년_상반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9_BOJ14503_로봇청소기 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, dir;
    private static int robotY, robotX;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        robotY = Integer.parseInt(st.nextToken());
        robotX = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        run();
        System.out.println(getCleanArea());
    }

    public static void run(){
        boolean flag = true;

        outer:
        while(true){
            if(flag) setClean(robotY, robotX);
            int cnt = 0;

            inner:
            for(int d = 3; d >= 0; --d){
                int nd = (dir + d) % 4;
                int ny = robotY + dy[nd];
                int nx = robotX + dx[nd];

                if(isWall(ny, nx) || isCleaned(ny, nx)){
                    ++cnt;
                    continue;
                }

                robotY = ny;
                robotX = nx;
                dir = nd;
                flag = true;
                break;
            }

            if(cnt == 4){
                int ny = robotY + dy[(dir + 2) % 4];
                int nx = robotX + dx[(dir + 2) % 4];
                if(isWall(ny, nx)) return;

                robotY = ny;
                robotX = nx;
                flag = false;
            }
        }
    }

    public static int getCleanArea(){
        int cnt = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(map[y][x] == 2) cnt++;
            }
        }
        return cnt;
    }

    public static boolean isWall(int y, int x){
        return map[y][x] == 1;
    }

    public static boolean isCleaned(int y, int x){
        return map[y][x] == 2;
    }

    public static void setClean(int y, int x){
        map[y][x] = 2;
    }
}
