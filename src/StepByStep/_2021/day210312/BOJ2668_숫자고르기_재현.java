package StepByStep._2021.day210312;

import java.util.*;
import java.io.*;

public class BOJ2668_숫자고르기_재현 {

    static List<Integer> ans;

    public static void main(String[] args) throws Exception {

        // ----- 데이터 입력 ----- //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];
        for (int i = 1; i < N+1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        ans = new ArrayList<>();

        // ----- 알고리즘 ----- //
        for (int start = 1; start < N+1; start++) {
            dfs(start, start, arr, new boolean[N+1]);
        }

        // ----- 출력 ----- //
        Collections.sort(ans);
        sb.append(ans.size()).append("\n");
        for(int num : ans)
            sb.append(num).append("\n");
        System.out.println(sb);
    }

    static void dfs(int start, int idx, int[] arr, boolean[] visited){
        visited[idx] = true;       // 현 idx 방문 표시
        int nextIdx = arr[idx];    // 다음 idx

        // 이미 집합에 있다면, 종료
        // why? (1). cycle이 아닌 것이 접근했을 경우
        //      (2). 같은 cycle의 원소가 접했을 경우
        if(ans.contains(nextIdx))
            return;

        // cycle이 완성되었을 때 (기저조건)
        if(start == nextIdx){
            for (int i = 1; i < visited.length; i++) {
                if(visited[i])
                    ans.add(i);
            }
            return;
        }

        // 다음 idx 로 넘어간다.
        if(!visited[nextIdx])
            dfs(start, nextIdx, arr, visited);
    }
}
