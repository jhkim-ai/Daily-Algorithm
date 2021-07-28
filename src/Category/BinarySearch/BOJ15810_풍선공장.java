package Category.BinarySearch;

import java.util.*;
import java.io.*;

public class BOJ15810_풍선공장 {

    private static int N, M;
    private static int[] staff;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        staff = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; ++i){
            staff[i] = Integer.parseInt(st.nextToken());
        }

        long left = 0;
        long right = 1000000000000l;

        while(left <= right){
            long mid = (left + right) / 2l;
            long sum = 0;
            for(int i = 0; i < N; ++i){
                sum += mid / staff[i];
            }
            if(sum < M) left = mid+1;
            else right = mid-1;
        }
        System.out.println(left);
    }
}
