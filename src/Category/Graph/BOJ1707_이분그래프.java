package Category.Graph;

import java.util.*;
import java.io.*;

public class BOJ1707_이분그래프 {

    private static int T, V, E;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        outer:while (--T > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            arr = new int[V+1];
            Arrays.fill(arr, -1);

            while (--E > 0) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 같은 집합일 경우
                if(arr[a] != -1 && arr[b] != -1 && arr[a] == arr[b]){
                    sb.append("NO").append("\n");
                    continue outer;
                }
//                if(arr[a] != -1)
            }
        }
    }
}
