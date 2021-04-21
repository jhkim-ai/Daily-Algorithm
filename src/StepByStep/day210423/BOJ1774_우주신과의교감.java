package StepByStep.day210423;

import java.util.*;
import java.io.*;

public class BOJ1774_우주신과의교감 {

    static int N, M;
    static List<Point> list;
    static List<Point> distance;
    static int[] root;
    static int[] rank;
    static double ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        distance = new ArrayList<>();

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine(), " ");
            list.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
        }
        for(int i = 0; i<M; ++i){
            st = new StringTokenizer(br.readLine(), " ");
            union(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // 모든 점에 대하여 거리를 구한다.
        for (int i = 0; i < list.size(); ++i) {
            for (int j = i+1; j < list.size(); ++j){
                Point p = list.get(i);
                Point q = list.get(j);
                Point info = new Point();
                info.num1 = p.num1;
                info.num2 = q.num1;
                info.getDistance(p, q);
                distance.add(info);
            }
        }

        // 0. kruskal 알고리즘을 위한 거리에 대한 오름차순 정렬
        Collections.sort(distance);
        // 1. 집합 초기화
        makeSet();
        // 2. 가장 작은 edge 부터 MST를 완성해 나간다.
        for(Point line : distance){
            if(N == M-1)
                break;
            if(!union(line.num1, line.num2)) break;
            ans += line.distance;
        }

        System.out.printf("%.2f",ans);
    }

    static void makeSet() {
        root = new int[list.size()+1];
        rank = new int[list.size()+1];
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    static int findSet(int a) {
        if (root[a] == a)
            return a;
        return root[a] = findSet(root[a]);
    }

    static boolean union(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a == b) return false;

        if(rank[a] < rank[b])
            root[a] = b;
        else {
            root[b] = a;
            if(rank[a] == rank[b])
                rank[a]++;
        }
        return true;
    }

    static double getDistance(Point p, Point q){
        int width = (p.x - q.x) * (p.x - q.x);
        int height = (p.y - q.y) * (p.y - q.y);

        return Math.sqrt(width + height);
    }
    static class Point implements Comparable<Point>{
        int y;
        int x;
        int num1;
        int num2;
        double distance;

        public Point(){

        }
        public Point(int y, int x, int num1) {
            this.y = y;
            this.x = x;
            this.num1 = num1;
        }
        void getDistance(Point p, Point q) {
            int width = (p.x - q.x) * (p.x - q.x);
            int height = (p.y - q.y) * (p.y - q.y);

            this.distance = Math.sqrt(width + height);
        }

        @Override
        public int compareTo(Point p){
            return Double.compare(this.distance, p.distance);
        }
    }
}
