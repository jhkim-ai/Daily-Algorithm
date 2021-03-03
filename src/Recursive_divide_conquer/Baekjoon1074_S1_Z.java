package Recursive_divide_conquer;

import java.io.*;
import java.util.*;

public class Baekjoon1074_S1_Z {

    static int Y, X, N;
    static int ans;

    public static void main(String[] args) throws Exception{
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader br = new BufferedReader(new StringReader(input));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        find(0, 0, (int)Math.pow(2, N));
    }

    static void find(int y, int x, int len){
        if(len == 1){
            if(y == Y && x == X){
                System.out.println(ans);
                System.exit(0);
            }
            ans++;
            return;
        }

        if(!(y<= Y && Y <y+len && x<= X && X < x+len)){
            ans += len * len;
            return;
        }

        int nextLen = len / 2;

        find(y, x, nextLen);
        find(y, x +nextLen, nextLen);
        find(y+nextLen, x, nextLen);
        find(y+nextLen, x+nextLen, nextLen);

        // for 문으로 묶기
        /*for(int i = 0; i<2; i++){
            for(int j=0; j<2; j++){
                find(y+nextLen*i, x+nextLen*j, nextLen);
            }
        }*/

    }

    static String input = "3 7 7";
}
