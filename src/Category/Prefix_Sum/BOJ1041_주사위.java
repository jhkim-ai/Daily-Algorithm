package Category.Prefix_Sum;

import java.util.*;
import java.io.*;

public class BOJ1041_주사위 {

    private static int N;
    private static int[] dice;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < 6; ++i){
            dice[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(Arrays.toString(dice));
    }
}
