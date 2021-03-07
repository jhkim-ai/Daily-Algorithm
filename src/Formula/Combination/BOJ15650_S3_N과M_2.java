package Formula.Combination;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 조합
public class BOJ15650_S3_N과M_2 {

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

        combination(M, new int[M], 0);
        System.out.println(sb);

    }

    static void combination(int cnt, int[] selected, int startIdx) {
        if (cnt == 0) {
            for (int i = 0; i < selected.length; i++) {
                sb.append(selected[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = startIdx; i < src.length; i++) {
            selected[selected.length - cnt] = src[i];
            combination(cnt - 1, selected, i + 1);
        }

    }

}
