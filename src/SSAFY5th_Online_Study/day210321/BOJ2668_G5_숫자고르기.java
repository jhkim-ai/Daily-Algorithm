package SSAFY5th_Online_Study.day210321;

import java.io.*;
import java.util.*;

public class BOJ2668_G5_숫자고르기 {

    static int N, count;
    static int[] arr, nArr;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringBuilder sb = new StringBuilder();

        // ---------- 데이터 입력 ---------- //
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        nArr = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 1; i < N + 1; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // ---------- 알고리즘 시작 ---------- //
        for (int idx = 1; idx < N + 1; idx++) {
            if (!visited[idx]) {
                dfs(idx, idx, new ArrayList<>(), new boolean[N + 1]);
            }
        }

        // ---------- 출력 ---------- //
        sb.append(count).append("\n");
        for (int i = 0; i < visited.length; i++) {
            if (visited[i])
                sb.append(i).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int start, int idx, ArrayList<Integer> list, boolean[] checked) {

        int next = arr[idx];
        checked[next] = true;
        list.add(next);

        if(!visited[next] && !checked[next]){
            System.out.println(start + " : " + next);
            dfs(start, next, list, checked);
        }

        if(start == next){
            count++;
            for (int i = 0; i < list.size(); i++) {
                int index = list.get(i);
                visited[index] = true;
            }
        }
    }

    static String input = "7\n" +
            "3\n" +
            "1\n" +
            "1\n" +
            "5\n" +
            "5\n" +
            "4\n" +
            "6";
}
