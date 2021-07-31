package Category.MST;

import java.util.*;
import java.io.*;

public class BOJ16202_MST게임 {

    private static int N, M, K;
    private static int[] root, rank;
    private static Edge[] edges;
    private static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean flag = false;

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        edges = new Edge[M];
        list = new ArrayList<>();
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int weight = i+1;
            edges[i] = new Edge(i, left, right, weight);
        }

        Arrays.sort(edges);
        while(K-->0){
            if(flag) {
              sb.append("0").append(" ");
              continue;
            };
            int count = 1;
            int sum = 0;
            makeSet();
            for(Edge edge : edges){
                if(edge.remove) continue;
                if(!union(edge.left, edge.right)) continue;
                ++count;
                list.add(edge.no);
                sum += edge.w;
            }
            if(count != N) {
                flag = true;
                sb.append(0).append(" ");
            }else{
                sb.append(sum).append(" ");
                removeEdge();
            }
        }
        System.out.println(sb);
    }
    public static void removeEdge(){
        int idx = list.get(0);
        edges[idx].remove = true;
        list.clear();
    }
    public static void makeSet(){
        rank = new int[N+1];
        root = new int[N+1];
        for(int i = 1; i <= N; ++i){
            root[i] = i;
        }
    }

    public static int findSet(int a){
        if(root[a] == a) return a;
        return root[a] = findSet(root[a]);
    }

    public static boolean union(int a, int b){
        a = findSet(a);
        b = findSet(b);

        if(a == b) return false;

        if(rank[a] < rank[b]) root[a] = b;
        else {
            root[b] = a;
            if(rank[a] == rank[b])
                rank[a]++;
        }
        return true;
    }

    static class Edge implements Comparable<Edge>{
        int no;
        int left;
        int right;
        int w;
        boolean remove = false;

        public Edge(int no, int left, int right, int w){
            this.no = no;
            this.left = left;
            this.right = right;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.w, e.w);
        }
    }
}
