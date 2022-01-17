package StepByStep.day210402;

import java.util.*;
import java.io.*;

public class BOJ2023_G5_신기한소수 {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // ---------- 알고리즘 시작 ---------- //
        // Idea. 순열
        // 1. 첫 자리는 2, 3, 5, 7
        // 2. 첫 자리를 제외한 모든 수는 홀수(1, 3, 5, 7, 9) 만

        int[] prime = {2, 3, 5, 7};
        for (int i = 0; i < prime.length; i++) {
            dfs(prime[i], N - 1);
        }

        System.out.println(sb);
    }
    
    // dfs 를 이용
    static void dfs(int num, int len) {
        if (len == 0) {
            sb.append(num).append("\n");
            return;
        }

        // 홀수만 why? 모든 자리수에서 소수가 나와야하기에
        for (int i = 1; i < 10; i += 2) {
            int nextNum = num * 10 + i; // 자릿수 증가
            if (isPrime(nextNum)) { // K개의 자리수가 소수인지 판별
                dfs(nextNum, len - 1);
            }
        }
    }
    
    // 소수 판별 함수
    static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)    // 소수가 아님
                return false;
        }
        return true;
    }
}
