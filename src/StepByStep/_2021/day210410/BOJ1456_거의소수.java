package StepByStep._2021.day210410;

import java.util.*;
import java.io.*;

public class BOJ1456_거의소수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        int ans = 0;
        int maxRange = (int) Math.sqrt(B);
        boolean[] isNotPrime = new boolean[maxRange + 1];
        isNotPrime[0] = true;
        isNotPrime[1] = true;

        for (int i = 2; i * i <= maxRange; i++) {
            for (int j = i * 2; j < isNotPrime.length; j += i) {
                if (isNotPrime[j])
                    continue;
                isNotPrime[j] = true;
            }
        }

        for (int i = 2; i < isNotPrime.length; i++) {
            if (isNotPrime[i]) continue;

            int num = i;
            while (num <= B/i) {
                if (num < A) {
                    num *= i;
                    continue;
                }
                ans++;
                num *= i;
            }
        }

        System.out.println(ans);
    }
}
