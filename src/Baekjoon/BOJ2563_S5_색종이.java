package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2563_S5_색종이 {

    static int T;
    static int[][] checked;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        checked = new int[101][101];
        int cnt = 0;
        for (int t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            for (int dy = y; dy < y + 10; ++dy) {
                for (int dx = x; dx < x + 10; ++dx) {
                    if(checked[dy][dx] == 0) {
                        checked[dy][dx] = 1;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
    }
}
