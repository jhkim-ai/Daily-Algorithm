package Category.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ15810_풍선공장 {

    private static int N, M;
    private static int[] staffs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        staffs = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            staffs[i] = Integer.parseInt(st.nextToken());
        }

        // BinarySearch - Lower Bound (K값 이상의 값이 처음 나타나는 위치를 찾을 때)
        long left = 0l;
        long right = 1000000000001l;
        while(left < right){
            long mid = (left + right) / 2;

            long sum = 0;
            for(int staff : staffs){
                sum += mid / staff;
            }

            if(sum >= M) right = mid;
            else left = mid+1;
        }

        System.out.println(right);
    }
}
