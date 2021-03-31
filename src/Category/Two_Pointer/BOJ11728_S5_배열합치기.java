package Category.Two_Pointer;

import java.util.*;
import java.io.*;

public class BOJ11728_S5_배열합치기 {

    static int N, M;
    static int[] arr, arr2, ans;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br;

    public static void main(String[] args) throws Exception {

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine(), " ");

        // ------- 입력 ------- //

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        arr2 = new int[M];
        ans = new int[N + M];

        // ------- 알고리즘 시작 ------- //
        // Idea. 투 포인터
        // 주의! 카운팅 정렬은 안된다. why? 배열의 길이가 10억이기 때문

        // solve1();

        // --------- solve2 시작
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = N; i < N + M; i++) {
            ans[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(ans);
        // --------- solve2 끝

        // ----- 출력 ----- //
        for (int n : ans) {
            sb.append(n).append(" ");
        }
        System.out.println(sb);
    }

    // 범위 확인
    static boolean isIn(int a, int b) {
        return 0 <= a && a < N && 0 <= b && b < M;
    }

    static void solve1() throws Exception {

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int arrIdx = 0; // arr 배열의 Pointer
        int arr2Idx = 0; // arr2 배열의 Pointer

        // ans 배열을 넣기 위한 N+M 까지 반복
        for (int i = 0; i < N + M; i++) {
            // N과 M의 범위안에 있을 때
            if (isIn(arrIdx, arr2Idx)) {
                // arr가 더 크다면, arr2의 원소를 ans에 넣는다.
                if (arr[arrIdx] > arr2[arr2Idx]) {
                    ans[i] = arr2[arr2Idx++];
                }
                // arr2가 더 크다면, arr의 원소를 ans에 넣는다.
                else if (arr[arrIdx] < arr2[arr2Idx]) {
                    ans[i] = arr[arrIdx++];
                }
                // 값이 같을 때
                else {
                    ans[i++] = arr[arrIdx++];
                    ans[i] = arr2[arr2Idx++];
                }
            }
            // arrIdx가 N의 범위를 넘을 때
            else if (arrIdx >= N) {
                ans[i] = arr2[arr2Idx++];
            }
            // arr2Idx가 M의 범위를 넘을 때
            else if (arr2Idx >= M) {
                ans[i] = arr[arrIdx++];
            }
        }
    }
}
