package Baekjoon;

import java.util.Scanner;

// 설탕 배달
public class Baekjoon2839 {

    static int N;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        if (N == 4 || N == 7)
            System.out.println(-1);
        else if (N % 5 == 0)
            System.out.println(N / 5);
        else if (N % 5 == 1)
            System.out.println(N / 5 + 1);
        else if (N % 5 == 2)
            System.out.println(N / 5 + 2);
        else if (N % 5 == 3)
            System.out.println(N / 5 + 1);
        else if (N % 5 == 4)
            System.out.println(N / 5 + 2);
    }

}
