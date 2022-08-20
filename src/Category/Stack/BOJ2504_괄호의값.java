package Category.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ2504_괄호의값 {

    private static String[] brackets = {"(", ")", "[", "]"};

    private static char[] input;
    private static Stack<String> s;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();
        s = new Stack<>();

        int ans = 0;
        boolean isValid = true;
        for (int i = 0; i < input.length; ++i) {
            String c = String.valueOf(input[i]);
            if (c.equals(brackets[0])) { // == "("
                s.push(c);
            } else if (c.equals(brackets[2])) { // == "["
                s.push(c);
            } else if (c.equals(brackets[1])) { // == ")"
                isValid = calculate(1, 2);
            } else if (c.equals(brackets[3])) { // == "]
                isValid = calculate(3, 3);
            } else {
                isValid = false;
            }

            if (!isValid) {
                break;
            }
        }

        if (!isValid) {
            ans = 0;
        } else {
            outer:
            while (!s.isEmpty()) {
                String tmp = s.pop();
                if(isBracket(tmp)){
                    ans = 0;
                    break outer;
                }
                ans += Integer.parseInt(tmp);
            }
        }
        System.out.println(ans);
    }

    // idx = 1;
    public static boolean calculate(int idx, int mul) {

        if (s.isEmpty()) {
            return false;
        }

        int sum = 0;
        if (s.peek().equals(brackets[idx - 1])) { // peek 가 "(" or "[" 이면, 합할 숫자가 없다
            sum = 1;
        } else {
            while (!s.peek().equals(brackets[idx - 1])) {
                // 짝이 안맞으면 pass
                if (s.peek().equals(brackets[(idx + 1) % 4])) {
                    return false;
                }

                String tmp = s.pop();
                sum += Integer.parseInt(tmp);

                if (s.isEmpty()) {
                    return false;
                }
            }
        }
        s.pop(); // "(" or "[" 버리기
        sum *= mul;
        s.push(String.valueOf(sum));

        return true;
    }

    public static boolean isBracket(String str){
        for (String bracket : brackets) {
            if (str.equals(bracket)) {
                return true;
            }
        }
        return false;
    }
}
