package Formula.Combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 조합
public class BOJ15655_S3_N과M_6 {

    static int N, M;
    static int[] src;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        src = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            src[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(src);

        combination(M, new int[M], 0);
        System.out.println(sb);
    }

    static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            for (int i = 0; i < M; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for (int i = startIdx; i < N; i++) {
            selected[selected.length-cnt] = src[i];
            combination(cnt - 1, selected, i + 1);
        }
    }
}
