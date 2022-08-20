package Category.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ9012_괄호 {

    private static int N;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        while(N-- > 0){
            char[] chars = br.readLine().toCharArray();
            Stack<Character> s = new Stack<>();
            boolean isValid = true;
            for(char c : chars){
                if(c == '('){
                    s.push(c);
                    continue;
                }

                if(s.isEmpty()){
                    isValid = false;
                    break;
                }

                if(s.peek() == '('){
                    s.pop();
                    continue;
                }
                isValid = false;
                break;
            }

            if(!s.isEmpty()){
                isValid = false;
            }

            if(!isValid){
                sb.append("NO");
            } else {
                sb.append("YES");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
