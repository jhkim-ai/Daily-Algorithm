package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ10818_B3_최소최대 {

    static int N;
    static int[] Arr;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Arr = new int[N];

        for (int i = 0; i < N; i++) {
            int sum = 0;
            while (sc.hasNext()) {
                sum += sc.nextInt();
            }
            System.out.println(sum);
        }
    }
}
