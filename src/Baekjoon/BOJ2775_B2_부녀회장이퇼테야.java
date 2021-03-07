package Baekjoon;

import java.io.*;
import java.util.Arrays;

public class BOJ2775_B2_부녀회장이퇼테야 {

    static int T, K, N;
    static int[][] room;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            K = Integer.parseInt(br.readLine());
            N = Integer.parseInt(br.readLine());
            room = new int[K+1][N];
            for (int y = 0; y < K+1; y++) {
                for (int x = 0; x < N; x++) {
                    if(x == 0)
                        room[y][x] = 1;
                    else if (y == 0)
                        room[y][x] = x+1;
                    else
                        room[y][x] = room[y-1][x] + room[y][x-1];
                }
            }
            bw.write(room[K][N-1] + "\n");
        }
        bw.flush();
        bw.close();
    }
}
