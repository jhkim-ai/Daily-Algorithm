package StepByStep._2021.day210227;

import java.io.*;
import java.util.*;

public class BOJ13305_S4_주유소 {

    static int N;
    static int[] val;
    static int[] dis;
    static long ans;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;

        // ------------- 데이터 입력 ------------- //

        N = Integer.parseInt(br.readLine());
        dis = new int[N-1];
        val = new int[N];
        ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < dis.length; i++) {
            dis[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < val.length; i++) {
            val[i] = Integer.parseInt(st.nextToken());
        }

        // ------------- 알고리즘 시작 ------------- //

        int idx = 0;
        long oil = Long.MAX_VALUE;

        while(idx != N-1){
            oil = Math.min(val[idx], oil);
            ans += 1L * oil*dis[idx++];
        }

        System.out.println(ans);
    }

    static String input = "5\n" +
            "3 2 1 4\n" +
            "5 8 9 4 1";
}
