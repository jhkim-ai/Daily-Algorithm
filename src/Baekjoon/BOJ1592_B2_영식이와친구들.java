package Baekjoon;

import java.io.*;
import java.util.*;

public class BOJ1592_B2_영식이와친구들 {

    static int N, M, L;
    static int[] arr;
    static int cnt = 0;

    public static void main(String[] args) throws Exception {

        // solution1();
        solution2();
    }

    static void solution2() throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        List<Point> list = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            list.add(new Point(0, i+1));
        }

        while (true) {

            list.get(0).p++;

            if (list.get(0).p == M)
                break;

            if (list.get(0).p % 2 == 0) {
                for (int i = 0; i < L; i++) {
                    list.add(0, list.get(list.size() - 1));
                    list.remove(N);
                }
            } else {
                for (int i = 0; i < L; i++) {
                    list.add(list.get(0));
                    list.remove(0);
                }
            }

            cnt++;
        }

        System.out.println(cnt);
    }

    static class Point {
        int p;
        int idx;

        public Point(int p, int idx) {
            this.p = p;
            this.idx = idx;
        }

        @Override
        public String toString() {
            return Integer.toString(this.p);
        }
    }

    static void solution1() throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];
        int idx = 0;

        while (true) {

            arr[idx]++; // 공을 받은 횟수 증가

            // M만큼 받을 시, 끝
            if (arr[idx] == M)
                break;

            // 공을 받은 횟수가 짝수 -> 반시계
            if (arr[idx] % 2 == 0) {
                idx -= L;
                // 음수가 될 경우, 배열의 오른쪽(큰 idx)으로 넘어간다.
                if (idx < 0) {
                    idx = N + idx;
                }
            }
            // 공을 받은 횟수가 홀수 -> 시계
            else {
                // 배열의 크기를 넘을 경우, 배열의 왼쪽(작은 idx)으로 넘어간다.
                idx += L;
                if (idx >= N) {
                    idx -= N;
                }
            }

            cnt++;  // 공을 던진 횟수
        }
        System.out.println(cnt);
    }

    static String input = "5 3 2";
}
