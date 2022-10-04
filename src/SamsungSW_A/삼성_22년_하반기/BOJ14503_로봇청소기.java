package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14503_로봇청소기 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M;
    private static int dir;
    private static int[][] map;
    private static int[][] tmp;
    private static boolean[][] isClean;
    private static Robot robot;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        tmp = new int[N][M];
        isClean = new boolean[N][M];

        st = new StringTokenizer(br.readLine(), " ");
        int robotY = Integer.parseInt(st.nextToken());
        int robotX = Integer.parseInt(st.nextToken());
        robot = new Robot(robotY, robotX);
        dir = Integer.parseInt(st.nextToken());

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
                tmp[y][x] = map[y][x];
            }
        }

        System.out.println(start());
    }

    public static int start() {
        int cnt = 0;
        boolean flag = true;

        outer:
        while(true) {
            if(flag && !isClean[robot.y][robot.x]) {
                isClean[robot.y][robot.x] = true;
                tmp[robot.y][robot.x] = 2;
                ++cnt;
            }

            int ny;
            int nx;
            for(int d = 0; d < 4; ++d){
                dir = (dir + 3) % 4;
                ny = robot.y + dy[dir];
                nx = robot.x + dx[dir];

                if(isClean[ny][nx]) continue;
                if(isWall(ny, nx)) continue;

                robot.y = ny;
                robot.x = nx;
                flag = true;

                continue outer;
            }

            dir = (dir + 2) % 4;
            ny = robot.y + dy[dir];
            nx = robot.x + dx[dir];

            if(isWall(ny, nx)) break;

            dir = (dir + 2) % 4;
            robot.y = ny;
            robot.x = nx;
            flag = false;
        }

        return cnt;
    }

    public static boolean isWall(int y, int x){
        return map[y][x] == 1;
    }

    public static class Robot {
        int y;
        int x;

        public Robot(int y, int x){
            this.y = y;
            this.x = x;
        }
    }
}
