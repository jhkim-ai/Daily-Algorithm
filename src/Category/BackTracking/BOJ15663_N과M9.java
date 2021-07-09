package Category.BackTracking;

import java.util.*;
import java.io.*;

public class BOJ15663_N과M9 {

    private static int N, M;
    private static int[] arr;
    private static Set<String> s;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder ans = new StringBuilder();

        s = new HashSet<>();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
//        Arrays.sort(arr);

        // =============================
        permutation(M, new int[M], 0);
        List<String> list = new ArrayList<>();
        // Set -> List 로 옮김
        for(String str : s){
            list.add(str);
        }
        // List 정렬 (정렬에 예외가 있다? 문자열 정렬 기준 : 길이를 먼저 한다.
        // 문자 정렬이 아니라면 쓰지말자. 예외 발생률이 높다
        Collections.sort(list);
        // 정답 출력
        for(String str : list){
            ans.append(str).append("\n");
        }
        System.out.print(ans);
    }

    public static void permutation(int cnt, int[] selected, int flag){
        if(cnt == 0){
            StringBuilder sb = new StringBuilder();
            for(int num : selected){
                sb.append(num).append(" ");
            }
            s.add(sb.toString());
            return;
        }

        for(int i = 0; i < N; ++i){
            if((flag & (1 << i)) > 0) continue;
            selected[selected.length - cnt] = arr[i];
            permutation(cnt-1, selected, flag | (1 << i));
        }
    }
}
