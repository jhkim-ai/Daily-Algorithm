package StepByStep.day210306;

import java.io.*;
import java.util.*;

public class BOJ1764_S4_듣보잡 {

    static int N, M;
    static String[] arr1, arr2;
    static List<String> list;

    public static void main(String[] args) throws Exception {

        // ------- 데이터 입력 ------- //

//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new String[N];
        arr2 = new String[M];
        list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            arr1[i] = br.readLine();
        }
        for (int i = 0; i < M; i++) {
            arr2[i] = br.readLine();
        }

        Arrays.sort(arr1);

        // ------- 알고리즘 시작 ------- //

        for (int i = 0; i < M; i++) {

            int start = 0;
            int end = N-1;
            int mid = 0;
            // 이분 탐색
            while (start <= end) {

                mid = (start + end) / 2;

                if (arr1[mid].equals(arr2[i])) {
                    list.add(arr2[i]);
                    break;
                }

                if (arr1[mid].compareTo(arr2[i]) > 0)
                    end = mid - 1;
                else
                    start = mid + 1;
            }
        }

        // ------- 출력 ------- //
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);
        sb.append(list.size());
        for (String s : list)
            sb.append("\n").append(s);
        System.out.println(sb);
    }

    static String input = "1 1\n" +
            "ee\n" +
            "ff\n";
}
