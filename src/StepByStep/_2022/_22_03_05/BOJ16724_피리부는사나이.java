package StepByStep._2022._22_03_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16724_피리부는사나이 {

    private static int N, M, ans;
    private static char[][] map;
    private static int[][] cycleInfo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        cycleInfo = new int[N][M];

        for(int y = 0; y < N; ++y){
            map[y] = br.readLine().toCharArray();
        }

        ans = 1;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < M; ++x){
                dfs(y, x);
            }
        }
    }

    public static void dfs(int y, int x){

    }
}
