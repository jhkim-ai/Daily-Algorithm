package Formula.Permutation;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

// 중복순열
public class BOJ15651_S3_N과M_3 {

    static int N, M;
    static int [] src;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];
        for (int i = 0; i<N; i++){
            src[i] = i+1;
        }

        permutationDup(M, new int [M]);
        System.out.println(sb);
    }

    static void permutationDup(int cnt, int[] selected){
        if (cnt == 0){
            for(int i = 0; i<M; i++){
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = 0; i<N; i++){
            selected[selected.length - cnt] = src[i];
            permutationDup(cnt - 1, selected);
        }
    }
}
