package StepByStep.day210305;

import java.io.*;
import java.util.*;

public class BOJ1449_S3_수리공항승 {

    static int N, L;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // -------- 알고리즘 시작 -------- //
        Arrays.sort(arr);

        //  ex) 5, 2    테이프의 길이가 1일 때도 생각하자.
        //  1 2 5 6 7

        int start = arr[0]; // 정렬 후, 가장 앞에 있는 값부터 시작
        int cnt = 1;        // 맨 앞에 있는 것부터 막아야하므로, 최소 테이프는 1개 시작
        for (int i = 0; i < N; i++) {
            // 위의 TestCase 에서 첫 번재 테이프는 (1과 2)를 막을 수 있다.
            // 좌우 0.5씩 포함해야 하므로 (0.5 ~ 2.5) 까지 길이가 2인 테이프를 1개 사용
            // 즉, (시작점 <=  x < 시작점 + 테이프 길이) 범위에 있는 값이
            // 하나의 테이프로 묶인 것.
            if (arr[i] < start + L)
                continue;
            start = arr[i];
            cnt++;
        }

        System.out.println(cnt);
    }

    static String input = "6 1\n" +
            "1 2 5 6 7 8";
}
