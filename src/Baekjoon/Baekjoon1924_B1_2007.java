package Baekjoon;

import java.io.*;
import java.util.*;

public class Baekjoon1924_B1_2007 {

    static int X, Y;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        // 월, 누적 합
        int[] sumMonth = new int[13];

        for (int i = 1; i < 13; i++) {
            if(i == 2)
                sumMonth[i] = 28 + sumMonth[i-1];
            else if(i==4 || i == 6 || i==9 || i==11)
                sumMonth[i] = 30 + sumMonth[i-1];
            else
                sumMonth[i] = 31 + sumMonth[i-1];
        }
        
        String[] str = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int tmp = sumMonth[X-1] + Y;

        System.out.println(str[tmp%7]);
    }

    static String input = "9 2";
}
