package StepByStep.day210305;

import java.io.BufferedReader;
import java.io.StringReader;

public class BOJ1541_S2_괄호 {

    static String str;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));

        int ans = 0;
        int start = -1, end = 0;
        char op = '+';
        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '+' && op == '+') {
                String tmp = str.substring(start, end + 1);
                System.out.println("a :" + tmp);
                ans += Integer.parseInt(tmp);
                start = -1;
            } else if (str.charAt(i) == '+' && op == '-') {
                String tmp = str.substring(start, end + 1);
                System.out.println("b :" + tmp);
                ans -= Integer.parseInt(tmp);
                start = -1;
            } else if (str.charAt(i) == '-' && op == '+') {
                op = '-';
                String tmp = str.substring(start, end + 1);
                System.out.println("c :" + tmp);
                ans += Integer.parseInt(tmp);
                start = -1;

            } else if (str.charAt(i) == '-' && op == '-') {
                String tmp = str.substring(start, end + 1);
                ans -= Integer.parseInt(tmp);
                start = -1;
            } else if (str.charAt(i) != '+' && str.charAt(i) != '-') {
                if (start == -1) {
                    start = i;
                    end = start;
                } else
                    end++;
            }
        }

        if (op == '-') {
            String tmp = str.substring(start, str.length());
            ans -= Integer.parseInt(tmp);
        } else {
            String tmp = str.substring(start, str.length());
            ans += Integer.parseInt(tmp);
        }
        System.out.println(ans);
    }

    static String input = "55-50+40";
}
