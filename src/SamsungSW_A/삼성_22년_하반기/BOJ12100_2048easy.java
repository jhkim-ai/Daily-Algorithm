package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ12100_2048easy {

    private static final int MAX_CNT = 5;

    private static int N, ans;
    private static int[][] originMap;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        originMap = new int[N][N];

        for(int y = 0; y < N; ++y) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x) {
                originMap[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        combination(MAX_CNT, new int[MAX_CNT]);
        System.out.println(ans);
    }

    public static void combination(int cnt, int[] selected) {
        if(cnt == 0){
            int[][] map = initMap();
            for(int d : selected) {
                move(d, map);
            }
            int getMaxValue = getMaxValue(map);
            ans = Math.max(ans, getMaxValue);
            return;
        }

        for(int d = 0; d < 4; ++d){
            selected[selected.length - cnt] = d;
            combination(cnt - 1, selected);
        }
    }

    public static int getMaxValue(int[][] map) {
        int maxValue = Integer.MIN_VALUE;
        for(int y = 0; y < N; ++y){
            for(int x = 0; x < N; ++x){
                if(map[y][x] == 0) continue;
                maxValue = Math.max(maxValue, map[y][x]);
            }
        }

        return maxValue;
    }

    public static void move(int d, int[][] map){
        switch (d) {
            case 0:
                moveUp(map);
                break;
            case 1:
                moveDown(map);
                break;
            case 2:
                moveLeft(map);
                break;
            case 3:
                moveRight(map);
                break;
        }
    }

    public static void moveUp(int[][] map) {
        List<Integer> tmpList = new ArrayList<>();
        for(int x = 0; x < N; ++x) {
            tmpList.clear();

            for(int y = 0; y < N; ++y){
                if(map[y][x] == 0) continue;
                tmpList.add(map[y][x]);
            }

            int newIdx = 0;
            int size = tmpList.size();
            for(int idx = 0; idx < size; ++idx) {
                int val = tmpList.get(idx);
                int nextIdx = idx + 1;
                if(nextIdx < size) {
                    if(val == tmpList.get(nextIdx)) {
                        val += val;
                        ++idx;
                    }
                }
                map[newIdx][x] = val;
                newIdx++;
            }

            for(int idx = newIdx; idx < N; ++idx) {
                map[idx][x] = 0;
            }
        }
    }

    public static void moveDown(int[][] map) {
        List<Integer> tmpList = new ArrayList<>();
        for(int x = 0; x < N; ++x) {
            tmpList.clear();

            for(int y = N-1; y >= 0; --y){
                if(map[y][x] == 0) continue;
                tmpList.add(map[y][x]);
            }

            int newIdx = N-1;
            int size = tmpList.size();
            for(int idx = 0; idx < size; ++idx) {
                int val = tmpList.get(idx);
                int nextIdx = idx + 1;
                if(nextIdx < size) {
                    if(val == tmpList.get(nextIdx)) {
                        val += val;
                        ++idx;
                    }
                }
                map[newIdx][x] = val;
                newIdx--;
            }

            for(int idx = newIdx; idx >= 0; --idx) {
                map[idx][x] = 0;
            }
        }
    }

    public static void moveLeft(int[][] map) {
        List<Integer> tmpList = new ArrayList<>();
        for(int y = 0; y < N; ++y) {
            tmpList.clear();

            for(int x = 0; x < N; ++x){
                if(map[y][x] == 0) continue;
                tmpList.add(map[y][x]);
            }

            int newIdx = 0;
            int size = tmpList.size();
            for(int idx = 0; idx < size; ++idx) {
                int val = tmpList.get(idx);
                int nextIdx = idx + 1;
                if(nextIdx < size) {
                    if(val == tmpList.get(nextIdx)) {
                        val += val;
                        ++idx;
                    }
                }
                map[y][newIdx] = val;
                newIdx++;
            }

            for(int idx = newIdx; idx < N; ++idx) {
                map[y][idx] = 0;
            }
        }
    }

    public static void moveRight(int[][] map) {
        List<Integer> tmpList = new ArrayList<>();
        for(int y = 0; y < N; ++y) {
            tmpList.clear();

            for(int x = N-1; x >= 0; --x){
                if(map[y][x] == 0) continue;
                tmpList.add(map[y][x]);
            }

            int newIdx = N-1;
            int size = tmpList.size();
            for(int idx = 0; idx < size; ++idx) {
                int val = tmpList.get(idx);
                int nextIdx = idx + 1;
                if(nextIdx < size) {
                    if(val == tmpList.get(nextIdx)) {
                        val += val;
                        ++idx;
                    }
                }
                map[y][newIdx] = val;
                newIdx--;
            }

            for(int idx = newIdx; idx >= 0; --idx) {
                map[y][idx] = 0;
            }
        }
    }

    public static int[][] initMap() {
        int[][] map = new int[N][];
        for(int y = 0; y < N; ++y){
            map[y] = originMap[y].clone();
        }

        return map;
    }

    public static void print() {
        System.out.println("=================");
        for(int y = 0; y < N; ++y) {
            System.out.println(Arrays.toString(originMap[y]));
        }
        System.out.println("=================");
    }
}
