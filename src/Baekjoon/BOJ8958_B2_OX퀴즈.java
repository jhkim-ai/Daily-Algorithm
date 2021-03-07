package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ8958_B2_OX퀴즈 {

    static int T;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            int sum = 0;
            int plus = 0;
            String str = br.readLine();
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == 'X') {
                    plus = 0;
                } else {
                    plus++;
                    sum += plus;
                }
            }
            sb.append(sum).append("\n");
        }
        System.out.println(sb);
    }

    static String input = "5\n" +
            "OOXXOXXOOO\n" +
            "OOXXOOXXOO\n" +
            "OXOXOXOXOXOXOX\n" +
            "OOOOOOOOOO\n" +
            "OOOOXOOOOXOOOOX";
}
