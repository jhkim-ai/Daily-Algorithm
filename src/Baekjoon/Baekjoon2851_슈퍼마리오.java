package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon2851_슈퍼마리오 {

    static final int N = 10;
    static int[] arr = new int[N];
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        // 누적합 배열 넣기
        for (int i = 0 ,j=0; i < N; i++) {

            // 0번째는 그냥 pass
            if(i==0)
                arr[i] = Integer.parseInt(br.readLine());

            // 1번째부터~9번째까지 누적합 계산
            else{
                arr[i] = arr[j++] + Integer.parseInt(br.readLine());

                // 100이 나오면, 그대로 끝
                if(arr[i] == 100){
                    ans = 100;
                    break;
                }
            }

            // 누적합 계산 후, 100에 가까운 수를 비교
            // 누적합 - 100 의 절댓값이 작은 수가 100에 가까운 수
            int tmp = Math.abs(100-arr[i]);
            int now = Math.abs(100-ans);

            if(tmp == now)
                ans = Math.max(arr[i], ans);
            else if(tmp < now)
                ans = arr[i];

        }

        System.out.println(ans);
    }

    static String input = "10\n" +
            "20\n" +
            "30\n" +
            "40\n" +
            "50\n" +
            "60\n" +
            "70\n" +
            "80\n" +
            "90\n" +
            "100";
}
