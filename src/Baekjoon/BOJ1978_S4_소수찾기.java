package Baekjoon;

import java.util.Scanner;

public class BOJ1978_S4_소수찾기 {

    static int N;
    static int cnt = 0;
    static int MAX = 1001;
    static int[] Arr = new int[MAX];

    public static void main(String[] args) {

        for (int i = 2; i < Arr.length; i++) {
            Arr[i] = i;
        }

        for (int i = 2; i < MAX; i++) {
            if (Arr[i] == 0)
                continue;
            for (int j = 2 * i; j < MAX; j += i)
                Arr[j] = 0;
        }

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        for (int i =0;i<N;i++){
            int input = sc.nextInt();
            if (Arr[input] != 0)
                cnt++;
        }
        System.out.println(cnt);
    }


//    무엇이 문제 인가?

//    static int N;
//    static int cnt = 0;
//
//    public static void main(String[] args) {
//
//        Scanner sc = new Scanner(System.in);
//        N = sc.nextInt();
//
//        for (int idx = 0; idx < N; idx++) {
//            int check = 0;
//            int input = sc.nextInt();
//            int to = (int)Math.sqrt(input);
//
//            if (input == 2 || input == 3) {
//                cnt++;
//                continue;
//            }
//
//            for (int i = 2; i <= to; i++) {
//                if (input % i == 0)
//                    break;
//                check++;
//            }
//            if (check == to -1)
//                cnt++;
//        }
//        System.out.println(cnt);
//    }
}
