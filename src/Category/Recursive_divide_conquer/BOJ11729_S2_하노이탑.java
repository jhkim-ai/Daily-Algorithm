package Category.Recursive_divide_conquer;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BOJ11729_S2_하노이탑 {

    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        sb.append((int)Math.pow(2,N)-1).append("\n");
        hanoi(N, 1, 3, 2);
        System.out.println(sb);
    }

    static void hanoi(int cnt, int start, int end, int tmp){
        if (cnt == 1){
            sb.append(start).append(" ").append(end).append("\n");
            return;
        }

        hanoi(cnt -1, start, tmp, end);
        sb.append(start).append(" ").append(end).append("\n");
        hanoi(cnt -1, tmp, end, start);
    }
}
