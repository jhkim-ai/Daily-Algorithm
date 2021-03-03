package StepByStep.day210227;

import java.io.*;
import java.util.*;

public class Baekjoon1052_S1_물병 {

    static int N, K;
    static int ans;
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // ---------- 알고리즘 시작 ---------- //

        ans = 0;
        while(true) {
            int tmp = N;
            int cnt = 0;
            while (tmp != 0) {
                if (tmp % 2 == 1)
                    cnt++;
                tmp /= 2;
            }

            if (cnt <= K)
                break;
            ans++;
            N++;
        }
        System.out.println(ans);
    }
    static String input = "3 1";
}
