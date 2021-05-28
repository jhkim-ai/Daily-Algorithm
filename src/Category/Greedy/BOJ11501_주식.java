package Category.Greedy;

import java.util.*;
import java.io.*;

public class BOJ11501_주식 {

    static int T;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int t = 0 ; t< T; ++t){
            Integer.parseInt(br.readLine()); // N: 날의 수
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            Stack<Integer> s = new Stack<>();

            // N일 동안의 주식 가격 Stack 에 추가
            while(st.hasMoreTokens()){
                s.push(Integer.parseInt(st.nextToken()));
            }

            long profit = 0;          // 이익금
            int maxStock = s.pop();  // 이익을 낼 수 있는 최대 주식 (가장 마지막 날 주식으로 시작)

            // 시간을 역행하며 풀어간다.
            while(!s.isEmpty()){
                int now = s.pop();  // 현재 주식
                if(now >= maxStock) { // 전날 주식이 최대 주식보다 크거나 같으면 이익 X
                    maxStock = now;   //
                    continue;
                }
                profit += maxStock - now; // 이익을 더한다.
            }
            sb.append(profit).append("\n");
        }
        System.out.println(sb);
    }
}
