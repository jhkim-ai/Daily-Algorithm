package Baekjoon;

import java.util.*;
import java.io.*;

public class Baekjoon2941 {

    static String str;
    static int ans = 0;

    public static void main(String[] args) throws Exception {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        str = br.readLine();

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            System.out.println(c);
            if (!isIn(i + 1))
                ans++;
            else {
                switch (c) {
                    case 'c':
                        if (str.charAt(i + 1) == '=' || str.charAt(i + 1) == '-') {
                            ans++;
                            i++;
                        } else
                            ans++;
                        break;

                    case 'd':
                        if (str.charAt(i + 1) == '-') {
                            ans++;
                            i++;
                        } else if (str.charAt(i + 1) == 'z'){
                            if( isIn(i+2) && str.charAt(i + 2) == '=') {
                                ans++;
                                i = i + 2;
                            }else {
                                i++;
                                ans += 2;
                            }
                        } else
                            ans++;
                        break;
                    case 'l':
                        if (str.charAt(i + 1) == 'j') {
                            ans++;
                            i++;
                        } else
                            ans++;
                        break;
                    case 'n':
                        if (str.charAt(i + 1) == 'j') {
                            ans++;
                            i++;
                        } else
                            ans++;
                        break;
                    case 's':
                        if (str.charAt(i + 1) == '=') {
                            ans++;
                            i++;
                        } else
                            ans++;
                        break;
                    case 'z':
                        if (str.charAt(i + 1) == '=') {
                            ans++;
                            i++;
                        } else
                            ans++;
                        break;
                    default:
                        ans++;
                }
            }
        }
        System.out.println(ans);
    }

    static String input = "ljes=njak";

    static boolean isIn(int n) {
        return 0 <= n && n < str.length();
    }
}
