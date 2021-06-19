package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ9934_완전이진트리 {

    private static int N, idx;
    private static int[] tree, arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        tree = new int[(int)Math.pow(2, N)];
        arr = new int[(int)Math.pow(2, N)];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < tree.length; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        idx = 1;
        dfs(1);
        System.out.println(Arrays.toString(tree));

        int j = 2;
        for(int i = 1; i < j; ++i){
            sb.append(tree[i]).append(" ");
            if(j == i -1){
                i = j-1;
                j *= 2;
                sb.append("\n");
            }
            if(j > tree.length) break;
        }
        System.out.println(sb);
    }

    public static void dfs(int num){
        if(num >= tree.length){
            return;
        }

        dfs(num*2);
        setTree(num, idx);
        ++idx;

        setTree(num, idx);
        ++idx;

        dfs(num*2+1);
        setTree(num, idx);
        ++idx;
    }

    public static void setTree(int num, int idx){
        if(num >= tree.length || idx >= tree.length) return;
        tree[num] = arr[idx];
    }

}
