package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.StringTokenizer;

public class Baekjoon14696_B1_딱지놀이 {

    static int N;
    static int[] arr;
    static int[] arr2;

    public static void main(String[] args) throws Exception {

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine());
        char ans = ' ';

        for (int i = 0; i < N; i++) {
            arr = new int[6];
            arr2 = new int[6];

            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                arr[Integer.parseInt(st.nextToken())]++;
            }
            st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                arr2[Integer.parseInt(st.nextToken())]++;
            }

            if (arr[4] != arr2[4]) {
                if (arr[4] > arr[4])
                    ans = 'A';
                else
                    ans = 'B';
            } else {
                if (arr[3] != arr2[3]) {
                    if (arr[3] > arr[3])
                        ans = 'A';
                    else
                        ans = 'B';
                } else {
                    if (arr[2] != arr[2]) {
                        if (arr[2] > arr[2])
                            ans = 'A';
                        else
                            ans = 'B';
                    }else{
                        if (arr[1] != arr[1]) {
                            if (arr[1] > arr[1])
                                ans = 'A';
                            else
                                ans = 'B';
                        }else{
                            ans = 'D';
                        }
                    }
                }
            }
            System.out.println(ans);
        }
    }

    static String input = "5\n" +
            "1 4\n" +
            "4 3 3 2 1\n" +
            "5 2 4 3 2 1\n" +
            "4 4 3 3 1\n" +
            "4 3 2 1 1\n" +
            "4 2 3 2 1\n" +
            "4 4 3 2 1\n" +
            "3 4 3 2\n" +
            "5 4 4 2 3 1\n" +
            "5 4 2 4 1 3";
}
