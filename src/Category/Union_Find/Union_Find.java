package Category.Union_Find;

import java.util.*;
import java.io.*;

public class Union_Find {

    public static int V, E;
    public static int[] root, rank;

    public static void main(String[] args) throws Exception {

        // 1. Make-Set : 집합 초기화
        root = new int[V+1];
        rank = new int[V+1];
        for (int i = 1; i <= V; i++) {
            root[i] = i;
        }

    }

    // 2. Find-Set : 대푯값 찾기
    static int findSet(int a){
        if(root[a] == a)
            return a;
        return root[a] = findSet(a);
    }

    // 3. Union : 집합 합치기
    static boolean union(int a, int b){
        a = findSet(a); // a 집합의 대푯값 찾기
        b = findSet(b); // b 집합의 대푯값 찾기

        if(a == b) return false; // a와 b가 같은 집합

        // 집합의 depth 크기가 작은 쪽이 큰 쪽으로 붙는다. (편향 트리 방지)
        if(rank[a] < rank[b]) root[a] = b;
        else{
            root[b] = a;
            if(rank[a] == rank[b]) // 둘의 rank (depth)가 같다면, depth + 1 을 한다
                rank[a]++;
        }

        return true;
    }
}
