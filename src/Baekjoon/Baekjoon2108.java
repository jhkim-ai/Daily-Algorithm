package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Baekjoon2108 {

    static int N;
    static int[] cnt, arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        cnt = new int[8001];
        arr = new int[N];
        int sum = 0;
        int mid = 0;
        int avg = 0;
        int range = 0;

        // 데이터 입력
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            sum += num;
            cnt[num + 4000]++;
            arr[i] = num;

            // 여기서 데이터 값을 찾는다면?
        }

        // 산술평균, 중앙값, 범위
        Arrays.sort(arr);
        mid = arr[N / 2];
        avg = (int) Math.round(((double) sum / N));
        range = arr[N - 1] - arr[0];

        // 최빈값
        int max = 0;          // 카운트된 수
        int idx = 0;          // 최빈 값
        boolean dupl = false; // 최빈값 중복

        for (int i = 0; i < cnt.length; i++) {

            if (cnt[i] != 0) {
                if (max == cnt[i])
                    dupl = true;
                else if (max < cnt[i]) {
                    max = cnt[i];
                    idx = i;
                    dupl = false;
                }
            }
        }
        if (dupl) {
            for (int i = idx, j = 0; i < cnt.length; i++) {
                if (cnt[i] == max) {
                    j++;
                }
                if (j == 2) {
                    idx = i;
                    break;
                }

            }
        }

        sb.append(avg).append("\n").append(mid).append("\n").append(idx - 4000).append("\n").append(range).append("\n");
        System.out.println(sb);

    }

}
