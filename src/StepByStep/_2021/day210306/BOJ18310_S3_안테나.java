package StepByStep._2021.day210306;

import java.io.*;
import java.util.*;

public class BOJ18310_S3_안테나 {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception{
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // --------- 알고리즘 시작 --------- //
        // (1) 정렬 후, 중앙 값을 찾는다.
        // (2) 짝수 일 경우 중앙 값에서 작은 수

        Arrays.sort(arr);
        System.out.println(arr[(N-1)/2]);
    }

    static String input = "4\n" +
            "5 1 7 9";
}
