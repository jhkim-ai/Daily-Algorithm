package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon5585_B2_거스름돈 {

    static int N, cnt;
    static int[] arr = {500, 100, 50, 10, 5, 1};

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = 1000 - Integer.parseInt(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            cnt += N / arr[i];
            N %= arr[i];
        }
        System.out.println(cnt);
    }

    static String input = "380";
}
