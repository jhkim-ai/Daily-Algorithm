import java.io.*;
import java.util.*;

public class Main {

    private static int N, idx, M;
    public static void main(String[] args) {

        N = 5;
        M = 3;
        idx = 1;
//        permutation(N, new int[N], new boolean[N]);
//        combination(M, new int[M], 0);
        powerSet();
    }

    public static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            System.out.println(idx++ +" " + Arrays.toString(selected));
            return;
        }

        for (int i = 0; i < N; i++) {
                selected[selected.length - cnt] = i;
                permutation(cnt-1, selected, visited);

        }
    }

    public static void combination(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            System.out.println(idx++ + " " + Arrays.toString(selected));
            return;
        }
        for (int i = startIdx; i < N; i++) {
            selected[selected.length - cnt] = i;
            combination(cnt-1, selected, i);
        }
    }

    public static void powerSet(){
        List<Integer> list = new ArrayList<>();
        for(int i = 1; i < (1 << N); ++i){
            list.clear();
            for(int j = 0; j < N; ++j){
                if((i & (1 << j)) > 0){
                    list.add(j);
                }
            }
            System.out.println(idx++ + " " + list);
        }
    }
}
