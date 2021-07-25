package Formula.SlidingWindow;

import java.util.*;
import java.io.*;

public class SlidingWindow {

    private static int len = 3;
    private static int[] arr = {10, 20, 30, 50, 40, 10};

    public static void main(String[] args) throws Exception {

        int res = 0;
        int sum = 0;
        int startIdx = 0;

        for(int i = 0; i < arr.length; ++i){
            sum += arr[i];                // (1) 다음 수 더하기

            // 구간 길이를 만족한다면
            if(i >= len -1){
//                System.out.println("구간의 합 " + sum);  // 출력: 길이가 len 인 구간의 합

                res = Math.max(res, sum); // (2) 구간의 합 중 최대값 구하기
//                System.out.println("최댓(솟)값 " + res);  // 출력 : 구간의 합 중 최댓값

                sum -= arr[startIdx];     // (3) 구간의 맨 앞의 수를 빼기
                startIdx++;               // (4) 구간의 맨 앞 Index 증가
            }
        }
    }
}
