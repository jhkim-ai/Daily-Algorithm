package StepByStep._2021.day210515;

import java.io.*;
import java.util.*;

public class BOJ9421_소수상근수 {

    static int N;
    static boolean[] isPrime;
    static int[] dp;    // 1: 소수상근수, 0: 아직 진행 x, -1: 소수상근수 x
    static List<Integer> ans, list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;
        dp = new int[500];
        ans = new ArrayList<>();

        for (int i = 2; i <= N; ++i) {
            for (int j = 2; j * j <= N; ++j) {
                int num = i * j;
                if (num > N) break;
                isPrime[num] = false;

            }
        }
        for (int i = 2; i < isPrime.length; i++) {
            if(isPrime[i]){
                list = new ArrayList<>();
//                System.out.println(i + "*********");
                if (isRight(i) == 1) {
                    ans.add(i);
                }
            }

        }
        System.out.println(ans);
    }

    // 상근수 조합
    public static int isRight(int n) {
        // 기저조건 : 소수상근수 불가능할 때,
        if(n == -1){
            for (int num : list)
                dp[num] = -1;
            return -1;
        }
        // 기저조건 : 소수상근수 가능할 때,
        if (n == 1) {
            for (int num : list)
                dp[num] = 1;
            return 1;
        }

        int sum = 0;
        while (n > 0) {
            int digit = n % 10;     // 자릿수
            sum += digit * digit;   // 제곱
            n /= 10;                // 다음 반복
        }

//        System.out.println(sum);

        if(dp[sum] == -1){
            return -1;
        }

        // 수가 반복된다면, dp에 기록 후 종료.
        if (list.contains(sum)) {
            return isRight(-1);
        }
        // 추가
        list.add(sum);

        // 기존에 소수상근수가 되었다면, 바로 출력
        if (dp[sum] == 1) {
            return isRight(1);
        }
        return isRight(sum);
    }
}
