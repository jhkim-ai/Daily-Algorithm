package Category.Tree;

import java.util.*;
import java.io.*;

public class BOJ14675_단절점과단절선 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1];
        int size = N-1;

        while(size-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            arr[child]++;
            arr[parent]++;
        }

        size = Integer.parseInt(br.readLine());
        while(size-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            if(t==1){
                if(arr[k] == 1) sb.append("no\n");
                else sb.append("yes\n");
            }else
                sb.append("yes\n");
        }

        System.out.println(sb);
    }
}
