package StepByStep.day210402;

import java.io.*;
import java.util.*;

public class BOJ1644_G3_소수의연속합 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int ans = 0;

        // N!=1 일 때만 (1은 소수가 X)
        if(N!=1) {
            // 1. N이하의 소수가 원소인 배열 받아오기
            List<Integer> primeNumber = prime(N);

            // 2. 배열안에서 연속합 구하기
            int left = 0;
            int right = 0;
            int sum = primeNumber.get(0);

            while (left <= right) {
                // System.out.println(left + " : " + right + "=" + sum); // 디버깅용

                // 2-1. 값이 N과 같다면, ans++ & left++
                // 주의! right 를 증가하면 에러가 난다.
                // why? (1) N이 소수일 경우 마지막 값(index)에서 right가 증가하면 안되기 때문 + (2) while 조건을 만족 못함
                // ex. 43(index=12)일 경우 left(12):right(12) 인데, 여기서 right 를 증가하면 13으로 Index 를 Over
                if (sum == N) {
                    ans++;
                    sum -= primeNumber.get(left);
                    left++;

                    // right = 잘못된 방법
                    // right++;
                    // sum += primeNumber.get(right);

                }
                // 2-2. 값이 작다면 right 증가
                else if (sum < N) {
                    right++;
                    // 예외처리 : N이 소수가 아닌 경우 Index 에러가 날 수 있음
                    if (right >= primeNumber.size())
                        break;
                    sum += primeNumber.get(right);
                }
                // 2-3. 값이 크다면 left 증가
                else if (sum > N) {
                    sum -= primeNumber.get(left);
                    left++;
                }
            }
        }
        // 출력
        System.out.println(ans);
    }

    // n 이하의 소수로 구성된 배열
    static ArrayList prime(int n) {
        ArrayList<Integer> list = new ArrayList<>();
        boolean[] primeNumber = new boolean[n + 1];
        Arrays.fill(primeNumber, true);

        primeNumber[0] = false;
        primeNumber[1] = false;

        // 에라토스테네의 체
        for (int i = 2; i*i <= n; i++) {
            for (int j = i * 2; j < primeNumber.length; j += i) {
                if (!primeNumber[j])
                    continue;
                primeNumber[j] = false;
            }
        }

        for (int i = 2; i < primeNumber.length; i++) {
            if (primeNumber[i])
                list.add(i);
        }
        return list;
    }
}
