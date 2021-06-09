package Formula.Combination;

import java.util.Arrays;

public class Combination_Duplicated {

    static int N, M, index;

    public static void main(String[] args) {

        N = 2;
        M = 5;

        // m H n
        combination(N, new int[N], 0);
        System.out.println("==============");
        System.out.println(index);
    }

    static void combination(int cnt, int[] selected, int startIdx){
        if(cnt == 0){
            System.out.println(Arrays.toString(selected));
            index++;
            return;
        }

        for(int i = startIdx; i < M; ++i){
            selected[selected.length - cnt] = i;
            combination(cnt-1, selected, i);
        }
    }
}
