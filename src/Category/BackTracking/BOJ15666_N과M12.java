package Category.BackTracking;

import sun.lwawt.macosx.CSystemTray;

import java.util.*;
import java.io.*;

public class BOJ15666_N과M12 {

    public static int N, M;
    public static int[] arr;
    public static List<int[]> list;
    public static Set<String> s;
    public static StringBuilder ans;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        ans = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        list = new ArrayList<>();
        s = new HashSet<>();

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        combination(M, new int[M], 0);
        System.out.print(ans);
    }

    public static void combination(int cnt, int[] selected, int startIdx){
        if(cnt == 0) {
            StringBuffer sb = new StringBuffer();
            for(int i = 0; i < selected.length; ++i){
                sb.append(selected[i]).append(" ");
            }
            String tmp = sb.toString();
            if(!s.contains(tmp)) {
                s.add(tmp);
                ans.append(tmp).append("\n");
            }
            return;
        }

        for(int i = startIdx; i < N; ++i){
            selected[selected.length - cnt] = arr[i];
            combination(cnt-1, selected, i);
        }
    }
}
