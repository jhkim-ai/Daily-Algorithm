package Category.MST;

import java.util.*;
import java.io.*;

public class BOJ1197_최소스패닝트리 {

    public static int V, E;
    public static Point[] edges;

    public static int[] root, rank;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        edges = new Point[E];

        // Edge 정보 입력
        for(int i = 0; i < E; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new Point(a, b, c);
        }

        // 1. Kruskal 알고리즘을 위한 오름차순 정렬
        Arrays.sort(edges);

        // 2. Union-find 를 위한 집합 초기화(makeSet)
        makeSet();

        // 3. Union 을 진행하며 MST 를 완성
        int sumWeight = 0;
        for(int i = 0; i < edges.length; ++i){
            int start = edges[i].start; // 시작점
            int end = edges[i].end;     // 도착점
            int weight = edges[i].w;    // 가중치
            if(union(start, end)) sumWeight += weight;
        }

        System.out.println(sumWeight);
    }

    // 집합 초기화
    public static void makeSet(){
        root = new int[V+1]; // 정점의 개수만큼 root, rank 를 초기화
        rank = new int[V+1];

        // 집합의 대푯값을 자기 자신으로 초기화
        for(int i = 0; i <= V; ++i){
            root[i] = i;
        }
        return;
    }

    // 집합의 대푯값 찾기
    public static int findSet(int a){
        if(root[a] == a)
            return a;
        return root[a] = findSet(root[a]); // path compression
    }

    // 집합 합치기
    public static boolean union(int a, int b){
        a = findSet(a);
        b = findSet(b);

        if(a == b) return false;  // 서로 같은 집합
        if (rank[a] < rank[b]) root[a] = b;
        else {
            root[b] = a;
            if(rank[a] == rank[b]) rank[a]++;
        }
        return true;
    }

    static class Point implements Comparable<Point>{
        int start;
        int end;
        int w;

        public Point(int start, int end, int w){
            this.start = start;
            this.end = end;
            this.w = w;
        }

        @Override
        public int compareTo(Point p){
            return Integer.compare(this.w, p.w);
        }
    }
}
