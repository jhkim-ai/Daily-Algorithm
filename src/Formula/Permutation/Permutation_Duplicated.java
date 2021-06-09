package Formula.Permutation;

import java.util.Arrays;

public class Permutation_Duplicated {
    static int N, M, index;

    public static void main(String[] args) {

        N = 3;
        M = 5;

        // m π n (중복 순열)
        permutation_Duplicated(3, new int[3]);
        System.out.println(index);
    }

    static void permutation_Duplicated(int cnt, int[] selected){
        if (cnt == 0) {
            System.out.println(Arrays.toString(selected));
            index++;
            return;
        }

        for (int i = 0; i < M; ++i) {
            selected[selected.length - cnt] = i;
            permutation_Duplicated(cnt - 1, selected);
        }
    }
}
