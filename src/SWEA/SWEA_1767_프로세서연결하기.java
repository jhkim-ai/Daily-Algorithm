package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1767_프로세서연결하기 {

    public static int T, N;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; ++t){
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for (int y = 0; y < N; y++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int x = 0; x < N; x++) {
                    map[y][x] = Integer.parseInt(st.nextToken());
                }
            }
        }
    }
}
