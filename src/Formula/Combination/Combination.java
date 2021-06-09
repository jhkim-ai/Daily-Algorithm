package Formula.Combination;

import java.util.*;

public class Combination {

    static int N, M, index;

    public static void main(String[] args) {

        N = 4;
        M = 6;

        // m C n
        combination(4, new int[4], 0);
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
            combination(cnt-1, selected, i+1);
        }
    }
}
