package SWEA;

import java.util.*;
import java.io.*;

public class SWEA4014_활주로건설 {

    static int N, X, ans;
    static int[][] map, transposedMap;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());
            ans = 0;
            map = new int[N][N];
            transposedMap = new int[N][N];

            for (int y = 0; y < N; y++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                    transposedMap[y][x] = map[x][y];
                }
            }

            // 1. 가로 검사
            checkRow();
            // 2. 세로 검사
            checkCol();
        }
    }

    static boolean checkRow(){
        for (int y = 0; y < N; y++) {
            int sameHeight = 1;

            for (int x = 1; x < N; x++) {
                // 높이가 같으면 pass
                if(map[y][x] == map[y][x-1]) {
                    sameHeight++;
                    continue;
                }

                // 높이가 다르면 check
                if(map[y][x] != map[y][x-1]){
                    // 올라감
                    if(map[y][x] == map[y][x-1]+1){
                        if(sameHeight >= X){
                            ans++;
                            break;
                        }
                    }
                    // 내려감
                    if(map[y][x] == map[y][x] -1){

                    }
                }
            }
        }

        return false;
    }

    static boolean checkCol(){
        return false;
    }

}
