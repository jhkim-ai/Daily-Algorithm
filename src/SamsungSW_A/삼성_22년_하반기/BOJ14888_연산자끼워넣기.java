package SamsungSW_A.삼성_22년_하반기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14888_연산자끼워넣기 {
    private static final char[] OPERATIONS = {'+', '-', '*', '/'};

    private static int N, ansMin, ansMax;
    private static int[] arr;
    private static List<Character> operations;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        operations = new ArrayList<>();
        ansMin = Integer.MAX_VALUE;
        ansMax = Integer.MIN_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 4; ++i){
            int cnt = Integer.parseInt(st.nextToken()); // +, -, *. /
            for(int j = 0; j < cnt; ++j){
                operations.add(OPERATIONS[i]);
            }
        }

        permutation(N-1, new char[N-1], new boolean[N-1]);
        System.out.println(ansMax);
        System.out.println(ansMin);
    }

    public static void permutation(int cnt, char[] selected, boolean[] visited) {
        if(cnt == 0){

            int sum = arr[0];
            for(int i = 1; i < N; ++i){
                char c = selected[i-1];

                switch(c) {
                    case '+':
                        sum += arr[i];
                        break;
                    case '-':
                        sum -= arr[i];
                        break;
                    case '*':
                        sum *= arr[i];
                        break;
                    case '/':
                        sum /= arr[i];
                        break;
                }
            }

            ansMin = Math.min(ansMin, sum);
            ansMax = Math.max(ansMax, sum);

            return;
        }

        for(int i = 0; i < N - 1; ++i) {
            if(visited[i]) continue;
            visited[i] = true;
            selected[selected.length - cnt] = operations.get(i);
            permutation(cnt-1, selected, visited);
            visited[i] = false;
        }
    }
}
