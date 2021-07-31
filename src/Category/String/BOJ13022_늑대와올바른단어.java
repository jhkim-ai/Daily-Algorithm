package Category.String;

import java.util.*;
import java.io.*;

public class BOJ13022_늑대와올바른단어 {

    private static char[] input;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        input = br.readLine().toCharArray();

        int ans = 0;
        int count = 0;

        int wCount = 0;
        int oCount = 0;
        int lCount = 0;
        int fCount = 0;
        if(input[0] == 'w'){
            if(isRight()) ans = 1;
        }
        System.out.println(ans);
    }
    public static boolean isRight(){
        int wCount = 0;
        int nextCount = 0;
        char before = input[0];

        for(int i = 0; i < input.length; ++i){
            char c = input[i];
            if(before == 'w' && before == c) {
                wCount++;
                continue;
            }
            if(before == c){
                nextCount++;
                continue;
            }
            if(before != 'w' && wCount != nextCount) return false;
            if(before == 'f' && c == 'w'){
                before = c;
                wCount = 0;
                wCount++;
                continue;
            }
            if((before == 'w' && c != 'o') || (before == 'o' && c != 'l') || (before == 'l' && c != 'f') || (before == 'f' && c != 'w')) return false;
            before = c;
            nextCount = 0;
            nextCount++;
        }
        if(before != 'f') return false;
        if(wCount != nextCount) return false;
        return true;
    }
}
