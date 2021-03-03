package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Baekjoon7568 {

    static int N;
    static List<Person> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        list = new ArrayList<Person>();
        int[] ranking = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            list.add(new Person(w, h, i));
            ranking[i] = 1;
        }

        for (int i = 0; i<list.size(); i++){
            for (int j = 0; j < list.size(); j++) {
                if(list.get(i).w > list.get(j).w && list.get(i).h > list.get(j).h ){
                    ranking[j]++;
                }
            }
        }



        StringBuilder sb = new StringBuilder();
        for (int tmp : ranking) {
            sb.append(tmp).append(" ");
        }
        System.out.println(sb);
    }

    static class Person implements Comparable<Person> {
        int w;
        int h;
        int idx;

        public Person(int w, int h, int idx) {
            this.w = w;
            this.h = h;
            this.idx = idx;
        }

        @Override
        public int compareTo(Person o) {
            return o.w - this.w;
        }

        @Override
        public String toString() {
            return "(" + w + ", " + h + ") " + idx;
        }
    }
}
