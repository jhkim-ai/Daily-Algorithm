package Formula.Permutation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 순열
public class BOJ15654_S3_N과M_5 {

    static int N, M;
    static int[] src;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        src = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0 ; i< N; i++){
            src[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);

        permutation(M, new int[M], new boolean[N]);
        System.out.println(sb);
    }

    static void permutation(int cnt, int[] selected, boolean[] visited){
        if (cnt == 0){
            for(int i = 0; i<M; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = 0 ; i<N; i++){
            if(!visited[i]){
                visited[i] = true;
                selected[selected.length-cnt] = src[i];
                permutation(cnt-1,selected, visited);
                visited[i] = false;
            }
        }
    }
}
