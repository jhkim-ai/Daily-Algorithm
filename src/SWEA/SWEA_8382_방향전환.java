package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_8382_방향전환 {

    static int startX, startY;
    static int endX, endY;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            // (0,0) 을 기준으로하기 위해 좌표이동
            endX = Math.abs(endX - startX);
            endY = Math.abs(endY - startY);

            int minLoc = Math.min(endX, endY);  // (X, Y)중 최솟값을 Get
            int line;   // 좌표축 위의 직선 거리
            int sum = 0;
            if(endY == 0 || endX == 0) {          // 같은 축에 있다면
                line = Math.max(endX, endY);
            }
            else {
                line = Math.abs(endX - endY);   // 다른 축에 있다면, [ 대각선 거리 + 직선 거리 ]
                sum += 2*minLoc;
            }
            sum += (line%2 == 0 ? line*2: line*2 - 1);  // 직선 거리 규칙(수열)

            sb.append(String.format("#%d %d\n", t, sum));
        }
        System.out.println(sb);
    }
}
