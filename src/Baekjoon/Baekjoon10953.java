package Baekjoon;

import java.util.Scanner;

public class Baekjoon10953 {

    static int T;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();

        // char -> int
        // (char 값 - '0') -> 자동으로 int 형으로 변환
        for (int i = 0; i < T; i++) {
            int sum = 0;
            String str = sc.next();
            sum += str.charAt(0) - '0';
            sum += str.charAt(2) - '0';
            System.out.println(sum);
        }
    }
}
