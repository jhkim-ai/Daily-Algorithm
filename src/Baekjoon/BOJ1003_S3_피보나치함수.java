package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1003_S3_피보나치함수 {

    static int T;
    static int[][] arr;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        T = Integer.parseInt(br.readLine());
        arr = new int[41][2];
        for (int i = 1; i < 41; i++) {
            fibo(i);
        }

    }

    static void fibo(int n) {
        if (n == 0) {
            return;
        } else if (n == 1) {
            return;
        }
        if(arr[n-1][0] != 0 && arr[n-2][0] != 0 && arr[n-1][1] != 0)

        fibo(n - 1);
        fibo(n - 2);
    }

    static String input = "3\n" +
            "0\n" +
            "1\n" +
            "3";
}
