import java.io.*;
import java.util.*;

public class sample {

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        Set<Integer> s = new HashSet<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] root = new int[N+1];
        int[] rank = new int[N+1];

        // 1. 초기화
        makeSet(root, N);

        // 2. union
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            union(a, b, root, rank);
        }

        // 3. 출력(종교의 개수)
        for (int i = 1; i < root.length; i++) {
            s.add(root[i]);
        }
        System.out.println(s.size());
    }

    // 1. 초기화
    static void makeSet(int[] root, int n){
        for (int i = 1; i < n+1; i++) {
            root[i] = i;
        }
    }

    // 2. union
    static int findSet(int a, int[] root){
        if(root[a] == a)
            return a;

        return root[a] = findSet(root[a], root);
    }

    // 3.
    static boolean union(int a, int b, int[] root, int[] rank){
        a = findSet(a, root);
        b = findSet(b, root);

        if(root[a] == root[b])
            return false;

        if(rank[a] < rank[b])
            root[a] = b;
        else{
            root[b] = a;
            if(rank[a] == rank[b]){
                rank[a]++;
            }
        }

        return true;
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
