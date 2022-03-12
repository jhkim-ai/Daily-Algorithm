package StepByStep._2022._22_02_19;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ21938_영상처리 {

    private static final int[] dy = {-1, 1, 0, 0};
    private static final int[] dx = {0, 0, -1, 1};

    private static int N, M, T;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        int ans = 0;

        for(int y = 0; y < N; ++y){
            st = new StringTokenizer(br.readLine(), " ");
            int sumRGB = 0;
            for(int x = 0; x < M * 3; ++x){
                sumRGB += Integer.parseInt(st.nextToken());
                if(x % 3 == 2){
                    map[y][x/3] = sumRGB / 3;
                    sumRGB = 0;
                }
            }
        }

        T = Integer.parseInt(br.readLine());
        setNewDisplay();

        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(!visited[y][x] && map[y][x] != 0) {
                    ans++;
                    visited[y][x] = true;
                    getCntOfObject(y, x);
                }
            }
        }

        System.out.println(ans);
    }

    public static void setNewDisplay(){
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(map[y][x] >= T){
                    map[y][x] = 255;
                } else {
                    map[y][x] = 0;
                }
            }
        }
    }

    public static void getCntOfObject(int y, int x){
        for(int d = 0; d < 4; ++d){
            int ny = y + dy[d];
            int nx = x + dx[d];

            if(!isIn(ny, nx) || visited[ny][nx] || map[ny][nx] == 0) {
                continue;
            }

            visited[ny][nx] = true;
            getCntOfObject(ny, nx);
        }
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
