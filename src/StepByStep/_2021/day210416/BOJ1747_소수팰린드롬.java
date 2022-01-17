package StepByStep.day210416;

import java.util.*;
import java.io.*;

public class BOJ1747_소수팰린드롬 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // Idea. 1. 소수인지를 판별        1<= n <= 1,000,000
        //       2. 팰린드롬인지를 판별

        while (true) {
            // 1. 소수 판별
            if (isPrime(N)) {
                // 2. 팰린드롬 판별
                if(isPalindrome(N)){
                    break;
                }
            }
            N++;
        }
        System.out.println(N);
    }

    // 1. 소수 판별
    static boolean isPrime(int num) {
        if (num == 1)       // 1은 소수가 아니다.
            return false;

        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0)
                return false;
        }
        return true;
    }

    // 2. 팰린드롬 판별
    static boolean isPalindrome(int num) {
        String str = String.valueOf(num);
        int left = 0;
        int right = str.length()-1;

        while(left <= right){
            if(str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}
