package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// 분해합
public class Baekjoon2231 {
    public static void main(String[] args) throws Exception {

        // ---------- 데이터 입력 ---------- //
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // ---------- 알고리즘 시작 ---------- //

        // 정답 초기화
        int ans = 0;

        for (int n = 1; n <= N; n++) {
            int sum = n;    // 현재 숫자
            int tmp = n;    // 임시 저장소 : 10으로 계속 나누어 자릿수의 합을 구하기 위함

            // [ tmp > 0 또는 tmp != 0 ] 일의 자리까지 구하기 위함
            while (tmp != 0) {
                sum += tmp % 10;
                tmp /= 10;
            }

            // 답이 나오면 break
            if (sum == N) {
                ans = n;
                break;
            }
        }

        System.out.println(ans);
    }
}
