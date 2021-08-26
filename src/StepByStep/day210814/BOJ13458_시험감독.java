package StepByStep.day210814;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13458_시험감독 {

    private static int N, B, C;
    private static long supervisor;
    private static int[] testRoom;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        testRoom = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            testRoom[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int num : testRoom){
            num -= B;
            supervisor++;
            if(num <= 0) continue;
            supervisor += (long)num / C;
            if(num % C > 0) supervisor++;
        }

        System.out.println(supervisor);
    }
}
