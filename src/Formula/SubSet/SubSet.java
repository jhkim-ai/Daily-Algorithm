package Formula.SubSet;

import java.util.*;

public class SubSet {

    static int N, index;
    static int[] arr;
    static List<Integer> list;

    public static void main(String[] args) {
        N = 4;
        arr = new int[]{1, 2, 3, 4};
        list = new ArrayList<>();

        // 집합의 원소 개수가 N 개 일때의 부분집합
        subSet();
    }

    static void subSet() {
        for (int i = 1; i < (1 << N); ++i) {
            list.clear();
            for (int j = 0; j < N; ++j) {
                if ((i & (1 << j)) > 0) list.add(arr[j]);
            }
            startLogic();
        }
    }

    static void startLogic() {
        index++;
        System.out.println(index + ": " + list);
    }
}
