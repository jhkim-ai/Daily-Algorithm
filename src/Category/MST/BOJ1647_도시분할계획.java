package Category.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ1647_도시분할계획 {

    private static int N, M;
    private static PriorityQueue<Edge> pq;
    private static int[] root, rank;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pq = new PriorityQueue<>();

        while(M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(start, end, cost));
        }

        makeSet();

        int cnt = 0;
        int answer = 0;
        while(!pq.isEmpty()) {
            Edge now = pq.poll();

            if(union(now.from, now.to)){
                answer += now.cost;
                cnt++;

                if(cnt == N - 2) break;
            }
        }

        System.out.println(answer);
    }

    public static void makeSet() {
        root = new int[N + 1];
        rank = new int[N + 1];

        for(int idx = 0; idx <= N; ++idx) {
            root[idx] = idx;
        }
    }

    public static int find(int a) {
        if(root[a] == a) return a;
        return root[a] = find(root[a]);
    }

    public static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if(a == b) return false;

        if(rank[a] < rank[b]){
            root[a] = b;
        } else {
            root[b] = a;
            if(rank[a] == rank[b]) {
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
        public int compareTo(Edge e) {
            return Integer.compare(this.cost, e.cost);
        }
    }
}
