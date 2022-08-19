package Category.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ1414_불우이웃돕기 {

    private static int N, ans;
    private static int[] root, rank;
    private static PriorityQueue<Node> pq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        pq = new PriorityQueue<>();
        ans = 0;
        makeSet();

        for(int y = 1; y <= N; ++y) {
            char[] arr = br.readLine().toCharArray();
            for(int x = 0; x < N; ++x) {
                int len = 0;
                char c = arr[x];

                if(c == '0') continue;

                if(c < 97) { // 'A'
                    len = c - 'A' + 27;
                } else { // 'a'
                    len = c - 'a' + 1;
                }
                pq.offer(new Node(y, x+1, len));
                ans += len;
            }
        }

        while(!pq.isEmpty()) {
            Node now = pq.poll();
            if(union(now.start, now.end)){
               ans -= now.cost;
            }
        }

        int representation = find(1);
        for(int idx = 2; idx <= N; ++idx) {
            int compare = find(idx);
            if(representation != compare) {
                ans = -1;
                break;
            }
        }

        System.out.println(ans);
    }

    public static void makeSet(){
        root = new int[N + 1];
        rank = new int[N + 1];

        for(int i = 0; i <= N; ++i){
            root[i] = i;
        }
    }

    public static int find(int a){
        if(root[a] == a) return a;
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

    public static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;

        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost);
        }
    }
}
