package Category.String;

import java.util.*;
import java.io.*;

public class BOJ4889_안정적인문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> s = new Stack<>();

        int testcase = 0; // 테스트케이스 번호
        while (true) {
            ++testcase;
            int ans = 0; // 바꿔야하는 횟수
            char[] input = br.readLine().toCharArray(); // 입력 String
            if(input[0] == '-') break; // 테스트케이스 종료 조건

            // 1. 길이가 0인 경우 안정적인 문자열이다.
            if (input.length == 0) {
                sb.append(String.format("%d. %d\n", testcase, ans));
                continue;
            }
            // 2. input 을 모두 돌아 먼저 짝이 지어진 것들을 삭제한다.
            else {
                for (char c : input) {
                    if (s.empty()) s.add(c);
                    else {
                        char before = s.peek();
                        if (before == '{' && c == '}') s.pop(); // 짝이 지어진 것들은 제외
                        else s.push(c);
                    }
                }
            }
            // 3. 짝이 지어진 것들이 삭제되면, 자동으로 짝이 안남는 것들만 남게 된다
            //    ※ "}{" 이 나올 수 있는 경우는 맨 앞과 맨 뒤인 2가지 경우만 존재
            //    ※ 이 외의 모든 예외는 "}}" or "{{"만 나온다.
            // 따라서, 예의의 모든 경우들을 안정적인 문자열로 바꿔준다.
            while (!s.empty()) {
                char now = s.pop();
                if (now == s.peek()) ++ans; // "}}" or "{{"인 경우 (1번만 바꾼다)
                else ans += 2;              // "}{"인 경우 (2번을 바꿔야 한다)
                s.pop();
            }
            sb.append(String.format("%d. %d\n", testcase, ans));
        }
        System.out.println(sb);
    }
}
