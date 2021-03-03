package Baekjoon;

import java.util.*;

public class Baekjoon2164 {

    static int N;
    static LinkedList<Integer> arr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            arr.add(i);
        }

        while (arr.size() != 1) {
            arr.remove(0);
            arr.add(arr.get(0));
            arr.remove(0);
        }

        System.out.println(arr.get(0));
    }

    // ArrayList 로 진행을 했었지만, 시간 초과
    // ArrayList, LinkedList, HashMap 의 속도 비교 ( 참고 - https://nnoco.tistory.com/73 )
}
