package StepByStep._2022._22_01_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class BOJ1918_후위표기식 {

    private static Map<Character, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        map = new HashMap<>();
        map.put('/', 1); // *와/ 의 우선순위는 +와- 보다 높다
        map.put('*', 1);
        map.put('+', 2);
        map.put('-', 2);

        int a = 'A';
        int b = 'Z';
        postfixExpression(arr);
    }

    public static void postfixExpression(char[] arr){
        Stack<Character> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for(char c : arr){
            if(c >= 65 && c <= 90){
                sb.append(c);
                continue;
            }


        }
    }
}
