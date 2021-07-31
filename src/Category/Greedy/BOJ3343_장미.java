package Category.Greedy;

import java.io.*;
import java.util.*;

public class BOJ3343_장미 {

    private static long N, A, B, C, D;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Long.parseLong(st.nextToken());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());
        D = Long.parseLong(st.nextToken());

        if(A > C){
            long tmp = C;
            C = A;
            A = C;
            tmp = D;
            D = B;
            B = D;
        }

        long left = 0l;
        long right = N / C;
        if(N%C != 0) right++;

        // lower Bound
        while(left < right){
            long mid = (left + right) / 2;

        }

        long ans = Long.MAX_VALUE;
        long aCount = 0, cCount = 0;
        int flag = 0;
        while (true) {
            long count = N;
            long total = 0; // 금액

            count -= C * cCount;
            aCount = count / A;
            if(count <= 0) {
                aCount = 0;
                flag++;
            }
            else if(count % A != 0) aCount++;
            if(flag >= 2) break;
//            System.out.println("aCount : " + aCount + ", cCount: " + cCount);

            total = aCount * B + cCount * D;
//            System.out.println("total: " + total);
//            System.out.println("==================");
            ans = Math.min(total, ans);
            cCount++;
        }
        System.out.println(ans);
    }
}
