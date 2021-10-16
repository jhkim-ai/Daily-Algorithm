package StepByStep.day211030;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ16943_숫자재배치 {

    private static int[] A;
    private static int B;
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        String inputA = st.nextToken();
        B = Integer.parseInt(st.nextToken());
        A = new int[inputA.length()];
        for(int i = 0; i < A.length; ++i){
            A[i] = inputA.charAt(i) - '0';
        }

        permutation(A.length, new int[A.length], new boolean[A.length]);
        if(ans == 0){
            ans = -1;
        }
        System.out.println(ans);
    }

    public static void permutation(int cnt, int[] selected, boolean[] visited){
        if(cnt == 0){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < A.length; ++i){
                if(i == 0 && selected[i] == 0){
                    return;
                }
                sb.append(selected[i]);
            }
            int tmp = Integer.parseInt(sb.toString());
            if(tmp < B && tmp > ans){
                ans = tmp;
            }
            return;
        }

        for(int i = 0; i < A.length; ++i){
            if(!visited[i]){
                visited[i] = true;
                selected[selected.length - cnt] = A[i];
                permutation(cnt-1, selected, visited);
                visited[i] = false;
            }
        }
    }
}
