package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_로봇청소기 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M;
    private static int[][] map;
    private static boolean[][] clean;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        int startY = Integer.parseInt(st.nextToken());
        int startX = Integer.parseInt(st.nextToken());
        int startD = Integer.parseInt(st.nextToken());

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(run(startY, startX, startD));
    }

    public static int run(int startY, int startX, int startD){
        int cnt = 0;
        clean = new boolean[N][M];
        boolean flag = true;

        outer:
        while(true) {

            if (!clean[startY][startX] && flag) { // 1. 청소하기
                clean[startY][startX] = true;
                flag = false;
                ++cnt;
            }

            int ny = -1, nx = -1, d = -1;
            int dirCount = 0;

            // 2. 4방향 탐색
            inner:
            for (int i = 0; i < 4; i++) {
                d = (startD + 3) % 4;
                ny = startY + dy[d];
                nx = startX + dx[d];

                // 2-b. 벽이거나 청소했을 경우
                if (isWall(ny, nx) || clean[ny][nx]) {
                    dirCount++;
                    startD = d;
                    flag = false;
                    continue inner; // 2번으로 돌아가기기
                }

                // 2-a. 청소가 안되어 있다면,
                startY = ny;
                startX = nx;
                flag = true;
                startD = d;
                break inner; // 1번으로 돌아가기
            }

            // 2-b. 네 방향 모두 청소가 되어 있는 경우
            if(dirCount == 4){
                ny = startY - dy[d];
                nx = startX - dx[d]; // 후진

                // 후진할 곳이 벽인 경우 종료
                if(isWall(ny, nx)){
                    break outer;
                }

                // 2번으로 돌아가기기
                flag = false;
                startY = ny;
                startX = nx;
                startD = d;
            }
        }

        return cnt;
    }

    // 벽인지 check
    public static boolean isWall(int y, int x){
        return map[y][x] == 1;
    }
}
