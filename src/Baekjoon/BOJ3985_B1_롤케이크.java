package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ3985_B1_롤케이크 {

    static int L, N;
    static int [] cake;
    static Person[] list;
    static int diff = Integer.MIN_VALUE;
    static int ans = 0;
    static int ans2 = 1;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new StringReader(input));
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        L = Integer.parseInt(br.readLine());
        cake = new int[L+1];

        N = Integer.parseInt(br.readLine());
        list = new Person[N+1];

        // 각 사람의 (p, k) 데이터 입력
        for (int i = 1; i <= N; i++) {
            
            // cake 조각 수
            int cnt = 0;
            
            // ------ 데이터 입력 ------ //
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            // cake 설정
            for (int idx = start; idx <= end; idx++) {
                if(cake[idx] == 0){
                    cake[idx] = i;
                    cnt++;
                }
            }
            
            // Person 객체에 데이터 저장
            list[i] = new Person(i, start, end, cnt, end-start+1);
            
            // 가장 많은 Cake 를 받을 것으로 예상되는 사람 번호
            if (diff < list[i].expect) {
                diff = list[i].expect;
                ans = i;
            }
        }

        // Person Array를 순회하며, 실제로(real) 케이크를 많이 받은 사람의 번호 확인
        for (int p = 1; p<list.length; p++) {

            for (int i = 1; i < cake.length; i++) {
                if(list[p].num == cake[i])
                    list[p].real++;
            }

            if(list[ans2].real < list[p].real)
                ans2 = p;
        }
        System.out.println(ans);
        System.out.println(ans2);


    }

    static class Person {
        int num;
        int start;
        int end;
        int cnt;
        int expect;
        int real=0;

        public Person(int num, int start, int end, int cnt, int expect) {
            this.num = num;
            this.start = start;
            this.end = end;
            this.cnt = cnt;
            this.expect = expect;
        }
    }

    static String input = "10\n" +
            "3\n" +
            "2 4\n" +
            "7 8\n" +
            "6 9";

}
