package StepByStep.day210423;

import java.util.*;
import java.io.*;

public class BOJ18222_투에모스문자열 {

    static final int[] arr = {0, 0, 1}; // 계산 편의를 위해 0번째 원소에 '0'을 넣음

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long K = Long.parseLong(br.readLine());

        // Idea. 거듭제곱으로 생성되는 것을 역으로 logn을 이용하여 역추적. 즉, f(x) = 1 - f(x-n번째 구역)
        //       문제 조건의 2~3 과정을 진행하면 n번째는 2^(n-1)개의 문자열이 생성.
        //       10^18을 약 60번만에 찾는다. O(logN)

        // 1. 문제와 달리 (0, 1)을 첫 시작(1구역)으로 본다.
        //    [ 2^(n-1) < K <= 2^n : K는 n 번째 구역]
        //    ex. 7 은 3구역 ( 4 < 7 <= 8 )

        // 2. k 번째 구역은 k-1 번째 구역을 두 번(원본+반대) 쓴 것.
        //    예를 들어, 8글자를 볼 때 [원본:(1, 2, 3, 4), 반대:(5, 6, 7, 8)]로 볼 수 있다.
        //    즉, 7은 3과 반대되는 글자이고, 3 = 7 - 2^(3-1) 로 볼 수 있다.

        // 3. 그리하여 7(3구역)->3(2구역)->1(1구역) 방법으로 1구역의 (1 or 2)가 닿을 때까지 dfs로 내려가고,
        //    내려간 횟수를 토대로, 짝수면 (1 or 2)번째 값을 그대로, 홀수면 반대로 출력한다.

        int area = getArea(K);  // K 의 구역 얻기

        System.out.println(dfs(K, area, 0));
    }

    // (1 or 2) 번째 글자까지 역추적
    static int dfs(long num, int area, int cnt) {
        // 1구역 도착 시, 종료
        if (area == 1) {
            int elem = (int)num;
            // cnt 가 짝수면 그대로, 홀수면 반대로.
            return cnt % 2 == 0 ? arr[elem] : arr[(elem + 1) % 3];
        }

        // 다음 수
        long nextNum = num - (long) Math.pow(2, area - 1);
        // 다움 영역
        int nextArea = getArea(nextNum);

        return dfs(nextNum, nextArea, cnt + 1);
    }

    static int getArea(long num) {
        int area = 1;
        while (Math.pow(2, area) < num) {
            ++area;
        }
        return area;
    }
}
