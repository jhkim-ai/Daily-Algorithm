package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ3273_S4_두수의합 {

    static int N, x, cnt;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        x = Integer.parseInt(br.readLine());

        // twoPointer();    // 투 포인터 방식
        binarySearch();     // 이진 탐색

        System.out.println(cnt);
    }

    static void twoPointer() {

        // 투 포인터를 위한 정렬
        Arrays.sort(arr);

        // 반례 (while 문의 조건은 low <= high 가 되면 안된다.)
        // 2
        // 1 13
        // 2
        // 답 : 0


        // 시간 복잡도 = O(n)?
        int low = 0;
        int high = arr.length - 1;
        int sum = 0;

        while (low < high) {
            sum = arr[low] + arr[high];

            if (sum == x) {
                cnt++;
                low++;
                high--;
                continue;
            }

            if (arr[low] + arr[high] > x)
                high--;
            else
                low++;
        }
    }

    static void binarySearch() {

        // 이진 탐색을 위한 정렬
        Arrays.sort(arr);

        for (int i = 0; i < arr.length; i++) {

            int find = x - arr[i];
            int low = i;
            int high = arr.length - 1;
            int mid = 0;
            while (low < high) {

                mid = (low + high) / 2;

                if (arr[mid] > find)
                    high = mid - 1;
                else if (arr[mid] <= find)
                    low = mid + 1;

            }
            if (arr[high] == find)
                cnt++;
        }
    }
}
