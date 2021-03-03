package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static java.lang.Math.sqrt;

public class Baekjoon11653 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int i = 2; i <= sqrt(N); i++) {
            while(N % i == 0){
                sb.append(i).append("\n");
                N /= i;
            }
        }

        if(N != 1)
            sb.append(N);
        System.out.println(sb);
    }
}
