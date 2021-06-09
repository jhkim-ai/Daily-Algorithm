package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1707_프로세서연결하기 {

    public static int T, N;
    public static int[][] map;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int y = 0; y < N; ++y){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int x = 0; x < N; ++x){
                   map[y][x] = Integer.parseInt(st.nextToken());
                }
            }

//            permutation();
        }

//        N = 6;
//        permutation(3, new int[3], new boolean[N]);
//        System.out.println(index);
//        System.out.println("==============");

//        subSet(3);
//        System.out.println("==============");

    }
    static int index;
    public static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            System.out.println(Arrays.toString(selected));
            index++;
            return;
        }

        for(int i = 0; i < N; ++i){
            if(visited[i]) continue;
            visited[i] = true;
            selected[selected.length - cnt] = i;
            permutation(cnt-1, selected, visited);
            visited[i] = false;
        }
    }
    static int[] arr = {1, 2, 3};
    static List<Integer> list = new ArrayList<>();
    public static void subSet(int n){
        for(int i = 1; i < (1<<n); ++i){
            list.clear();
            for(int j = 0; j < n; ++j){
                if((i & (1 << j)) > 0) list.add(arr[j]);
            }
            printFun();
        }
    }
    public static void printFun(){
        System.out.println(list);
    }
    public static void print(){
        System.out.println("=============");
        for(int y = 0; y < N; ++y){
            for(int x = 0; x< N; ++x){
                System.out.print(map[y][x] + " ");
            }
            System.out.println();
        }
    }

    public static String input = "3\n" +
            "7\n" +
            "0 0 1 0 0 0 0\n" +
            "0 0 1 0 0 0 0\n" +
            "0 0 0 0 0 1 0\n" +
            "0 0 0 0 0 0 0\n" +
            "1 1 0 1 0 0 0\n" +
            "0 1 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0\n" +
            "9\n" +
            "0 0 0 0 0 0 0 0 0\n" +
            "0 0 1 0 0 0 0 0 1\n" +
            "1 0 0 0 0 0 0 0 0\n" +
            "0 0 0 1 0 0 0 0 0\n" +
            "0 1 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 1 0 0\n" +
            "0 0 0 1 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 1 0\n" +
            "0 0 0 0 0 0 0 0 1\n" +
            "11\n" +
            "0 0 1 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 1\n" +
            "0 0 0 1 0 0 0 0 1 0 0\n" +
            "0 1 0 1 1 0 0 0 1 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 1 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 1 0 0\n" +
            "0 0 0 0 0 0 1 0 0 0 0\n" +
            "0 0 0 0 0 0 0 0 0 0 0";
}
