package Category.Dynamic_Programming;

import java.util.*;
import java.io.*;

public class BOJ1010_S5_다리놓기 {

    static int T;
    static int N, M;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            sb.append(comb(M, N)).append("\n");
        }
        System.out.println(sb);
    }

    static long comb(int n, int r){
        if(r > (n/2)){
            r = n -r;
        }
        if(r == 0){
            return 1;
        }
        long a = 1;
        long b = 1;
        for (int i = 1; i <= r ; i++) {
            a *= n--;
            b *= i;
        }

        return a/b;
    }
}
