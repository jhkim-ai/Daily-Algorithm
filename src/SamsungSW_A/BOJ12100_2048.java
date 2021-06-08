package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ12100_2048 {

    public static final int[] dy = {-1, 1, 0, 0};   // 상, 하, 좌, 우
    public static final int[] dx = {0, 0, -1, 1};

    public static int N, ans;
    public static int[][] map, tmpMap;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        ans = Integer.MIN_VALUE;
        map = new int[N][N];

        for (int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; ++x) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        // ========== 알고리즘 시작 ========== //
        // 완전 탐색 (중복 순열)
        dupPermutation(5, new int[5]);
        System.out.println(ans);
    }

    // 중복 순열
    public static void dupPermutation(int cnt, int[] selected) {
        if (cnt == 0) {
            initMap(); // Map 초기화
            for(int dir : selected){
                move(dir);
                doSum(dir);
                move(dir);
            }
            ans = Math.max(ans, getMax());
            return;
        }

        for (int i = 0; i < 4; ++i) {
            selected[selected.length - cnt] = i;
            dupPermutation(cnt - 1, selected);
        }
    }

    // 움직이기
    public static void move(int dir) {
        Queue<Integer> tmpStore = new LinkedList<>();
        switch (dir) {
            // 상 (위에서 아래로 임시저장)
            case 0:
                for (int x = 0; x < N; ++x) {
                    // 0이 아닌 값은 Queue에 저장하고 모든 값을 0으로 만들기
                    for (int y = 0; y < N; ++y) {
                        if(tmpMap[y][x] == 0) continue;  // 빈 곳은 pass
                        tmpStore.offer(tmpMap[y][x]);
                        tmpMap[y][x] = 0;
                    }
                    // 위에서부터 차례대로 tmpMap에 다시 넣기 => 순서대로 들어간다
                    for (int y = 0; y < N; ++y) {
                        if(tmpStore.isEmpty()) break;
                        tmpMap[y][x] = tmpStore.poll();
                    }
                }
                break;
            // 하 (아래에서 위로 임시저장)
            case 1:
                for (int x = 0; x < N; ++x) {
                    // 0이 아닌 값은 Queue에 저장하고 모든 값을 0으로 만들기
                    for (int y = N-1; y >= 0; --y) {
                        if(tmpMap[y][x] == 0) continue;  // 빈 곳은 pass
                        tmpStore.offer(tmpMap[y][x]);
                        tmpMap[y][x] = 0;
                    }
                    // 아래부터 차례대로 tmpMap에 다시 넣기 => 순서대로 들어간다
                    for (int y = N-1; y >=0; --y) {
                        if(tmpStore.isEmpty()) break;
                        tmpMap[y][x] = tmpStore.poll();
                    }
                }
                break;
            // 좌 (왼쪽에서 오른쪽으로 임시저장)
            case 2:
                for (int y = 0; y < N; ++y) {
                    // 0이 아닌 값은 Queue에 저장하고 모든 값을 0으로 만들기
                    for (int x = 0; x < N; ++x) {
                        if(tmpMap[y][x] == 0) continue;  // 빈 곳은 pass
                        tmpStore.offer(tmpMap[y][x]);
                        tmpMap[y][x] = 0;
                    }
                    // 위에서부터 차례대로 tmpMap에 다시 넣기 => 순서대로 들어간다
                    for (int x = 0; x < N; ++x) {
                        if(tmpStore.isEmpty()) break;
                        tmpMap[y][x] = tmpStore.poll();
                    }
                }
                break;
            // 우 (오른쪽에서 왼쪽으로 저장)
            case 3:
                for (int y = 0; y < N; ++y) {
                    // 0이 아닌 값은 Queue에 저장하고 모든 값을 0으로 만들기
                    for (int x = N-1; x >= 0; --x) {
                        if(tmpMap[y][x] == 0) continue;  // 빈 곳은 pass
                        tmpStore.offer(tmpMap[y][x]);
                        tmpMap[y][x] = 0;
                    }
                    // 위에서부터 차례대로 tmpMap에 다시 넣기 => 순서대로 들어간다
                    for (int x = N-1; x >=0; --x) {
                        if(tmpStore.isEmpty()) break;
                        tmpMap[y][x] = tmpStore.poll();
                    }
                }
        }
    }

    // 합치기
    public static void doSum(int dir){
        switch(dir){
            // 상
            case 0:
                for(int x = 0; x < N; ++x){
                    for(int y = 0; y < N-1; ++y){
                        if(tmpMap[y][x] == tmpMap[y+1][x]){ // 바로 아래의 숫자가 같을 때
                            tmpMap[y][x] += tmpMap[y][x];   // 위로 합치기
                            tmpMap[y+1][x] = 0;             // 합쳐진(아래) 숫자 0으로 바꾸기
                            ++y;                            // 합쳐진 숫자는 조회할 필요 없으니 pass
                        }
                    }
                }
                break;
            // 하
            case 1:
                for(int x = 0; x < N; ++x){
                    for(int y = N-1; y > 0; --y){
                        if(tmpMap[y][x] == tmpMap[y-1][x]){ // 바로 위의 숫자가 같을 때
                            tmpMap[y][x] += tmpMap[y][x];   // 아래로 합치기
                            tmpMap[y-1][x] = 0;             // 합쳐진(위) 숫자 0으로 바꾸기
                            --y;                            // 합쳐진 숫자는 조회할 필요 없으니 pass
                        }
                    }
                }
                break;
            // 좌
            case 2:
                for(int y = 0; y < N; ++y){
                    for(int x = 0; x < N-1; ++x){
                        if(tmpMap[y][x] == tmpMap[y][x+1]){
                            tmpMap[y][x] += tmpMap[y][x];
                            tmpMap[y][x+1] = 0;
                            ++x;
                        }
                    }
                }
                break;
            // 우
            case 3:
                for(int y = 0; y < N; ++y){
                    for(int x = N-1; x > 0; --x){
                        if(tmpMap[y][x] == tmpMap[y][x-1]){
                            tmpMap[y][x] += tmpMap[y][x];
                            tmpMap[y][x-1] = 0;
                            --x;
                        }
                    }
                }
        }
    }

    // 5번 이동 후, 최댓값 구하기
    public static int getMax(){
        int maxValue = Integer.MIN_VALUE;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                maxValue = Math.max(maxValue, tmpMap[y][x]);
            }
        }

        return maxValue;
    }

    // map 초기화
    public static void initMap() {
        tmpMap = new int[N][N];
        for (int y = 0; y < N; ++y) {
            tmpMap[y] = map[y].clone();
        }
    }

    // debugging 용
    public static void print(int[][] map) {
        System.out.println("============");
        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }
}
