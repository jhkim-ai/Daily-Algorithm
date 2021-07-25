package Category.Prefix_Sum;

import java.util.*;
import java.io.*;

public class BOJ1041_주사위 {

    private static long N;
    private static int[] dice;
    private static long sum1, sum2, sum3;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[6];

        sum1 = Long.MAX_VALUE;
        sum2 = Long.MAX_VALUE;
        sum3 = Long.MAX_VALUE;

        // 주사위 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; ++i){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        sum1 = minSubArray(1);
        sum2 = minSubArray(2);
        sum3 = minSubArray(3);

        long ans = minSumPlane();
        System.out.println(ans);
    }

    // 면의 최소합 구하기
    public static long minSumPlane(){
        long sum = 0;

        // 1층 ~ N-1층 까지의 합
        sum += sum2*4;
        sum += (N-2) * 4 * sum1;
        sum *= (N-1);

        // N 층
        sum += sum3 * 4;
        sum += (N-2) * 4d * sum2;
        sum += (N-2) * (N-2d) * sum1;

        // 주의!. N 을 parseInt 로 받으면 Integer 간의 곱으로 된 후 casting 되기 때문에 조심해야한다.
        return sum;
    }

    // sliding window 를 이용한 배열의 부분 최소합 구하기
    public static long minSubArray(int len){
        long minValue = Long.MAX_VALUE;
        long sum = 0;
        int start = 0;
        for(int i = 0; i < 6; ++i){
            sum += dice[i];
            if(i >= len-1){
                minValue = Math.min(sum, minValue);
                sum -= dice[start++];
            }
        }
        return minValue;
    }
}
