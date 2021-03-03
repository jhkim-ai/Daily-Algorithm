package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Locale;

public class Baekjoon11720 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine().toUpperCase(Locale.ROOT);
        int [] cnt = new int[26];

        for (int i = 0; i < str.length(); i++) {
            cnt[str.charAt(i)-65]++;
        }
        int max = 0;
        boolean duplicate = false;
        for (int i = 0; i< cnt.length; i++){
            if(cnt[max] < cnt[i]) {
                max = i;
                duplicate = false;
            }else if (cnt[max] == cnt[i]){
                duplicate = true;
            }
        }
        if(duplicate)
            System.out.println("?");
        else{
            System.out.println((char)(max+65));
        }
    }
}
