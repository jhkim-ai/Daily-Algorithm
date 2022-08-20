package StepByStep._2022._22_01_15;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16931_겉넓이구하기 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, ans;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < M; ++x){
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // 알고리즘 시작
        ans += getUpAndBottom(); // 윗면 + 아랫면
        ans += getRest4Area();   // 4방향 옆면

        System.out.println(ans);
    }

    public static int getUpAndBottom(){
        return N*M*2;
    }

    // 1. 각 좌표에서 4방향의 블럭들을 탐색.
    // 2. 자신보다 높이가 낮은 블럭탑만 높이 차를 더한다.
    public static int getRest4Area(){
        int sum = 0;

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                int cnt = map[y][x];
                for(int d = 0; d < 4; ++d){
                    int ny = y + dy[d];
                    int nx = x + dx[d];
                    if(!isIn(ny, nx)){ // 가장 바깥(외곽)면일 경우우
                       sum += cnt;
                        continue;
                    }
                    if(cnt > map[ny][nx]) { // 현재 탑 높이보다 낮은 높이의 탑일 때
                        sum += cnt - map[ny][nx];
                    }
                }
            }
        }

        return sum;
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
