package Baekjoon;

import java.util.*;
import java.io.*;

// 나머지 (Bronze 2)
public class BOJ3052_B2_나머지 {
    
    // counting 정렬 이용
    static int[] cnt = new int[42];

    public static void main(String[] args) throws Exception {

        // -------- 데이터 입력 -------- //

        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        int num = 0;
        for (int i = 0; i < 10; i++) {
            // 데이터를 받는과 동시에 나머지를 구하여
            // 나머지를 index 로 갖는 cnt 배열에 넣는다
            num = Integer.parseInt(br.readLine());
            cnt[num%42]++;
        }

        // 0 이 아닌 index 를 찾아 counting 한다.
        int ans = 0;
        for (int i = 0; i < 42; i++) {
            if(cnt[i] != 0)
                ans++;
        }

        System.out.println(ans);
    }

    static String input = "42\n" +
            "84\n" +
            "252\n" +
            "420\n" +
            "840\n" +
            "126\n" +
            "42\n" +
            "84\n" +
            "420\n" +
            "126";
}
