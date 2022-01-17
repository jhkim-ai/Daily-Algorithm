package StepByStep.day210403;

import java.util.*;
import java.io.*;

public class BOJ9342_S4_염색체 {

    static int T;
    static String str, regex;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());
        regex = "^[A-F]?A+F+C+[A-F]?$"; // 정규 표현식
        
        for (int t = 0; t < T; t++) {
            str = br.readLine();
            if(str.matches(regex))
                sb.append("Infected!");
            else
                sb.append("Good");
            sb.append("\n");
        }
        System.out.println(sb);
    }

}
