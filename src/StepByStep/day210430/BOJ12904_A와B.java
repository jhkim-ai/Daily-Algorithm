package StepByStep.day210430;

import java.util.*;
import java.io.*;

public class BOJ12904_A와B {

    static String S,T;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        S = br.readLine();
        T = br.readLine();
        StringBuilder sb = new StringBuilder(T);

        int sLen = S.length();
        int tLen = T.length();

        // Idea. 역발상 (사다리타기와 비슷)
        //       S -> T 가 아닌
        //       T -> S 로 찾자.

        // 길이가 같을 때까지 반복
        while(sLen < tLen){
            // 마지막이 B라면
            if(sb.charAt(tLen-1) == 'B'){
                sb.deleteCharAt(tLen-1);
                tLen--;
                sb.reverse();
            }
            // 마지막이 A라면
            else{
                sb.deleteCharAt(tLen-1);
                tLen--;
            }
        }
        
        // 길이가 같아지면, 정답 확인
        int ans = 0;
        if(sb.toString().equals(S))
            ans = 1;
        System.out.println(ans);
    }
}
