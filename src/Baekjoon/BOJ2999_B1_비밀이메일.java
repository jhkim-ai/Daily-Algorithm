package Baekjoon;

import java.io.*;

public class BOJ2999_B1_비밀이메일 {
    public static void main(String[] args) throws Exception {

        // -------- 데이터 입력 -------- //

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        int N = str.length();
        int y = 0, x = N;

        // -------- Row, Column 설정 -------- //
        // N/2 부터 감소하며, N의 약수를 찾음.
        for (int i = N/2 ; i >= 1; i--) {
            if (i * (N / i) == N && i <= N / i) {
                y = i;
                x = (N/i);
                break;
            }
        }
        
        // -------- 암호문 배열 -------- //
        char[][] arr = new char[y][x];
        
        for (int dx = 0, idx = 0; dx < x; dx++) {
            for (int dy = 0; dy < y; dy++) {
                arr[dy][dx] = str.charAt(idx++);
            }
        }

        // -------- 암호문 재배열 -------- //
        for (int dy = 0; dy < y; dy++) {
            for (int dx = 0; dx < x; dx++) {
                sb.append(arr[dy][dx]);
            }
        }

        System.out.println(sb);

    }

    static String input = "boudonuimilcbsai";
}
