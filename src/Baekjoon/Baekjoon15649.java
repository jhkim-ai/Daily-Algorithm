package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 순열
public class Baekjoon15649 {

    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int [] src;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        src = new int[N];
        for (int i = 0; i<N; i++){
            src[i] = i+1;
        }

        permutation(M, new int[M], new boolean[N]);
        System.out.println(sb);
    }

    static void permutation(int cnt, int [] selected, boolean[] visited){
        if (cnt == 0){
            for (int i = 0; i<selected.length; i++)
                sb.append(selected[i]).append(" ");
            sb.append("\n");
            return;
        }

        for (int i = 0; i< src.length; i++){
            if(!visited[i]){
                visited[i] = true;
                selected[selected.length - cnt] = src[i];
                permutation(cnt-1, selected, visited);
                visited[i] = false;
            }
        }

    }
}
