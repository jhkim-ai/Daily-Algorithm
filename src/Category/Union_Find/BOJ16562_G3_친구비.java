package Category.Union_Find;

import java.io.*;
import java.util.*;

public class BOJ16562_G3_친구비 {

    static int N, M, K;
    static int[] root;
    static int[] rank;
    static int[] money;

    public static void main(String[] args) throws Exception {
        //        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 학생 수
        M = Integer.parseInt(st.nextToken());   // 친구 관계 수
        K = Integer.parseInt(st.nextToken());   // 현재 가진 돈

        root = new int[N + 1];
        rank = new int[N + 1];
        money = new int[N + 1];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i < N + 1; i++) {
            money[i] = Integer.parseInt(st.nextToken());
        }

        // 1. 집합 초기화
        make();

        // 2. union
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);
        }
        
        // 출력
        Set<Integer> s = new HashSet<>();
        for (int i = 1; i < N+1; i++) {
            s.add(findSet(i));
        }

        int sum = 0;
        for(int a : s){
            sum += money[root[a]];
        }
        if(sum <= K)
            System.out.println(sum);
        else
            System.out.println("Oh no");

    }

    static void make() {
        for (int i = 1; i < N + 1; i++) {
            root[i] = i;
        }
    }

    // 2. Find-Set (대표값 찾기)
    static int findSet(int a) {
        if (root[a] == a)
            return a;
        return root[a] = findSet(root[a]);  // Path Compression
    }

    // 3. Union (결합)
    static boolean union(int a, int b) {
        a = findSet(a);
        b = findSet(b);

        if (a == b)
            return false;

        if(money[a] > money[b]){
            root[a] = b;
        }else{
            root[b] = a;
        }
        return true;
    }

    static String input = "5 3 10\n" +
            "10 10 20 20 30\n" +
            "1 3\n" +
            "2 4\n" +
            "5 4";
}
