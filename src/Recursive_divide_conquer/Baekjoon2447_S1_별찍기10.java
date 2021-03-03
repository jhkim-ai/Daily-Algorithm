package Recursive_divide_conquer;

import java.util.*;
import java.io.*;

// 분할 정복 (Divide and Conquer)
public class Baekjoon2447_S1_별찍기10 {

    static int N;
    static char[][] map;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        // 데이터 받기
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        
        // 모든 map의 데이터를 ' '로 초기화
        for (int i = 0; i < map.length; i++) {
            Arrays.fill(map[i], ' ');
        }

        // 재귀함수
        star(0, 0, N);

        // 출력
        StringBuilder sb = new StringBuilder();
        for (char[] a : map) {
            for (int i = 0; i < a.length; i++) {
                sb.append(a[i]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void star(int y, int x, int len){
        if(len == 1){
            map[y][x] = '*';
            return;
        }

        int size = len / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(i == 1 && j==1) continue;
                star(y + size*i, x+size*j, size);
            }
        }

    }

    static String input = "27";
}
