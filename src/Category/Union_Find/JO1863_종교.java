package Category.Union_Find;

import java.io.*;
import java.util.*;

public class JO1863_종교 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 1. makeSet
        int[] root = new int[n+1];
        int[] rank = new int[n+1];
        for (int i = 1; i <= n ; i++) {
            root[i] = i;
        }

        while(m-- > 0){
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            // 3. union
            union(root, rank, a, b);
        }

        // 4. 출력
        Set<Integer> s = new HashSet<>();
        for(int i = 1; i<root.length; i++){
            s.add(findSet(root, i));
        }
        System.out.println(s.size());

    }

    // 2. find-set
    static int findSet(int[] root, int a){
        if(root[a] == a)
            return a;
        return root[a] = findSet(root, root[a]); // path Compression
    }

    // 3. union
    static void union(int[] root, int[] rank, int a, int b){
        a = findSet(root, a);
        b = findSet(root, b);

        if(a == b){
            return;
        }

        // rank 를 이용하여 한 쪽으로 치우쳐지는 경우 (편향트리)를 막는다.
        // rank 가 큰 (depth 가 깊은) 쪽으로 붙인다.
        if(rank[a] < rank[b]) {
            root[a] = b;
        }
        else {
            root[b] = a;
            if(rank[a] == rank[b])
                rank[a]++;
        }
    }

    static String input = "10 9\n" +
            "1 2\n" +
            "1 3\n" +
            "1 4\n" +
            "1 5\n" +
            "1 6\n" +
            "1 7\n" +
            "1 8\n" +
            "1 9\n" +
            "1 10";
}
