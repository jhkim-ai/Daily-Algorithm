package StepByStep.day210423;

import java.util.*;
import java.io.*;

public class BOJ1662_압축 {

    static String str;

    public static void main(String[] args) throws Exception {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        str = br.readLine();

        // Idea. 길이를 계산

        // 주의!. String으로 접근했을 때, Worst Case로에 의하여 메모리 초과가 난다.
        // Worst Case : 9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(111))))))))))))))))))))))))


    }

    //    static String input = "33(56(2)1(719))2(2)";
//    static String input = "33(562(71(9)))";
//        static String input = "6(22)122";
//        static String input = "3(3(3(2(2)2(2))))1(1)1(1)";
//    static String input = "3333";
    static String input = "9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(9(111))))))))))))))))))))))))";
}
