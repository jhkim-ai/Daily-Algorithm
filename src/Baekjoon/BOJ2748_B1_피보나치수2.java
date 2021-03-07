package Baekjoon;

import java.util.*;
import java.io.*;

public class BOJ2748_B1_피보나치수2 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        Arrays.fill(arr, -1);

        arr[0] = 0;
        arr[1] = 1;

        System.out.println(fibo(N));
    }

    static int fibo(int n){
        if(arr[n] == -1){
            arr[n] = fibo(n-1) + fibo(n-2);
        }
        return arr[n];
    }
}
