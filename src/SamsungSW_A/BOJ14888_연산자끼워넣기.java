package SamsungSW_A;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ14888_연산자끼워넣기 {

    private static int N;
    private static int minVal, maxVal;
    private static int[] arr;
    private static List<Integer> operationIdxList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        minVal = Integer.MAX_VALUE;
        maxVal = Integer.MIN_VALUE;
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        operationIdxList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            int idx = Integer.parseInt(st.nextToken());
            while(idx-- > 0){
                operationIdxList.add(i);
            }
        }

        permutation(N-1, new int[N-1], new boolean[N-1]);

        System.out.println(maxVal);
        System.out.println(minVal);
    }

    public static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            int value = calculate(selected);
            maxVal = Math.max(maxVal, value);
            minVal = Math.min(minVal, value);
            return;
        }

        for (int i = 0; i < N-1; i++) {
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            selected[selected.length - cnt] = operationIdxList.get(i);
            permutation(cnt-1, selected, visited);
            visited[i] = false;
        }
    }

    public static int calculate(int[] selected){
        int arrIdx = 0;
        int sum = arr[arrIdx];

        for(int idx : selected){
            ++arrIdx;
            switch (idx){
                case 0: // '+'
                    sum += arr[arrIdx];
                    break;
                case 1: // '-'
                    sum -= arr[arrIdx];
                    break;
                case 2: // '*'
                    sum *= arr[arrIdx];
                    break;
                case 3:
                    sum /= arr[arrIdx];
                    break;
            }
        }

        return sum;
    }
}
