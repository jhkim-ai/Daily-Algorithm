package Category.Dynamic_Programming;

import java.io.*;
import java.util.*;

public class BOJ9625_B1_BABBA {

    static int N;
    static int[] a;
    static int[] b;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // a 초기화
        a = new int[N];
        a[0] = 1;
        if(N>1)
            a[1] = 0;

        // b 초기화
        b = new int[N];
        b[0] = 0;
        if(N>1)
            b[1] = 1;

        // (1). Bottom-Up
        // dp_Bottom_Up();
        // (2). 다른 Bottom-Up (규칙 발견)
        dp_Bottom_Up2();

        System.out.println(sb);
    }

    // (1). Bottom-Up (for 문)
    static void dp_Bottom_Up(){

        for (int i = 2; i < N; i++) {
            a[i] = a[i-1] + a[i-2];
            b[i] = b[i-1] + b[i-2];
        }


        sb.append(a[N-1]).append(" ").append(b[N-1]);
    }

    // (2). 다른 Bottom-Up (규칙 발견)
    static void dp_Bottom_Up2(){
        int A = 1;
        int B = 0;
        int sum = 0;

        for (int i = 1; i <N+1 ; i++) {
            sum = A + B;
            A = B;
            B = sum;
        }

        sb.append(A).append(" ").append(B);
    }
}
