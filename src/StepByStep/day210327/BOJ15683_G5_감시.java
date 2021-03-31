package StepByStep.day210327;

import java.util.*;
import java.io.*;

public class BOJ15683_G5_감시 {

    static int N, M;
    static int[][] map;
    static int ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine()," ");
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
                if(map[y][x] == 0)
                    ans++;
            }
        }
    }
}
