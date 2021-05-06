package StepByStep.day210501;

import java.util.*;
import java.io.*;

public class BOJ1368_물대기 {

    static int N;
    static int[] root, rank;
    static Point[] well;
    static int[][] edge;

    static BufferedReader br;

    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        // 1. 집합 초기화
        initSet();
        // 2. 우물 초기화
        initWell();
        // 3. 우물 간 Edge 초기화
        initEdge();

        Arrays.sort(well);

        int sum = well[0].cost;
        for(int i = 1; i < N; ++i){
            int minCost = well[i].cost;
            for(int j = 0; j < N; ++j){

            }
        }
    }

    // 1. 집합 초기화
    public static void initSet(){
        root = new int[N];
        rank = new int[N];

        for(int i = 0; i < N; ++i){
            root[i] = i;
        }
    }

    // 2. 우물 초기화
    public static void initWell() throws IOException{
        well = new Point[N];
        for(int i = 0; i < N; ++i){
            well[i] = new Point(i, Integer.parseInt(br.readLine()));
        }
    }

    // 3. 우물 간 Edge 초기화
    public static void initEdge() throws IOException{
        edge = new int[N][N];
        StringTokenizer st = null;

        for(int i = 0 ; i < N; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j < N; ++j){
                edge[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    public static class Point{
        int num;
        int cost;

        public Point(int num, int cost){
            this.num = num;
            this.cost = cost;
        }
    }
}
