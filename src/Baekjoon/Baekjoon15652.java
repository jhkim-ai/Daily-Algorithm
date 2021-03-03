package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 중복조합 (오름차순)
public class Baekjoon15652 {

    static int N, M;
    static int[] src;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        for (int i = 0; i < N; i++) {
            src[i] = i + 1;
        }

        combinationDescent(M, new int[M], 0);
        System.out.println(sb);
    }

    static void combinationDescent(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = startIdx; i< N; i++){
            selected[selected.length - cnt] = src[i];
            combinationDescent(cnt - 1, selected, i);
        }
    }
}
