package SWEA.D4;

import java.util.*;
import java.io.*;

public class SWEA_1218_괄호짝짓기 {

    public static int N, ans;
    public static String str;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            N = Integer.parseInt(br.readLine());
            str = br.readLine();
            ans = 1;

            Stack<Character> s = new Stack<>();
            for(int idx = 0; idx < str.length(); ++idx){
                char c = str.charAt(idx);

                if(c == '(' || c == '{' || c == '[' || c == '<') s.push(c);
                else{
                    char stackTop = s.peek();
                    if(stackTop == '(' && c == ')') s.pop();
                    else if(stackTop == '{' && c == '}') s.pop();
                    else if(stackTop == '[' && c == ']') s.pop();
                    else if(stackTop == '<' && c == '>') s.pop();
                    else{
                        ans = 0;
                        break;
                    }
                }
            }
            sb.append(String.format("#%d %d\n", t, ans));
        }

        System.out.println(sb);
    }
}
