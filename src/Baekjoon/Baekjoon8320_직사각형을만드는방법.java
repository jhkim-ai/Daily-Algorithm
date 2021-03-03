package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon8320_직사각형을만드는방법 {

    static int N, ans;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        N = Integer.parseInt(br.readLine());
        ans = 0;
        
        // 1. N개 이하의 정사각형으로 직사각형을 만들어야 한다.
        // 2. 정사각형의 개수 = 가로 변 * 세로 변
        // 3. 중복 x (1,2) 와 (2,1)은 같은 것.
        // 4. 결론, 중복 조합
        
        for (int i = 1; i <= N; i++) {
            // 중복조합이므로, j는 i부터 시작
            for (int j = i; j <= N; j++) {
                // 주어진 정사각형의 개수가 N 개보다 크면, 다음 경우의 수를 찾는다.
                if(i*j > N)
                    break;
                ans++;
            }
        }
        System.out.println(ans);
    }

    static String input = "6";
}
