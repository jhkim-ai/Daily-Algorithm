package Category.Tree;

import java.util.*;
import java.io.*;

public class BOJ9934_완전이진트리 {

    private static int K, idx;
    private static int[] arr;
    private static int[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        K = Integer.parseInt(br.readLine());
        arr = new int[(1<<K)];
        tree = new int[(1<<K)];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < arr.length; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 풀이1. 재귀함수 이용
        dfs(1);

        int depth = 1;
        for(int i = 1; i < tree.length; ++i){
            sb.append(tree[i]).append(" ");
            if(i == (1<<depth) - 1){
                depth++;
                sb.append("\n");
            }
        }

        System.out.println(sb);
    }

    public static void dfs(int num){

        if(num >= tree.length) return;

        dfs(num*2);
        tree[num] = arr[++idx];
        dfs(num*2 + 1);
    }

}
