package StepByStep.day210406;

import java.util.*;
import java.io.*;

public class BOJ16432_떡갈비와호랑 {

    static int[][] arr;
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][];

        for (int t = 0; t < N; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            arr[t] = new int[Integer.parseInt(st.nextToken())];
            for (int i = 0; i < arr[t].length; i++) {
                arr[t][i] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(N, 0, new HashMap<Integer, Integer>());
        System.out.println(-1);
    }

    static void dfs(int n, int day, HashMap<Integer, Integer> hm){
        if(n == 0) {
            for (int i = 0; i < N; i++) {
                sb.append(hm.get(i)).append("\n");
            }
            System.out.println(sb);
            System.exit(0);
            return;
        }

        for(int riceCake=0; riceCake < arr[day].length; riceCake++){
            int nextRiceCake = arr[day][riceCake];   // 다음 떡
            if(!hm.containsValue(nextRiceCake)) {    // 이전에 떡을 먹은 적이 있나?
                hm.put(N - n, arr[day][riceCake]);   // 없으면 HashMap 에 넣는다.
                dfs(n - 1, day + 1, hm);
            }
        }
    }
}
