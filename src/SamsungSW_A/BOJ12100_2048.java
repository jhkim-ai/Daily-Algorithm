package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ12100_2048 {

    public static final int[] dy = {-1, 1, 0, 0};   // 상, 하, 좌, 우
    public static final int[] dx = {0, 0, -1, 1};

    public static int N;
    public static int[][] map, tmpMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        tmpMap = new int[N][N];

        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());

            }
        }

        // ========== 알고리즘 시작 ========== //
        // 완전 탐색 (중복 순열)
        dupPermutation(5, new int[5]);
//        print();
    }

    // 중복 순열
    public static void dupPermutation(int cnt, int[] selected){
        if(cnt == 0){
            tmpMap = initMap(); // Map 초기화
            return;
        }

        for(int i = 0; i < 4; ++i){
            selected[selected.length - cnt] = i;
            dupPermutation(cnt-1, selected);
        }
    }

    // 상
    public static void moveUp(){
        int empty = -1;
        for(int x = 0; x < N; ++x){
            for(int y = 0; y < N; ++y){
                if(tmpMap[y][x] == 0) {
                    empty = y;  // 비어있는 위치 저장
                    continue;
                }
                tmpMap[y][empty] = tmpMap[y][x]; // 위로 이동
                tmpMap[y][x] = 0;                // 이전 값 0으로 초기화
            }
        }
    }

    // map 초기화
    public static int[][] initMap(){
        int[][] tmp = new int[N][N];

        for(int y = 0; y < N; ++N){
            for(int x = 0; x < N; ++N){
                tmp[y][x] = map[y][x];
            }
        }
        return tmp;
    }

    // debugging 용
    public static void print() {
        System.out.println("============");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
