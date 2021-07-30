package Category.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ2110_공유기설치 {

    private static int N, C;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        arr = new int[N];

        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(br.readLine());
        }

        // 주의! "가장 인접한 두 공유기 사이의 거리를 가능한 크게"의 의미를 잘 헤아리자.
        // 1. 거리가 가장 짧게(?) C개의 공유기를 설치하는 것은 일정한 간격으로 설치하는 것이다.
        // 2. 최대 거리가 10억임으로 이분탐색을 사용해야 한다. (min = 1, max = arr[arr.length-1] - arr[0])
        // 3. 일정 거리(mid = (min+max)/2)를 기준으로 모든 집(원소)에 대하여 설치하고 비교하며 탐색한다.
        // 4. 다시 2번을 실행하며 반복한다.

        Arrays.sort(arr); // 이분 탐색을 위해선 먼저 오름차순 정렬

        int minLen = 1; // 최소거리
        int maxLen = arr[arr.length - 1] - arr[0]; // 최대거리

        // 이분 탐색
        while(minLen <= maxLen){
            int mid = (minLen + maxLen) / 2;
            int count = 1; // 공유기를 설치한 집의 수
            int curHome = arr[0]; // 공유기가 설치된 집

            // 각 집을 비교
            for(int nextHome : arr){
                // 다음 집이 (현재 설치된 집의 위치 + 거리)이상이라면, 공유기 설치가 가능
                // (가장 인접한  두 공유기 사이의 거리를 가능한 크게 하기 위해 "이상"을 사용하여 비교)
                if(curHome + mid <= nextHome){
                    ++count; // 공유기 설치 집의 수 증가
                    curHome = nextHome; // 마지막으로 설치된 집의 위치를 저장
                }
            }

            // lower bound (K값 이상)
            // mid 를 포함하지 않기 때문에 기저조건(minLen <= maxLen)에서 등호를 넣어줘야 한다.
            if(count >= C) minLen = mid + 1; // 공유기 개수가 많다면 거리를 늘린다.
            else maxLen = mid - 1;           // 공유기 개수가 적다면 거리를 줄인다.
        }

        System.out.println(maxLen);
    }
}
