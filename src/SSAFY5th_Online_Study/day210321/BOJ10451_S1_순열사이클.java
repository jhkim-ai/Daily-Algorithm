package SSAFY5th_Online_Study.day210321;

import java.io.*;
import java.util.*;

public class BOJ10451_S1_순열사이클 {

    static int T, N, ans;
    static int[] arr;
    static boolean[] visited;
    static int[] vArr;  // 디버깅 용

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        // Test Case 만큼 실행
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {

            // --------- 데이터 입력 --------- //
            ans = 0;
            N = Integer.parseInt(br.readLine());
            arr = new int[N+1];
            visited = new boolean[N+1];
            vArr = new int[N+1];
            st = new StringTokenizer(br.readLine(), " ");
            for (int idx = 1; idx < N+1 ; idx++) {
                arr[idx] = Integer.parseInt(st.nextToken());
            }

            // --------- 알고리즘 시작 --------- //
            
            for (int idx = 1; idx < N+1; idx++) {
                // 방문하지 않은 idx(node)를 dfs로 탐색
                if(!visited[idx]){
                    ans++;  // 방문하지 않았다면 새로운 사이클을 뜻함.
                    dfs(idx);

                    // 디버깅 용
                    // System.out.println(Arrays.toString(vArr));
                    // System.out.println("==============");
                }
            }

            // --------- 출력 --------- //
            sb.append(ans).append("\n");
        }
        
        System.out.println(sb);
    }
    
    // dfs 탐색
    static void dfs(int idx){
        vArr[idx] = ans;    // 디버깅 용
        
        visited[idx] = true; // 현재 idx(node)를 check
        int next = arr[idx]; // 다음 idx(node)를 받기

        if(!visited[next])   // 다음 idx(node)가 방문한 적이 없다면 사이클 탐색 시작
            dfs(next);
    }

    static String input = "2\n" +
            "8\n" +
            "3 2 7 8 1 4 5 6\n" +
            "10\n" +
            "2 1 3 4 5 6 7 9 10 8";
}
