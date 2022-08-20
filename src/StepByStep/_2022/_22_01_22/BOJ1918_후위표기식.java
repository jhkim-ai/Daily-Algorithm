package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BOJ1918_후위표기식 {

    private static Map<Character, Integer> map;
    private static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        sb = new StringBuilder();
        map = new HashMap<>();
        map.put('/', 1); // *와/ 의 우선순위는 +와- 보다 높다
        map.put('*', 1);
        map.put('+', 2);
        map.put('-', 2);
        map.put('(', 3);

        postfixExpression(arr);
        System.out.println(sb);
    }

    public static void postfixExpression(char[] arr){
        Stack<Character> stack = new Stack<>(); // 사칙연산 저장소

        for(char c : arr){
            if(c >= 65 && c <= 90){             // 문자는 바로 출력한다.
                sb.append(c);
                continue;
            }

            if(c == ')'){                       // ( ... ) 괄호 안의 모든 사칙연산을 출력(pop)한다.
                stackPopBracket(stack);
            }
            // stack 이 비어있거나, '(' 괄호가 열려있거나, stack의 Top에 있는 사칙연산보다 현재 사칙연산이 우선순위가 더 높을 때
            else if(stack.empty() || c == '(' || map.get(stack.peek()) > map.get(c)){
                stack.push(c);
            }
            // stack의 Top에 있는 사칙연산보다 현재 사칙연산이 우선순위가 더 낮거나 같을 때 -> 현재보다 낮은 연산자가 나올 때까지 pop 한다.
            else if(map.get(stack.peek()) <= map.get(c)){
                stackPopPriority(stack, c);
                stack.push(c);
            }
        }
        if(!stack.isEmpty()) {
            stackPopRestElement(stack);
        }
    }

    public static void stackPopPriority(Stack<Character> stack, char c){
        while(!stack.isEmpty() && map.get(stack.peek()) <= map.get(c)){
            sb.append(stack.pop());
        }
    }

    public static void stackPopBracket(Stack<Character> stack){
        while(stack.peek() != '('){
            sb.append(stack.pop());
        }
        stack.pop();
    }

    public static void stackPopRestElement(Stack<Character> stack){
        while(!stack.isEmpty()){
            sb.append(stack.pop());
        }
    }
}
