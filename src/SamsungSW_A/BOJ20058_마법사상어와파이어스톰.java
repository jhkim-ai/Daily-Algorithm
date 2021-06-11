package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ20058_마법사상어와파이어스톰 {

    public static int N, Q, command, L;
    public static int[] commands;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = (int) Math.pow(2, Integer.parseInt(st.nextToken()));
        Q = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        commands = new int[Q];

        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Q; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        for (int c : commands) {
            command = c;
            start();
        }
        print();
    }

    public static void start() {
        L = (int) Math.pow(2, command);
        System.out.println("L: " + L);
        divide(N, 0, 0);
    }

    public static void divide(int num, int y, int x) {

        if (num == L) {
            circulation(y, x);
            return;
        }

        int n = num / 2;
        divide(n, y, x);
        divide(n, y + n, x);
        divide(n, y, x + n);
        divide(n, y + n, x + n);
    }

    public static void circulation(int y, int x) {
        List<Integer>[] arrList = new ArrayList[L];

        // ArrayList 배열 초기화
        for (int i = 0; i < L; ++i) {
            arrList[i] = new ArrayList<>();
        }

        // 열을 배열에 저장
        int idx = 0;
        for (int nx = x; nx < x + L; nx++) {
            for (int ny = y; ny < y + L; ny++) {
                arrList[idx].add(map[ny][nx]);
            }
            ++idx;
        }

        // 재배열
        int i = 0;
        for (int ny = y; ny < y + L; ny++) {
            int j = L - 1;
            for (int nx = x; nx < x + L; nx++) {
                map[ny][nx] = arrList[i].get(j);
                --j;
            }
            ++i;
        }
    }

    public static void print() {
        System.out.println("==============");
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
