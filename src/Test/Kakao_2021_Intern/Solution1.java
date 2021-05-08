package Test.Kakao_2021_Intern;

import java.util.*;
import java.io.*;

public class Solution1 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int answer = Integer.parseInt(convertStringToNumber(str));
        System.out.println(answer);
    }

    // 문자 => 숫자 변환
    public static String convertStringToNumber(String str) {
        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < str.length(); ++idx) {
            char now = str.charAt(idx);

            // 숫자면 pass
            if (isNumber(now)) {
                sb.append(now);
                continue;
            }
            // 'zero'
            if (now == 'z') {
                sb.append(0);
                idx += 3;
            }
            // 'one'
            else if (now == 'o') {
                sb.append(1);
                idx += 2;
            } else if (now == 't') {
                char next = str.charAt(++idx);
                // 'two'
                if (next == 'w') {
                    sb.append(2);
                    ++idx;
                }
                // 'three'
                else {
                    sb.append(3);
                    idx += 3;
                }
            } else if (now == 'f') {
                char next = str.charAt(++idx);
                // 'four'
                if (next == 'o') {
                    sb.append(4);
                }
                // 'five'
                else {
                    sb.append(5);
                }
                idx += 2;
            } else if (now == 's'){
                char next = str.charAt(++idx);
                // 'six'
                if (next == 'i') {
                    sb.append(6);
                    ++idx;
                }
                // 'seven'
                else {
                    sb.append(7);
                    idx += 3;
                }
            }
            // 'eight'
            else if (now=='e'){
                sb.append(8);
                idx += 4;
            }
            // 'nine'
            else {
                sb.append(9);
                idx += 3;
            }
        }
        return sb.toString();
    }

    // 현재 Character가 숫자인지 확인
    public static boolean isNumber(char c) {
        int ascii = c - '0';
        return ascii >= 0 && ascii <= 9;
    }
}
