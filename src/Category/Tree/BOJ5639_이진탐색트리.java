package Category.Tree;

import java.util.*;
import java.io.*;

public class BOJ5639_이진탐색트리 {

    private static int[] tree;

    public static void main(String[] args) throws Exception{
        Scanner sc = new Scanner(System.in);
        tree = new int[10001];

        while(sc.hasNext()){
            int N = sc.nextInt();
            insert(N, 1);
            System.out.println(N);
        }
        System.out.println("out");
        postOrder(1);
    }

    public static void insert(int n, int idx){
        if(tree[idx] != 0 && tree[idx] < n) insert(n, idx*2);
        if(tree[idx] != 0 && tree[idx] > n) insert(n, idx*2 + 1);
        tree[idx] = n;
    }

    public static void postOrder(int n){
        if(tree[n] == 0) return;
        postOrder(n*2);
        postOrder(n*2 + 1);
        System.out.println(tree[n]);
    }
}
