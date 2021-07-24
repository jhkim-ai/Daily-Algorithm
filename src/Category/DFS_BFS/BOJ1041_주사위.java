package Category.DFS_BFS;

import java.util.*;
import java.io.*;

public class BOJ1041_주사위 {

    private static long N;
    private static int[] dice;
    private static long sum1, sum2, sum3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[6];
        long ans = 0;

        // 주사위 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; ++i) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        if (N == 1) {
            Arrays.sort(dice);
            for(int i = 0; i < 5; ++i){
                ans += dice[i];
            }
        } else {
            sum1 = minSubPlane(1, new int[1], 0);
            sum2 = minSubPlane(2, new int[2], 0);
            sum3 = minSubPlane(3, new int[3], 0);

//        System.out.println(sum1 + " " + sum2 + " " + sum3);
            ans = minSumPlane();
        }
        System.out.println(ans);
    }

    // 면의 최소합 구하기
    public static long minSumPlane() {
        long sum = 0;

        // 1층 ~ N-1층 까지의 합
        sum += sum2 * 4;
        sum += (N - 2) * 4 * sum1;
        sum *= (N - 1);

        // N 층
        sum += sum3 * 4;
        sum += (N - 2) * 4d * sum2;
        sum += (N - 2) * (N - 2d) * sum1;

        // 주의!. N 을 parseInt 로 받으면 Integer 간의 곱으로 된 후 casting 되기 때문에 조심해야한다.
        // N 을 parseLong 으로 받던지, 계산할 때 리터럴 숫자 뒤에 d를 붙이던지 하자
        return sum;
    }

    // combination
    public static long minSubPlane(int cnt, int[] selected, int startIdx) {
        long res = Long.MAX_VALUE;

        if (cnt == 0) {
            long sum = 0;
            for (int num : selected) {
                sum += dice[num];
            }
            return sum;
        }

        for (int i = startIdx; i < dice.length; ++i) {
            selected[selected.length - cnt] = i;
            if (!isPossible(selected)) {
                selected[selected.length - cnt] = 0;
                continue;
            }
            res = Math.min(minSubPlane(cnt - 1, selected, i + 1), res);
        }
        return res;
    }

    // 선택된 위치 중, 마주보고 있는 면일 경우를 체크
    public static boolean isPossible(int[] selected) {
        boolean[] checked = new boolean[6];
        for (int num : selected) checked[num] = true;
        return !(checked[0] && checked[5]) && !(checked[1] && checked[4]) && !(checked[2] && checked[3]);
    }
}
