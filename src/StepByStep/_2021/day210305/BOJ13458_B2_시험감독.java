package StepByStep._2021.day210305;

import java.io.*;
import java.util.*;

public class BOJ13458_B2_시험감독 {

    static int N, A, B;
    static int[] arr;
    static long ans;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());   // 총 감독관
        B = Integer.parseInt(st.nextToken());   // 부 감독간

        for (int i = 0; i < arr.length; i++) {
            ans++;
            if(arr[i] <= A) {
                continue;
            }else{
                ans += (arr[i] - A) / B;
                if((arr[i]-A)%B != 0)
                    ans++;
            }
        }
        System.out.println(ans);
    }

    static String input="5\n" +
            "10 9 10 9 10\n" +
            "7 2";
}
