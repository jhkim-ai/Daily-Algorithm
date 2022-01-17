package StepByStep.day210327;

import java.io.*;
import java.util.*;

public class BOJ1197_G4_최소스패닝트리 {
    public static void main(String[] args) throws Exception{

        // -------- 입력 -------- //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        Node[] graph = new Node[e];

        int[] rank = new int[v+1];
        int[] root = new int[v+1];
        int ansDist = 0;

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            graph[i] = new Node(Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()),
                    Integer.parseInt(st.nextToken()));

        }

        // -------- 알고리즘 시작 -------- //
        // Idea. MST 를 만들기 위한 Kruskal 알고리즘 사용
        // Kruskal => (1) 간선을 오름차순으로 정렬
        //            (2) Union-find 를 이용하여 Cycle 체크와 동시에 간선 선택
        //            (3) 모든 간선을 체크할 때까지 (1), (2) 반복
        
        Arrays.sort(graph); // 1. 정렬
        makeSet(root);      // 2. union-find 를 사용하기 위한, 집합 초기화

        // 3. 가장 낮은 간선의 가중치부터 모든 간선을 탐색
        for (int i = 0; i < graph.length; i++) {
            Node n = graph[i];

            // 3-1. Cycle 을 이루지 않는다면, union 진행
            if(union(root, rank, n.now, n.next)){
                ansDist += n.cost;
            }
        }
        
        // 4. 정답 출력
        System.out.println(ansDist);
    }

    // [Union-Find 공식]
    // Union-Find - 1. make
    static void makeSet(int[] root){
        for (int i = 1; i < root.length; i++) {
            root[i] = i;
        }
    }

    // Union-Find - 2. findSet
    static int findSet(int[] root, int a){
        if(root[a] == a)
            return a;
        return root[a] = findSet(root, root[a]); // path compression
    }

    // Union-Find - 3. union
    static boolean union(int[] root, int[] rank, int a, int b){
        a = findSet(root, root[a]);
        b = findSet(root, root[b]);

        if(a == b)
            return false;

        if(rank[a] < rank[b])
            root[a] = b;
        else{
            root[b] = a;
            if(rank[a] == rank[b])
                rank[a]++;
        }
        return true;
    }

    static class Node implements Comparable<Node>{
        int now;    // input 데이터의 첫 번째
        int next;   // input 데이터의 두 번째
        int cost;   // input 데이터의 세 번째(가중치)

        public Node(int now, int next, int cost) {
            this.now = now;
            this.next = next;
            this.cost = cost;
        }
        
        // 정렬
        @Override
        public int compareTo(Node n){
            return Integer.compare(this.cost, n.cost);
        }
    }
}
