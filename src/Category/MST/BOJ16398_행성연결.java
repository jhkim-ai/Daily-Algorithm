package Category.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ16398_행성연결 {

    private static int N;
    private static int[] root, rank;
    private static PriorityQueue<Edge> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();

        for(int y = 0; y < N; ++y){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int x = 0; x < N; ++x){
                int cost = Integer.parseInt(st.nextToken());
                if(x < y) continue;
                pq.offer(new Edge(y, x, cost));
            }
        }

        makeSet();

        int cnt = 0;
        long ans = 0;
        while(!pq.isEmpty()){
            Edge now = pq.poll();

            if(union(now.from, now.to)){
                ans += now.cost;
                cnt++;
                if(cnt == N - 1) break;
            }
        }

        System.out.println(ans);
    }

    public static void makeSet(){
        rank = new int[N];
        root = new int[N];
        for(int i = 0; i < N; ++i){
            root[i] = i;
        }
    }

    public static int find(int a){
        if(a == root[a]) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(rank[a] < rank[b]) {
            root[a] = b;
        } else {
            root[b] = a;
            if(rank[a] == rank[b]){
                rank[a]++;
            }
        }

        return true;
    }

    public static class Edge implements Comparable<Edge> {
        int from;
        int to;
        int cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e){
            return Integer.compare(this.cost, e.cost);
        }
    }
}
