package StepByStep._2022._22_02_12;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ7795_먹을_것인가_먹힐_것인가 {

    private static int N, M;
    private static int[] A, B;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            int ans = 0;
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            A = new int[N];
            B = new int[M];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; ++i){
                A[i] = Integer.parseInt(st.nextToken());

            }

            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < M; ++i){
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B); // 정렬
            for(int k : A){
               ans += getCntPair(k);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    // Lower Bound
    public static int getCntPair(int k){
        int cnt = 0;
        int start = 0;
        int end = M-1;
        int mid;

        while (start < end) {
            mid = (start + end) / 2;
            if (B[mid] < k) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if(k <= B[end]){
            cnt += end;
        } else {
            cnt += end + 1;
        }

        return cnt;
    }
}
