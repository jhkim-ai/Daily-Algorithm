package Baekjoon;

import java.util.ArrayList;
import java.util.Scanner;

// 제로
public class Baekjoon10773 {

    static int N;
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i = 0; i < N; i++) {
            int input = sc.nextInt();
            if (input == 0)
                arr.remove(arr.size() - 1);
            else
                arr.add(input);

        }

        int sum = 0;
        for (int num : arr) {
            sum += num;
        }
        System.out.println(sum);

    }
}
