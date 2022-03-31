package SamsungSW_A.삼성_22년_상반기_준비;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class _10_BOJ14888_연산자끼워넣기 {

    private static final char[] OP = {'+', '-', '*', '/'};
    private static int N;
    private static int maxVal, minVal;
    private static int[] arr;
    private static List<Character> operator;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        operator = new ArrayList<>();
        maxVal = Integer.MIN_VALUE;
        minVal = Integer.MAX_VALUE;

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < N; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < 4; ++i){
            int cnt = Integer.parseInt(st.nextToken());
            while(cnt-- > 0){
                operator.add(OP[i]);
            }
        }

        permutation(N-1, new char[N-1], new boolean[N-1]);

        System.out.println(maxVal);
        System.out.println(minVal);
    }

    public static void permutation(int cnt, char[] selected, boolean[] visited){
        if(cnt == 0){
            int val = getNumber(selected);
            minVal = Math.min(minVal, val);
            maxVal = Math.max(maxVal, val);
            return;
        }

        for(int i = 0; i < N-1; ++i){
            if(!visited[i]){
                visited[i] = true;
                selected[selected.length - cnt] = operator.get(i);
                permutation(cnt - 1, selected, visited);
                visited[i] = false;
            }
        }
    }

    public static int getNumber(char[] selected){
        int sum = arr[0];

        for(int i = 1; i < N; ++i){
            char op = selected[i-1];
            if(op == '+'){
                sum += arr[i];
            } else if(op == '-'){
                sum -= arr[i];
            } else if(op == '*'){
                sum *= arr[i];
            } else {
                sum /= arr[i];
            }
        }

        return sum;
    }
}
