package StepByStep.day211030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ3986_좋은단어 {

    private static int N, ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        while(N-- > 0){
            char[] input = br.readLine().toCharArray();
            Stack<Character> stack = new Stack<>();
            if(input.length % 2 == 1){
                continue;
            }
            for (int i = 0; i < input.length; i++) {
                char c = input[i];

                if(stack.isEmpty()){
                    stack.push(c);
                    continue;
                }

                if(c == stack.peek()){
                    stack.pop();
                    continue;
                } else {
                    stack.push(c);
                }
            }
            if(stack.isEmpty()){
                ans++;
            }
        }

        System.out.println(ans);
    }
}
