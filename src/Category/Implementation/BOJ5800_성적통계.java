package Category.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ5800_성적통계 {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; ++t){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            arr = new int[N];
            for(int i = 0; i < N; ++i){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int gapVal = Integer.MIN_VALUE;
            Arrays.sort(arr);

            for(int i = N-1; i >= 1; --i){
                gapVal = Math.max(arr[i] - arr[i-1], gapVal);
            }

            sb.append("Class ").append(t).append("\n");
            sb.append("Max ").append(arr[N-1]).append(", Min ").append(arr[0]);
            sb.append(", Largest gap ").append(gapVal).append("\n");
        }

        System.out.println(sb);
    }


}
