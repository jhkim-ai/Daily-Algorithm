package SamsungSW_A;

import java.util.*;
import java.io.*;

public class BOJ13458_시험감독 {

    public static int N;    // 시험장의 개수
    public static int[] A;  // 응시자 수
    public static int B, C; // 총감독관 감시할 응시자 수, 부감독관이 감시할 응시자 수
    public static long[] mem = new long[10000001];
    public static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        ans = 0;

        // 각 고사장의 응시자 수 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 총감독관 부감독관이 감시할 응시자 수
        st = new StringTokenizer(br.readLine());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; ++i){
            int num = A[i]; // 응시자 수
            int observer = 1; // 총 감독관은 한 명

            // 기존에 구했다면 pass
            if(mem[num] != 0){
                ans += mem[num];
                continue;
            }

            // 부 감독관 수 구하기
            num -= B;
            if(num > 0) {
                observer += (num / C);
                num %= C;
                if (num != 0) observer++;

                // mem 과 ans 에 저장
                mem[A[i]] = observer;
            }
            ans += observer;
        }
        System.out.println(ans);
    }
}
