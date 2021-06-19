package Category.Union_Find;

import java.util.*;
import java.io.*;

public class BOJ11724_연결요소개수 {

    public static int N, M;
    public static int[] rank, root;
    public static Set<Integer> s;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        s = new HashSet<>();

        makeSet();
        for(int i = 0; i < M; ++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a,b);
        }

        for(int i = 1; i <= N; ++i){
            s.add(findSet(i));
        }
        System.out.println(s.size());
    }

    // 1. makeSet
    public static void makeSet() {
        rank = new int[N + 1];
        root = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            root[i] = i;
        }
    }

    // 2. findSet
    public static int findSet(int a){
        if(root[a] == a) return a;
        return root[a] = findSet(root[a]);
    }

    // 3. unionSet
    public static boolean union(int a, int b){
        a = findSet(a);
        b = findSet(b);

        if(a == b) return false; // 같은 집합

        // 합치기
        if(rank[a] < rank[b]) root[a] = b;
        else{
            root[b] = a;
            if(rank[a] == rank[b])
                rank[a]++;
        }

        return true;
    }
}
