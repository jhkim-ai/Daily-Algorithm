package Category.Tree;

import java.util.*;
import java.io.*;

public class BOJ2263_트리의순회 {

    private static int N, index;
    private static int[] arr, arrInOrder, arrPostOrder;
    private static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[1<<N];
        arrInOrder = new int[N+1];
        arrPostOrder = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; ++i){
            arrInOrder[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; ++i){
            arrPostOrder[i] = Integer.parseInt(st.nextToken());
        }

        inOrder(1);
        preOrder(1);

        System.out.println(sb);
    }

    public static void inOrder(int idx){
        if(idx > N) return;

        inOrder(idx*2);
        arr[idx] = arrInOrder[++index];
        inOrder(idx*2 + 1);
    }

    public static void preOrder(int idx){
        if(idx > N) return;

        sb.append(arr[idx]).append(" ");
        preOrder(idx*2);
        preOrder(idx*2 + 1);
    }
}

