package StepByStep.day210430;

import java.util.*;
import java.io.*;

public class BOJ2687_성냥개비 {

    static final int[] arr = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};

    static int T, N;
    static int ansMin, ansMax;
    static List<Integer> list;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder ans = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; ++t) {
            N = Integer.parseInt(br.readLine());
            ansMin = Integer.MAX_VALUE;
            ansMax = Integer.MIN_VALUE;

            // 1. 부분집합을 구한다.
            powerSet();
            ans.append(ansMin).append(" ").append(ansMax).append("\n");
        }
        System.out.println(ans);
    }

    public static void powerSet() {
        list = new ArrayList<>();

        // bit masking 부분집합
        for (int i = 1; i < (1 << arr.length); ++i) { // 2^10(1024) 개의 부분집합 중 공집합을 제외
            int sum = 0;
            list.clear();
            for (int j = 0; j < arr.length; ++j) {
                if ((i & (1 << j)) > 0) {
                    list.add(arr[j]);
                }
            }

            // 가지치기
            for (int num : list) {
                sum += arr[num];
            }
            if (sum != N) continue;

            // 2. 선택된 부분 집합을 조합한다.
            sorting();
        }
    }

    static void sorting() {
        StringBuilder sb = new StringBuilder();
        Collections.sort(list);

        // 최솟값
        int inter = -1;
        if(list.get(0) == 0){
            for(int i = 0; i < list.size(); ++i){
                if(list.get(i) == 0) continue;
                inter = i;
                break;
            }
        }

        if(inter != -1)
            sb.append(list.get(inter));
        for(int i = 0; i < list.size(); ++i){
            if(i == inter) continue;
            sb.append(list.get(i));
        }
        ansMin = Math.min(Integer.parseInt(sb.toString()), ansMin);

        sb.setLength(0);    // 가장 빠른 초기화라 한다.
        // 최대값
        for (int i = list.size() - 1; i >= 0; --i) {
            sb.append(list.get(i));
        }
        ansMax = Math.max(Integer.parseInt(sb.toString()), ansMax);
    }
}
