package Category.SlidingWindow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12847_꿀아르바이트 {

    private static int N, M;
    private static int pay[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        pay = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            pay[i] = Integer.parseInt(st.nextToken());
        }

        long ans = Long.MIN_VALUE;
        int startIdx = 0;
        long sum = 0l;
        for(int i = 0; i < N; ++i){
            sum += pay[i] + 0l;
            if(i >= M-1){
                ans = Math.max(ans, sum);
                sum -= pay[startIdx++];
            }
        }

        System.out.println(ans);
    }
}
