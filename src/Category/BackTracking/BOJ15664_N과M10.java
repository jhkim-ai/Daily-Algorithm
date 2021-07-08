package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ15664_N과M10 {

    private static int N, M;
    private static int[] arr;
    private static Set<String> s;
    private static StringBuilder ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        ans = new StringBuilder();
        s = new HashSet<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        combination(M, new int[M], 0);
        System.out.print(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            StringBuilder sb = new StringBuilder();
            for(int num : selected){
                sb.append(num).append(" ");
            }
            String str = sb.toString();
            if(!s.contains(str)){
                s.add(str);
                ans.append(str).append("\n");
            }
            return;
        }
        for (int i = startIdx; i < N; ++i){
            selected[selected.length - cnt] = arr[i];
            combination(cnt-1, selected, i+1);
        }
    }
}
