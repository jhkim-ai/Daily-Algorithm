package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class _9_BOJ14503_로봇청소기 {

    private static final int[] dy = {-1, 0, 1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    private static int N, M, ans;
    private static int robotY, robotX, dir;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine(), " ");
        robotY = Integer.parseInt(st.nextToken());
        robotX = Integer.parseInt(st.nextToken());
        dir = Integer.parseInt(st.nextToken());
        System.out.println(robotY + ", " + robotX);
        map = new int[N][M];
        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        run();
        System.out.println(getCleaned());
    }
    static int a = 0;
    public static void run(){
        outer:
        while(true){
            setClean(robotY, robotX);

            int cnt = 0;
            for(int d = 3; d >= 0; --d){
                if(d == 3){
                    cnt = 0;
                }
                a++;
                if(a > 50) System.exit(0);
                print(null);
                ++cnt;
                int nd = (dir + d) % 4;
                int ny = robotY + dy[nd];
                int nx = robotX + dx[nd];

                // 이미 청소된 곳이라면 한 번 더 회전
                if(isClean(ny, nx)) {
                    System.out.println("청소된 곳이기에 한 번 더 회전");
                    if(cnt == 4) {
                        dir = nd;
                        ny = robotY + dy[(dir + 2) % 4];
                        nx = robotX + dx[(dir + 2) % 4];
                        if(isWall(ny, nx)){ // 후진한 곳이 벽일 경우.
                            System.out.println("4방향 중 청소할 곳 없음 + 후진한 곳도 벽");
                            break outer;
                        }

                        robotY = ny;
                        robotX = nx;
                        d = 4;
                        System.out.println("4방향이 다 청소되어 후진 후 다시 재탐색");
                    };
                    continue;
                }

                if(isWall(ny, nx)){ // 앞으로 청소할 곳이 벽일 경우.
                    ny = robotY + dy[(nd + 2) % 4]; // 벽을 바라보고 후진
                    nx = robotX + dx[(nd + 2) % 4];
                    if(isWall(ny, nx)){ // 후진한 곳도 벽이라면 종료.
                        System.out.println("벽을 만나 - 후진한 곳도 벽 2");
                        break outer;
                    }

                    robotY = ny;
                    robotX = nx;
                    dir = nd;
                    d = 4;
                    System.out.println("벽을 만나 후진 후 다시 재탐색");
                    continue;
                }

                // 4방향 중 청소할 공간을 찾음
                if(map[ny][nx] == 0) {
                    System.out.println("4방향 중 청소할 공간 찾음");
                    robotY = ny;
                    robotX = nx;
                    dir = nd;
                    break;
                }

            }
        }
    }

    public static void setClean(int y, int x){
        map[y][x] = 2;
        print("청소");
    }

    public static int getCleaned(){
        int cnt = 0;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(map[y][x] == 2) cnt++;
            }
        }
        return cnt;
    }

    public static boolean isClean(int y, int x){
        return map[y][x] == 2;
    }

    public static boolean isWall(int y, int x){
        return map[y][x] == 1;
    }

    public static void print(String str){
        System.out.println(str);
        System.out.println("로봇위치: " + robotY + ", " + robotX + ", 방향: " + dir + "========================");
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
