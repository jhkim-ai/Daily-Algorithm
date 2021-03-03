package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

// Counting 정렬
public class Baekjoon10807 {

    static int N, V;
    static int[] arr = new int[201];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(st.nextToken())+100]++;
        }

        V = Integer.parseInt(br.readLine());
        System.out.println(arr[V+100]);

    }
}
