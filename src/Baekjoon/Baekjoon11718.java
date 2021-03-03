package Baekjoon;

import java.util.Arrays;
import java.util.Scanner;

public class Baekjoon11718 {

    static int N;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        int [] arr = new int [N];
        for (int i = 0; i < N ; i++){
            arr[i] = sc.nextInt();
        }

        Arrays.sort(arr);
        for(int n : arr){
            System.out.println(n);
        }
    }
}
