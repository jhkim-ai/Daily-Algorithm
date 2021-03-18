package Jungol;

import java.util.*;
import java.io.*;

// Union-find (트리라 생각하자, 루트에 가까운 쪽으로 이동하자)
public class JO1863_종교 {

    static int N, M;
    static int[] parents;
    static Set<Integer> ans;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());   // 학생 수
        M = Integer.parseInt(st.nextToken());   // 쌍의 수
        parents = new int[N + 1];
        ans = new HashSet<>();

        // 1. Make-Set
        make();

        // 2. union
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int studentA = Integer.parseInt(st.nextToken());
            int studentB = Integer.parseInt(st.nextToken());

            union(studentB, studentA);
        }

        for (int i = 1; i <= N ; i++) {
            ans.add(findSet(i));
        }

        System.out.println(ans.size());
    }

    // 1. Make-Set
    static void make(){
        for (int i = 1; i < N+1; i++) {
            parents[i] = i;
        }
    }

    // 2. Find-Set
    static int findSet(int a){
        if(parents[a] == a) return a;

        return parents[a] = findSet(parents[a]);    // Path Compression
    }

    // 3. Union
    static boolean union(int a, int b){
        int aRoot = findSet(a);
        int bRoot = findSet(b);

        // 같은 집합일 시, union(x)
        if(aRoot == bRoot) return false;

        // 다른 집합일 시, union(o)

        // 주의! 아래와 같은 상황은 불균형을 유발한다
        // parents[bRoot] = aRoot;
        // 한쪽으로 치우치는 트리를 만들 수 있다.

        // <결론> 작은 수든, 큰 수든 규칙을 정해야 한다.
        // 왜냐하면, 이 조건이 없을 시, 오른쪽에서 왼쪽으로만 집합을 하겠다는 의미다.
        if(aRoot < bRoot){
            parents[bRoot] = aRoot;
        }else{
            parents[aRoot] = bRoot;
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
