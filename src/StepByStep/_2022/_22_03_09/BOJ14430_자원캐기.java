package StepByStep._2022._22_03_09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ14430_자원캐기 {

    private static int N, M;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
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

        // 누적합
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                if(!isIn(y-1, x) && !isIn(y, x-1)){
                    continue;
                } else if(!isIn(y-1, x)){
                    map[y][x] += map[y][x-1];
                } else if(!isIn(y, x-1)){
                    map[y][x] += map[y-1][x];
                } else {
                    map[y][x] += Math.max(map[y-1][x], map[y][x-1]);
                }
            }
        }

        System.out.println(map[N-1][M-1]);
    }

    public static boolean isIn(int y, int x){
        return y >= 0 && x >= 0 && y < N && x < M;
    }
}
