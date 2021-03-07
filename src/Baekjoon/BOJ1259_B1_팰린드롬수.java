package Baekjoon;

import java.util.Scanner;

// 펠린드롬
public class BOJ1259_B1_팰린드롬수 {
    static public void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {
            boolean res = true;
            int input = sc.nextInt();
            if (input == 0)
                break;

            String arr = Integer.toString(input);

            for (int i = 0; i < arr.length() / 2; i++) {
                int start = i;
                int end = arr.length() - 1 - start;
                if (arr.charAt(start) != arr.charAt(end)) {
                    res = false;
                }
            }

            if (!res) {
                System.out.println("no");
            }else {
                System.out.println("yes");
            }
        }
    }
}
