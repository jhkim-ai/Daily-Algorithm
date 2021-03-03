package Baekjoon;

import java.util.Scanner;

public class Baekjoon10250 {

    static int T;
    static int H, W, N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        for (int t = 0; t < T; t++) {
            H = sc.nextInt();
            W = sc.nextInt();
            N = sc.nextInt();

            int y, x;
            y = N % H;
            x = (N / H) + 1;
            if (N % H == 0) {
                y = H;
                x = N / H;
            }

            System.out.println(y * 100 + x);
        }
    }
}
