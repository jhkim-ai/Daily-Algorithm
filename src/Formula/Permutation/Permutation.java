package Formula.Permutation;

import java.util.*;

public class Permutation {

    static int N, M, index;

    public static void main(String[] args) {

        N = 3;
        M = 5;

        // m P n
        permutation(N, new int[N], new boolean[M]);
        System.out.println(index);
    }

    static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            System.out.println(Arrays.toString(selected));
            index++;
            return;
        }

        for(int i = 0; i < visited.length; ++i){
            if(visited[i])  continue;
            visited[i] = true;
            selected[selected.length - cnt] = i;
            permutation(cnt-1, selected, visited);
            visited[i] = false;
        }
    }
}
