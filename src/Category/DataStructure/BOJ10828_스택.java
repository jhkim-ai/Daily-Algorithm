package Category.DataStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ10828_스택 {

    private static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();

        N = Integer.parseInt(br.readLine());
        while(N-- > 0){
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String type = st.nextToken();
            int num = 0;
            if(type.equals("push")){
                num = Integer.parseInt(st.nextToken());
                stack.push(num);
                continue;
            } else if (type.equals("pop")){
                if(stack.isEmpty()){
                    num = -1;
                } else {
                    num = stack.pop();
                }
            } else if (type.equals("size")){
                num = stack.size();
            } else if (type.equals("empty")){
                if(stack.isEmpty()){
                    num = 1;
                }
            } else if (type.equals("top")){
                if(stack.isEmpty()){
                    num = -1;
                } else {
                    num = stack.peek();
                }
            }
            sb.append(num).append("\n");
        }
        System.out.println(sb);
    }
}
