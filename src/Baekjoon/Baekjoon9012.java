package Baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;

// 괄호의 개수 Count
public class Baekjoon9012 {

    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int t = 0 ; t<N;t++){
            int cnt = 0;
            String input = br.readLine();

            for (int i = 0; i<input.length();i++){
                char c = input.charAt(i);

                if(c == '(')
                    cnt++;
                else {
                    cnt--;
                    if(cnt <0)
                        break;
                }
            }

            if (cnt != 0)
                System.out.println("NO");
            else
                System.out.println("YES");
        }
    }

//    // Stack 으로 풀기

//    static int N;
//    static Stack<Character> list;
//
//    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        N = Integer.parseInt(br.readLine());
//        System.out.println(N);
//        for (int t = 0; t < N; t++) {
//            list = new Stack<>();
//            String input = br.readLine();
//
//            for (int i = 0; i < input.length(); i++) {
//                if(!list.isEmpty() && list.peek() == ')'){
//                    break;
//                }
//
//                char c = input.charAt(i);
//
//                if (c == '(') {
//                    list.push(c);
//                    System.out.println(list);
//                }
//                else {
//                    if (!list.isEmpty() && list.peek() == '(')
//                        list.pop();
//                    else
//                        list.push(c);
//                    System.out.println(list);
//                }
//
//            }
//
//            if (!list.isEmpty())
//                System.out.println("NO");
//            else
//                System.out.println("YES");
//        }
//
//    }
}
