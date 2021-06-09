package Formula.Permutation;

import java.util.*;

public class Permutation_BitMasking {

    static int N, M, index;

    public static void main(String[] args) {

        N = 4;
        M = 6;

        // m P n (순열)
        permutationBitMasking(N, new int[N], 0);
        System.out.println(index);
    }

    static void permutationBitMasking(int cnt, int[] selected, int flag) {
        if (cnt == 0) {
            System.out.println(Arrays.toString(selected));
            index++;
            return;
        }

        for (int i = 0; i < M; ++i) {
            if ((flag & (1 << i)) > 0) continue;
            selected[selected.length - cnt] = i;
            permutationBitMasking(cnt-1, selected, flag | (1 << i));
        }
    }
}
