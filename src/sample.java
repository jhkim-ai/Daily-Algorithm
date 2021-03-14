import java.io.*;
import java.util.*;

public class sample {

    static String str;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new StringReader(input));
        str = br.readLine();

        // --------- 알고리즘 시작 --------- //
        // Idea. 2는 4의 약수이다
        // 즉, 2의 배수: 2 4 6 8 10 12 14 16 18 20 ...
        //     4의 배수: 4 8 12 16 20 ...
        // (2의 배수 / 4의 배수)를 한다면 나머지는 0 or 2 만 나온다.

        int cnt = 0;
        int idx = 0;
        while (idx < str.length()) {
            // 한 어절의 X의 수 - ('.'이 나오기 전까지)
            if (str.charAt(idx) != '.') {
                cnt++;
                idx++;
            }
            // '.'이 나왔을 경우 X를 폴리오미노로 덮기
            else {
                idx++;
                // X가 홀수 개면, "-1"
                if (cnt % 2 == 1) {
                    cnt = -1;
                    break;
                }
                if (cnt == 0) {
                    sb.append(".");
                    continue;
                }
                check(cnt);
                sb.append(".");
                cnt = 0;
            }
        }
        
        // 마지막 어절 처리
        if (cnt > 0) {
            // TestCase가 "X" 일 경우 (한 자리)
            if(cnt == 1)
                cnt = -1;
            else {
                check(cnt);
                cnt = 0;
            }
        }

        // 정답 출력
        if (cnt != -1)
            System.out.println(sb);
        else {
            System.out.println(-1);
        }
    }

    static void check(int cnt) {

        // X가 짝수 개면 4로 나눈 몫만큼 "AAAA"로 대체
        for (int i = 0; i < cnt / 4; i++) {
            sb.append("AAAA");
        }
        // X를 4로 나누었을 때 나머지가 2라면, "BB"로 대체
        if (cnt % 4 != 0)
            sb.append("BB");
    }

    static String input = "XX.XX";
}
