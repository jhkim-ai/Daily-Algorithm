package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ15665_N과M11 {

    private static int N, M;
    private static int[] arr;
    private static Set<String> s;
    private static StringBuilder ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        s = new HashSet<>();
        ans = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        duplicatedPermutation(M, new int[M]);
        System.out.print(ans);
    }

    public static void duplicatedPermutation(int cnt, int[] selected){
        if(cnt == 0){
            StringBuilder sb = new StringBuilder();
            for(int num : selected){
                sb.append(num).append(" ");
            }
            String str = sb.toString();
            if(s.contains(str)) return;
            s.add(str);
            ans.append(str).append("\n");
            return;
        }

        for(int i = 0; i < N; ++i){
            selected[selected.length - cnt] = arr[i];
            duplicatedPermutation(cnt-1, selected);
        }
    }
}
