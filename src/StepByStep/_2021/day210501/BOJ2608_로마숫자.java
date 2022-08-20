package StepByStep._2021.day210501;

import java.util.*;
import java.io.*;

public class BOJ2608_로마숫자 {

    static String input;
    static Map<Character, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        // 로마숫자와 아라비아 숫자 매칭 (아스키코드를 이용해서 int 배열을 넣는게 더 효율)
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // 두 로마 숫자의 합
        int sum = 0;
        for (int i = 0; i < 2; ++i) {
            sum += romanToNumber(br.readLine());
        }

        // numberToRoman(sum) = sum 을 로마 숫자로 바꿈
        sb.append(sum).append("\n").append(numberToRoman(sum));
        System.out.println(sb);
    }

    // 로마 숫자 -> 아라비아 숫자
    public static int romanToNumber(String str) {
        int result = 0;

        for (int idx = 0; idx < str.length(); ++idx) {
            int now = map.get(str.charAt(idx)); // 현재 숫자

            // 현재숫자(10) < 다음 숫자(50) ==> 다음 숫자(50) - 현재숫자(10)
            if (idx != str.length() - 1 && now < map.get(str.charAt(idx + 1))) {
                result += map.get(str.charAt(idx + 1)) - now;
                idx++;
                continue;
            }
            result += now;
        }

        return result;
    }

    // 아라비아 숫자 -> 로마 숫자
    public static String numberToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        while (num != 0) {
            if (num >= 1000) {
                sb.append("M");
                num -= 1000;
            } else if (num / 100 == 9) {
                sb.append("CM");
                num -= 900;
            } else if (num >= 500) {
                sb.append("D");
                num -= 500;
            } else if (num / 100 == 4) {
                sb.append("CD");
                num -= 400;
            } else if (num >= 100) {
                sb.append("C");
                num -= 100;
            } else if (num / 10 == 9) {
                sb.append("XC");
                num -= 90;
            } else if (num >= 50) {
                sb.append("L");
                num -= 50;
            } else if (num / 10 == 4) {
                sb.append("XL");
                num -= 40;
            } else if (num >= 10) {
                sb.append("X");
                num -= 10;
            } else if (num % 10 == 9) {
                sb.append("IX");
                num -= 9;
            } else if (num >= 5) {
                sb.append("V");
                num -= 5;
            } else if (num % 10 == 4) {
                sb.append("IV");
                num -= 4;
            } else if (num >= 1) {
                sb.append("I");
                num -= 1;
            }
        }

        return sb.toString();
    }
}
