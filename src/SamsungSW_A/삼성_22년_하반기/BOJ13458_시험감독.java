package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ13458_시험감독 {

    private static int N;
    private static int B, C;
    private static int[] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        N = Integer.parseInt(br.readLine()); // 시험장 수
        A = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            A[i] = Integer.parseInt(st.nextToken()); // i번째 시험장에 있는 응시자 수
        }

        st = new StringTokenizer(br.readLine(), " ");
        B = Integer.parseInt(st.nextToken()); // 총감독관 한 명당 감시 가능한 응시생 수
        C = Integer.parseInt(st.nextToken()); // 부감독관 한 명당 감시 가능한 응시생 수

        long ans = 0;
        for(int i = 0; i < N; ++i) {
            A[i] -= B;
            ans++;

            if(A[i] <= 0) continue;

            ans += A[i] / C;
            if(A[i] % C != 0) ans++;
        }
        System.out.println(ans);
    }
}
