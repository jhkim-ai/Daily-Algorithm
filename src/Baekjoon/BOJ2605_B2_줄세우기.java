package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ2605_B2_줄세우기 {
    static int N;
    static List<Integer> list;
    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        list = new LinkedList<>();
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            int tmp = Integer.parseInt(st.nextToken());

            if(tmp==0) {
                list.add(i+1);
            }
            else
                list.add(list.size()-tmp, i+1);
        }

        for (int i : list)
            sb.append(i).append(" ");
        System.out.println(sb);
    }

    static String input = "5\n" +
            "0 1 1 3 2";
}
