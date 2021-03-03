package Baekjoon;

import java.io.*;
import java.util.*;

public class Baekjoon17413 {

    // 출력 데이터 저장소
    static Queue<Character> q;
    // 문자열 reverse 를 위한 stack
    static Stack<Character> s;


    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(src));
        StringBuilder sb = new StringBuilder();

        // input 데이터
        String str = br.readLine();

        // 출력 데이터 저장소
        q= new LinkedList<>();
        // 문자열 reverse 를 위한 stack
        s = new Stack<>();
        
        // "<" 와 ">"가 기준
        boolean isBracket = false;
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            
            // "<" 나오면, 1. 문자열의 시작이면 바로 Stack 이 비어있음으로 ">"가 나올 때까지 Queue 에 저장
            //            2. 문자열 중간에 나오면, 기존에 저장된 Stack 을 Queue 로 보냄
            if (c == '<') {
                if(!s.isEmpty())
                    sendQ();
                isBracket = true;
            }
            
            // "< .... >"가 아니면 Stack에 저장
            // 만약 공백이 나타나면, 이전에 저장된 Stack 의 데이터를 공백과 같이 Queue 로 전송. 
            if(!isBracket){
                if(c == ' '){
                    if(!s.isEmpty())
                        sendQ();
                    q.offer(' ');
                }
                else{
                    s.push(c);
                }

            }
            // "< .... >"가 맞다면 그대로 Queue 로 전송
            // 만약, ">"가 나타아면 괄호의 끝임으로 Queue로 전송 후, isBracket = false로 수정
            else{
                q.offer(c);
                if (c == '>'){
                    isBracket = false;
                }
            }
        }

        // 마지막이 괄호로 안끝났을 경우
        if(!s.isEmpty())
            sendQ();

        // Queue 에 저장된 정답 출력
        for(char t : q)
            sb.append(t);
        System.out.println(sb);
    }

    // Stack 의 데이터를 Queue 로 보내는 method
    static void sendQ(){
        while(!s.isEmpty())
            q.offer(s.pop());
    }

    static String src = "<problem>17413<is hardest>problem ever<end>";
}
