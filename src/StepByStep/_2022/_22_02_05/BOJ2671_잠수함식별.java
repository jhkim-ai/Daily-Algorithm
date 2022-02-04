package StepByStep._2022._22_02_05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class BOJ2671_잠수함식별 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        boolean regCheck = Pattern.matches("^(100+1+|01)+$", input);

        if(regCheck){
            System.out.println("SUBMARINE");
        } else {
            System.out.println("NOISE");
        }
    }
}
